package run;

import org.compsys704.Ports;
import org.compsys704.SignalLevelClient;

/** Cross-domain sends for the Labeller <-> Sorter handoff.
 *  Same reasoning as RotaryConveyorBridge: these SignalLevelClients must be owned by
 *  a class in a single-segment package (like this one) rather than declared inline
 *  inside a .sysj reactive body - this build of sjc crashes resolving types from
 *  multi-segment packages (e.g. org.compsys704) when used for a local instance
 *  declaration inside a .sysj reactive body.
 */
public class LabellerSorterBridge {
	private static final SignalLevelClient BOTTLE_FROM_LABELLER =
			new SignalLevelClient(Ports.PORT_SORTER_PLANT, Ports.SORTER_BOTTLE_FROM_LABELLER);
	private static final SignalLevelClient SORTER_TAKEN_ACK =
			new SignalLevelClient(Ports.PORT_LABELLER_PLANT, Ports.LABELLER_SORTER_TAKEN_ACK);

	public static void setBottleFromLabeller(boolean state) { BOTTLE_FROM_LABELLER.send(state); }
	public static void setSorterTakenAck(boolean state) { SORTER_TAKEN_ACK.send(state); }
}
