package digitaltwin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * What a SystemJ controller actually talks to. Each controller process
 * creates its own TwinClient (an ordinary {@code new}, unlike PlantTwin
 * subclasses, which stay singletons -- but now inside TwinServer's process,
 * not the controller's) and every call is forwarded over a socket to
 * TwinServer's control port, since that's the only place the real twin
 * state lives once controllers and plants are separate processes.
 *
 * Twin reporting is intentionally best-effort: if the server is
 * unreachable, every method here logs once and returns quietly rather than
 * throwing, so a twin-server hiccup can never break the physical control
 * loop it's just an observer of.
 *
 * Typical use inside a controller's SystemJ body:
 * <pre>
 *   TwinClient twin = new TwinClient("cap-screwer", "127.0.0.1", 9090);
 *   twin.update(PlantTwin.State.IDLE);
 *   ...
 *   twin.recordEvent("Cap screwed on", productId);
 * </pre>
 */
public final class TwinClient {

    private final String stationId;
    private final String host;
    private final int port;

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private boolean warnedThisOutage = false;

    public TwinClient(String stationId, String host, int port) {
        this.stationId = stationId;
        this.host = host;
        this.port = port;
    }

    public synchronized void update(PlantTwin.State state) {
        update(state, null);
    }

    public synchronized void update(PlantTwin.State state, String errorDetail) {
        sendCommand("PLANT_UPDATE|" + stationId + "|" + state.name() + "|" + safe(errorDetail));
    }

    public synchronized void recordEvent(String eventLabel) {
        recordEvent(eventLabel, null);
    }

    public synchronized void recordEvent(String eventLabel, String productId) {
        sendCommand("PLANT_EVENT|" + stationId + "|" + safe(eventLabel) + "|" + (productId == null ? "-" : productId));
    }

    /** Only meaningful when called from the Filling station's client. Returns the new productId, or null on failure. */
    public synchronized String createProduct(double volumeMl, double liquidRatio) {
        return sendCommand("PRODUCT_CREATE|" + volumeMl + "|" + liquidRatio);
    }

    /** Only meaningful when called from the Sorting station's client. */
    public synchronized void archiveProduct(String productId, boolean rejected) {
        sendCommand("PRODUCT_ARCHIVE|" + productId + "|" + rejected);
    }
    
    public synchronized void startBatch(String batchId, int targetCount) {
        sendCommand("BATCH_START|" + safe(batchId) + "|" + targetCount);
    }
    

    public synchronized void reportBatchElapsed(long elapsedMs) {
    	sendCommand("BATCH_ELAPSED|" + elapsedMs);
    }
    
    public synchronized void reportProgress(int completedCount) {
        sendCommand("BATCH_PROGRESS|" + completedCount);
    }
    
    public synchronized void reportBatchDone(int elapsedMs) {
        sendCommand("BATCH_DONE|" + elapsedMs);
    }
    
    public synchronized String getProductIdAtPosition(int position) {
        return sendCommand("PRODUCT_AT_POSITION|" + position);
    }

    /** Returns the full SystemTwin JSON feed, or null if the server is unreachable. */
    public synchronized String querySystemJson() {
        return sendCommand("QUERY_SYSTEM");
    }

    private String safe(String field) {
        if (field == null) {
            return "";
        }
        // The wire protocol is pipe-delimited and line-delimited -- strip
        // anything that would otherwise corrupt a message.
        return field.replace("|", "/").replace("\n", " ").replace("\r", " ");
    }

    private String sendCommand(String line) {
        if (!ensureConnected()) {
            return null;
        }
        try {
            out.println(line);
            String reply = in.readLine();
            if (reply == null) {
                closeQuietly();
                warnOnce("TwinClient [" + stationId + "]: connection to twin server closed.");
                return null;
            }
            if (reply.startsWith("ERR ")) {
                System.err.println("TwinClient [" + stationId + "]: twin server rejected \"" + line + "\" -- " + reply);
                return null;
            }
            warnedThisOutage = false;
            return reply;
        } catch (IOException e) {
            closeQuietly();
            warnOnce("TwinClient [" + stationId + "]: lost connection to twin server (" + e.getMessage() + ").");
            return null;
        }
    }

    private boolean ensureConnected() {
        if (socket != null && socket.isConnected() && !socket.isClosed()) {
            return true;
        }
        try {
            socket = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
            warnedThisOutage = false;
            return true;
        } catch (IOException e) {
            closeQuietly();
            warnOnce("TwinClient [" + stationId + "]: could not reach twin server at "
                    + host + ":" + port + " (" + e.getMessage() + "). Continuing without twin reporting.");
            return false;
        }
    }

    private void warnOnce(String message) {
        if (!warnedThisOutage) {
            System.err.println(message);
            warnedThisOutage = true;
        }
    }

    private void closeQuietly() {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException ignored) {
            // already gone
        }
        socket = null;
        in = null;
        out = null;
    }
}