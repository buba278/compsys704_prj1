package run;

import org.compsys704.Ports;
import org.compsys704.SignalLevelClient;

/** Cross-domain send for the Lid Placer Controller -> Plant magazine refill.
 *  Same reasoning as the other Rotary*Bridge classes: owned by a class in a
 *  single-segment package rather than declared inline inside a .sysj reactive
 *  body - this build of sjc crashes resolving types from multi-segment
 *  packages (e.g. org.compsys704) when used for a local instance declaration
 *  inside a .sysj reactive body.
 */
public class LoaderRefillBridge {
	private static final SignalLevelClient REFILL =
			new SignalLevelClient(Ports.PORT_LOADER_PLANT, Ports.REFILL_SIGNAL);

	public static void setRefill(boolean state) { REFILL.send(state); }
}
