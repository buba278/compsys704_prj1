package org.compsys704;

import java.io.IOException;

public class WindowFocuser {

	public static void focus(String windowTitle) {
		String escaped = windowTitle.replace("'", "''");
		String command = "(New-Object -ComObject wscript.shell).AppActivate('" + escaped + "')";
		try {
			new ProcessBuilder("powershell.exe", "-NoProfile", "-WindowStyle", "Hidden", "-Command", command)
					.start();
		} catch (IOException e) {
			System.err.println("[WindowFocuser] failed to focus \"" + windowTitle + "\": " + e);
		}
	}
}
