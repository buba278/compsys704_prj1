package digitaltwin;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
 
/**
 * The one process that actually holds the SystemTwin / PlantTwin / ProductTwin
 * state. Must be started explicitly as its own process -- NOT as a static
 * initializer side effect of loading SystemTwin -- because capperController.xml
 * / capperPlant.xml confirm controllers and plants run as separate JVMs, and
 * whichever one happened to load SystemTwin first would otherwise silently
 * "win" the port while every other controller's bind attempt failed quietly.
 *
 * Runs two independent sockets:
 *
 *  - Visualiser push socket (default 8080): unchanged from the original
 *    design. TwinListener/TwinVisualiser connect here and receive the full
 *    SystemTwin JSON, pushed once every 100ms, with no changes needed on
 *    that side.
 *
 *  - Controller control socket (default 9090): what TwinClient talks to.
 *    One command per line in, one reply per line out, pipe-delimited:
 *
 *      PLANT_UPDATE|<stationId>|<IDLE|IN_PROGRESS|ERROR>|<errorDetailOrEmpty>  -> OK / ERR ...
 *      PLANT_EVENT|<stationId>|<eventLabel>|<productIdOrDash>                  -> OK / ERR ...
 *      PRODUCT_CREATE|<volumeMl>|<liquidRatio>                                 -> <productId> / ERR ...
 *      PRODUCT_ARCHIVE|<productId>|<true|false rejected>                       -> OK / ERR ...
 *      QUERY_SYSTEM                                                            -> <one-line JSON>
 *
 * Run with: java -cp bin digitaltwin.TwinServer [visualiserPort] [controlPort]
 * (defaults: 8080 visualiser, 9090 control).
 */
public class TwinServer {
 
    public static void main(String[] args) {
        int visualiserPort = args.length > 0 ? Integer.parseInt(args[0]) : 7070;
        int controlPort = args.length > 1 ? Integer.parseInt(args[1]) : 9090;
 
        // Touch every station singleton once so they're all registered with
        // SystemTwin before any client connects. Add your other renamed
        // subclasses here alongside CapperPlantTwin as you bring them in --
        // e.g. RotaryPlantTwin.getInstance(), FillingPlantTwin.getInstance(), etc.
        CapperPlantTwin.getInstance();
        LabellerPlantTwin.getInstance();
        FillerPlantTwin.getInstance();
        LidPlacingPlantTwin.getInstance();
        SorterPlantTwin.getInstance();
        RotaryPlantTwin.getInstance();
        LoadingConveyorPlantTwin.getInstance();
 
        startVisualiserSocket(visualiserPort);
        startControlSocket(controlPort);
    }
 
    // ---- Visualiser push socket (original design, fixed to allow reconnects) ----
 
