package machines.bigpicture;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import machines.capper.CapperVizWorker;
import machines.conveyor.ConveyorVizWorker;
import machines.coordinator.CoordinatorVizWorker;
import machines.filler.FillerVizWorker;
import machines.labeller.LabellerVizWorker;
import machines.pos.OrderQueue;
import machines.pos.PosFormPanel;
import machines.rotarytable.RotaryTableVizWorker;
import machines.sorter.SorterVizWorker;
import org.compsys704.LoaderVizWorker;
import org.compsys704.Ports;
import org.compsys704.SignalServer;

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

		// Fault-tolerance IP's fault/backup indicators (Filler/Capper/Lid Placer)
		SignalServer<FillerVizWorker> fillerServer =
				new SignalServer<FillerVizWorker>(Ports.PORT_FILLER_BIGPICTURE_VIZ, FillerVizWorker.class);
		new Thread(fillerServer).start();

		SignalServer<CapperVizWorker> capperServer =
				new SignalServer<CapperVizWorker>(Ports.PORT_CAPPER_BIGPICTURE_VIZ, CapperVizWorker.class);
		new Thread(capperServer).start();

		SignalServer<LoaderVizWorker> lidPlacerServer =
				new SignalServer<LoaderVizWorker>(Ports.PORT_LOADER_BIGPICTURE_VIZ, LoaderVizWorker.class);
		new Thread(lidPlacerServer).start();

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
