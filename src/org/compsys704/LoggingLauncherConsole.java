package org.compsys704;

import java.io.IOException;
import java.io.PrintStream;

/**
 * Same as LoggingLauncher but also tees output to the console, for the
 * "Live" run group used when you actually want to watch a station instead
 * of just collecting its log file - see LoggingLauncher for why the default
 * run group doesn't do this.
 */
public class LoggingLauncherConsole {
	public static void main(String[] args) throws IOException {
		PrintStream fileOut = LoggingLauncher.openLogFile(args);
		System.setOut(new PrintStream(new LoggingLauncher.TeeOutputStream(System.out, fileOut), true));
		System.setErr(new PrintStream(new LoggingLauncher.TeeOutputStream(System.err, fileOut), true));
		com.systemj.SystemJRunner.main(args);
	}
}
