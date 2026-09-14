package org.compsys704;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Drop-in replacement for com.systemj.SystemJRunner as a launch config's
 * Main Type - same Program Arguments (the station's .xml path), but writes
 * stdout/stderr to a file under logs/ instead of the console. With this many
 * stations running at once, Eclipse's Console view repainting for every one
 * of them is the main source of lag; routing output straight to a file
 * (paired with the launch config's capture_output=false, so Eclipse doesn't
 * allocate a console for this process at all) avoids that, while still
 * leaving a file to hand off instead of copying console text by hand. See
 * LoggingLauncherConsole for the variant that also shows output live, for
 * the separate run group meant for watching a station.
 */
public class LoggingLauncher {
	public static void main(String[] args) throws IOException {
		PrintStream fileOut = openLogFile(args);
		System.setOut(fileOut);
		System.setErr(fileOut);
		com.systemj.SystemJRunner.main(args);
	}

	// A run group (RunAll) starts every station's own JVM independently, so
	// there's no shared "this is one run" id to hand them - each process just
	// rounds its own start time down to a bucket. Any two stations that start
	// within the same window land in the same logs/<bucket>/ folder, which
	// covers a launch group's normal few-second spread without needing any
	// cross-process coordination.
	private static final long RUN_BUCKET_MS = 30_000;

	static PrintStream openLogFile(String[] args) throws IOException {
		String station = "station";
		if (args.length > 0) {
			String path = args[0].replace('\\', '/');
			String name = path.substring(path.lastIndexOf('/') + 1);
			if (name.toLowerCase().endsWith(".xml")) {
				name = name.substring(0, name.length() - 4);
			}
			station = name;
		}

		long bucketStart = (System.currentTimeMillis() / RUN_BUCKET_MS) * RUN_BUCKET_MS;
		String runId = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date(bucketStart));
		File logDir = new File(new File("logs"), runId);
		logDir.mkdirs();
		File logFile = new File(logDir, station + ".log");
		return new PrintStream(new TimestampingOutputStream(new FileOutputStream(logFile)), true);
	}

	/**
	 * Prefixes every line written with a HH:mm:ss.SSS timestamp, at the byte
	 * level so it works uniformly under print/println/printf - lets you see
	 * real elapsed time between log lines (including across different
	 * stations' separate log files) instead of just their relative order,
	 * which is what actually shows up an unexpectedly long gap between two
	 * steps rather than just that one happened after the other.
	 */
	static class TimestampingOutputStream extends OutputStream {
		private final OutputStream out;
		private final SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss.SSS");
		private boolean atLineStart = true;

		TimestampingOutputStream(OutputStream out) { this.out = out; }

		@Override
		public synchronized void write(int b) throws IOException {
			if (atLineStart) {
				out.write((fmt.format(new Date()) + " ").getBytes(StandardCharsets.UTF_8));
				atLineStart = false;
			}
			out.write(b);
			if (b == '\n') {
				atLineStart = true;
			}
		}

		@Override
		public synchronized void write(byte[] buf, int off, int len) throws IOException {
			for (int i = 0; i < len; i++) {
				write(buf[off + i]);
			}
		}

		@Override
		public void flush() throws IOException { out.flush(); }

		@Override
		public void close() throws IOException { out.close(); }
	}

	static class TeeOutputStream extends OutputStream {
		private final OutputStream a, b;
		TeeOutputStream(OutputStream a, OutputStream b) { this.a = a; this.b = b; }
		@Override public void write(int x) throws IOException { a.write(x); b.write(x); }
		@Override public void write(byte[] buf, int off, int len) throws IOException { a.write(buf, off, len); b.write(buf, off, len); }
		@Override public void flush() throws IOException { a.flush(); b.flush(); }
	}
}
