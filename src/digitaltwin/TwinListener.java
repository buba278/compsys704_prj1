package digitaltwin;

import javax.swing.SwingUtilities;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class TwinListener {

	public void connectToTwin(String host, int port, TwinVisualiser ui) {
        new Thread(() -> {
            while (true) {
                try (Socket socket = new Socket(host, port);
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    
                    System.out.println("Connected to SystemJ Twin.");
                    String jsonLine;
                    while ((jsonLine = in.readLine()) != null) {
                        final String latestJson = jsonLine;
                        SwingUtilities.invokeLater(() -> ui.updateDashboard(latestJson));
                    }
                } catch (Exception e) {
                    try { Thread.sleep(2000); } catch (InterruptedException ie) {}
                }
            }
        }).start();
    }
}
