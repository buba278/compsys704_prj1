package machines.bigpicture;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import machines.conveyor.ConveyorVizWorker;
import machines.coordinator.CoordinatorVizWorker;
import machines.labeller.LabellerVizWorker;
import machines.pos.OrderQueue;
import machines.pos.PosFormPanel;
import machines.rotarytable.RotaryTableVizWorker;
import machines.sorter.SorterVizWorker;
import org.compsys704.Ports;
import org.compsys704.SignalServer;

// Standalone launcher for the merged Big-Picture view (see BigPictureCanvas). Runs its
// own SignalServers for the Coordinator's viz signals (liquidARatioE/targetVolumeMlE/
// bottlesNeededE/bottlesFilledE/bottleFaultedE/batchDoneE/batchElapsedE - see
// coordinator.sysj) and each other station's *BigE viz signals (mirrors of that
// station's own *E signals - see conveyorPlant.sysj/xml, rotaryTablePlant.sysj/xml,
// labellerPlant.sysj/xml, sorterPlant.sysj/xml) - the same pattern FillerPanel/PosPanel
// use for their own station's viz port, so the batch/recipe/progress panel, the
// conveyor-belt bottle animation, the rotary table dial, the labeller, and the sorter
// are all genuinely live. The Purchase Order form/queue on the left (PosFormPanel) is
// the exact same component the individual Pos window uses - see OrderQueue for how the
// two windows share one queue.
public class BigPicture extends JFrame {

	private static final long serialVersionUID = 1L;

	private final PosFormPanel posForm = new PosFormPanel();

	public BigPicture() {
		BigPictureCanvas canvas = new BigPictureCanvas();
		canvas.setPreferredSize(new Dimension(BigPictureCanvas.CANVAS_W, BigPictureCanvas.CANVAS_H));
		canvas.setBackground(Color.WHITE);
		this.add(canvas, BorderLayout.CENTER);
		this.add(posForm, BorderLayout.WEST);

		this.setTitle("Big-Picture Visualisation - EABS Overview");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	public static void main(String[] args) {
		// This window is a queue mirror, not the hub - see OrderQueue's class comment.
		OrderQueue.startAsClient();

		BigPicture frame = new BigPicture();
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		SignalServer<CoordinatorVizWorker> coordinatorServer =
				new SignalServer<CoordinatorVizWorker>(Ports.PORT_COORDINATOR_VIZ, CoordinatorVizWorker.class);
		new Thread(coordinatorServer).start();

		SignalServer<ConveyorVizWorker> conveyorServer =
				new SignalServer<ConveyorVizWorker>(Ports.PORT_CONVEYOR_BIGPICTURE_VIZ, ConveyorVizWorker.class);
		new Thread(conveyorServer).start();

		SignalServer<RotaryTableVizWorker> rotaryTableServer =
				new SignalServer<RotaryTableVizWorker>(Ports.PORT_ROTARYTABLE_BIGPICTURE_VIZ, RotaryTableVizWorker.class);
		new Thread(rotaryTableServer).start();

		SignalServer<LabellerVizWorker> labellerServer =
				new SignalServer<LabellerVizWorker>(Ports.PORT_LABELLER_BIGPICTURE_VIZ, LabellerVizWorker.class);
		new Thread(labellerServer).start();

		SignalServer<SorterVizWorker> sorterServer =
				new SignalServer<SorterVizWorker>(Ports.PORT_SORTER_BIGPICTURE_VIZ, SorterVizWorker.class);
		new Thread(sorterServer).start();

		while (true) {
			try {
				frame.posForm.refresh();
				frame.repaint();
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
