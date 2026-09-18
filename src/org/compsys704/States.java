package org.compsys704;

public class States {
	
	public static volatile boolean ARM_AT_DEST = true;
	public static volatile boolean ARM_AT_SOURCE = !ARM_AT_DEST;
	public static volatile boolean PUSHER_RETRACTED = true;
	public static volatile boolean PUSHER_EXTENDED = !PUSHER_RETRACTED;
	public static volatile boolean GRIPPED = false;
	public static volatile boolean MAG_EMPTY = false;

	// Remaining caps in the magazine, for a real per-cap stack rather than just the binary MAG_EMPTY.
	public static volatile int CAP_COUNT = 5;

	public static volatile boolean CAP_READY = false;

	// Fault indicator (see run.LidFaultState / the IP report)
	public static volatile boolean FAULTED = false;

	// Persistent backup-unit-active indicator (see run.LidFaultState)
	public static volatile boolean BACKUP_ACTIVE = false;
}
