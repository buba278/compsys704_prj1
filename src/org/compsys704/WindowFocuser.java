package org.compsys704;

import java.io.IOException;

/** Brings another station's already-open JFrame to the foreground, even though it lives
 *  in a different JVM process - each clock domain's GUI is its own separate process (see
 *  CLAUDE.md), so a click in the Big-Picture window can't just call toFront() on a
 *  JFrame instance it doesn't own. Shells out to a one-line PowerShell command using the
 *  WScript.Shell COM object's AppActivate, which finds a top-level window by its title
 *  text (matching each station's own setTitle(...) call / WindowTile's key list) and
 *  activates it, regardless of which process it belongs to.
 */
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