    private static void startVisualiserSocket(int port) {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                System.out.println("Digital Twin TCP Server (visualiser push) running on port " + port);
 
                while (true) {
                    // Blocks until a Swing app connects
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Swing Visualiser connected: " + clientSocket.getInetAddress());
 
                    // Each pushed connection gets its own thread now, so a
                    // dead/disconnected visualiser can't stop accept() from
                    // picking up the next one.
                    Thread pusher = new Thread(() -> pushLoop(clientSocket));
                    pusher.setDaemon(true);
                    pusher.start();
                }
            } catch (Exception e) {
                System.err.println("Failed to start Twin visualiser socket: " + e.getMessage());
            }
        }).start();
    }
 
    private static void pushLoop(Socket clientSocket) {
        try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            while (!clientSocket.isClosed()) {
                String json = SystemTwin.getInstance().toJson();
                out.println(json);
                // PrintWriter swallows IOExceptions -- checkError() is the
                // only way to notice the other end has actually gone away.
                if (out.checkError()) {
                    break;
                }
                Thread.sleep(100); // 10 Hz
            }
        } catch (Exception e) {
            // fall through to cleanup below
        } finally {
            System.out.println("Swing Visualiser disconnected. Waiting for reconnect...");
            try {
                clientSocket.close();
            } catch (IOException ignored) {
                // already gone
            }
        }
    }
 
    // ---- Controller control socket (new) ----
 
    private static void startControlSocket(int port) {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                System.out.println("Digital Twin TCP Server (controller control) running on port " + port);
                while (true) {
                    Socket client = serverSocket.accept();
                    Thread handler = new Thread(() -> handleControlClient(client));
                    handler.setDaemon(true);
                    handler.start();
                }
            } catch (Exception e) {
                System.err.println("Failed to start Twin control socket: " + e.getMessage());
            }
        }).start();
    }
 
    private static void handleControlClient(Socket socket) {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true)
        ) {
            String line;
            while ((line = in.readLine()) != null) {
                out.println(handleCommand(line));
            }
        } catch (IOException e) {
            // controller disconnected -- nothing to do
        } finally {
            try {
                socket.close();
            } catch (IOException ignored) {
                // already closed
            }
        }
    }
 
    private static String handleCommand(String line) {
        try {
            String[] parts = line.split("\\|", -1);
            String cmd = parts[0];
 
            switch (cmd) {
            	case "BATCH_START": {
            		String batchId = parts[1];
            		int targetCount = Integer.parseInt(parts[2]);
            		SystemTwin.getInstance().startBatch(batchId, targetCount);
            		return "OK";
            	}
            	
            	case "BATCH_PROGRESS": {
            	    int completedCount = Integer.parseInt(parts[1]);
            	    SystemTwin.getInstance().reportBatchProgress(completedCount);
            	    return "OK";
            	}
            	
            	case "BATCH_DONE": {
            	    int elapsedMs = Integer.parseInt(parts[1]);
            	    SystemTwin.getInstance().reportBatchDone(elapsedMs);
            	    return "OK";
            	}
            	
            	case "PRODUCT_AT_POSITION": {
            	    int position = Integer.parseInt(parts[1]);
            	    String productId = SystemTwin.getInstance().getProductIdAtPosition(position);
            	    return productId != null ? productId : "ERR no product at position " + position;
            	}
            	
            	case "ROTARY_TURN": {
            	    System.out.println("TwinServer: Received ROTARY_TURN command from client.");
            	    SystemTwin.getInstance().onRotaryTurn();
            	    return "OK";
            	}
            	
                case "PLANT_UPDATE": {
                    PlantTwin twin = requireStation(parts[1]);
                    PlantTwin.State state = PlantTwin.State.valueOf(parts[2]);
                    String errorDetail = (parts.length > 3 && !parts[3].isEmpty()) ? parts[3] : null;
                    twin.update(state, errorDetail);
                    return "OK";
                }
                case "PLANT_EVENT": {
                    PlantTwin twin = requireStation(parts[1]);
                    String eventLabel = parts[2];
                    String productId = (parts.length > 3 && !parts[3].equals("-") && !parts[3].isEmpty()) ? parts[3] : null;
                    twin.recordEvent(eventLabel, productId);
                    return "OK";
                }
                case "PRODUCT_CREATE": {
                    // Routed through SystemTwin directly (package-private access) rather
                    // than a specific station's method, since this file doesn't assume
                    // the shape of your renamed Filling/Sorting twin classes. If you'd
                    // rather route this through e.g. FillingPlantTwin.reportNewBottle(...)
                    // for its own side effects, swap this line for that call.
                    double volumeMl = Double.parseDouble(parts[1]);
                    double liquidRatio = Double.parseDouble(parts[2]);
                    return SystemTwin.getInstance().createProductTwin(volumeMl, liquidRatio);
                }
                case "PRODUCT_ARCHIVE": {
                    String productId = parts[1];
                    boolean rejected = Boolean.parseBoolean(parts[2]);
                    SystemTwin.getInstance().archiveProductTwin(productId, rejected);
                    return "OK";
                }
                case "QUERY_SYSTEM":
                    return SystemTwin.getInstance().toJson();
                default:
                    return "ERR unknown command: " + cmd;
            }
        } catch (Exception e) {
            return "ERR " + e.getClass().getSimpleName() + ": " + e.getMessage();
        }
    }
 
    private static PlantTwin requireStation(String stationId) {
        PlantTwin twin = SystemTwin.getInstance().getPlantTwin(stationId);
        if (twin == null) {
            throw new IllegalArgumentException("unknown stationId: " + stationId);
        }
        return twin;
    }
}
