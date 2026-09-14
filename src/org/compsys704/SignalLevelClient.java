package org.compsys704;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;

// Sends a pure signal's presence/absence directly, unlike SignalClient (momentary
// pulse only) or SignalRadioClient (always sends presence=true, value-select only)
public class SignalLevelClient {

	Socket s = new Socket();
	ObjectOutputStream oos = null;
	int port;
	final String ip = "127.0.0.1";

	String dest;

	public SignalLevelClient(int p, String dest) {
		this.dest = dest;
		port = p;
		try {
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void send(boolean state) {
		try {
			if (s.isClosed()) {
				s = new Socket();
				// 10ms was too tight for a loopback connect right after every
				// station launches at once (all 14+ JVMs contending for CPU) -
				// every retry in that window failed silently and the caller
				// (a bounded ack-or-timeout loop) just ran out its clock with
				// nothing ever delivered. 500ms matches OrderQueue's client,
				// which doesn't hit this.
				s.connect(new InetSocketAddress(ip, port), 500);
				oos = new ObjectOutputStream(s.getOutputStream());
				oos.writeObject(dest);
				int resp = s.getInputStream().read();
				if (resp < 0)
					throw new ConnectException("Not thru");
			}
			oos.writeObject(new Object[]{state});
		} catch (IOException ee) {
			// Was silently swallowed before - made every failed send here
			// indistinguishable from a working one in the logs.
			System.err.println("[SignalLevelClient] send to " + dest + " failed: " + ee);
			try { s.close(); } catch (IOException e1) {
				e1.printStackTrace();
				System.exit(1);
			}
		}
	}
}
