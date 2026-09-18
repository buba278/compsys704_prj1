package org.compsys704;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Canvas extends JPanel {
	BufferedImage arm1;
	BufferedImage arm2;
	BufferedImage p1;
	BufferedImage p2;
	BufferedImage loader;

	public Canvas(){
		try {
			BufferedImage bi = ImageIO.read(new File("res/arm.png"));
			arm1 = bi.getSubimage(0, 0, 64, 256);
			arm2 = bi.getSubimage(71, 0, 48, 256);
			loader = ImageIO.read(new File("res/loader.png"));
			bi = ImageIO.read(new File("res/pusher.png"));
			p1 = bi.getSubimage(0, 0, 238, 68);
			p2 = bi.getSubimage(238, 0, 172, 68);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);;
		}
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(loader, 0, 100, null);
		
		if(States.ARM_AT_DEST)
			g.drawImage(arm1, 0, 0, null);
		else
			g.drawImage(arm2, 30, 0, null);
		
		if(States.GRIPPED){
			if(States.ARM_AT_DEST){
				g.setColor(Color.black);
				g.fillOval(10, 11, 30, 30);
				g.setColor(Color.red);
				g.fillOval(10, 11, 15, 15);

			}
			else{
				g.setColor(Color.black);
				g.fillOval(40, 243, 30, 30);
				g.setColor(Color.red);
				g.fillOval(35, 232, 15, 15);
			}
			g.setColor(Color.black);
		}
//		else{
			if(States.CAP_READY){ // A cap is pushed to the source pos
				g.setColor(Color.black);
				g.fillOval(40, 243, 30, 30);
			}
//		}
		
		if(States.PUSHER_RETRACTED){
			g.drawImage(p1, 90, 225, null);
			if(!States.MAG_EMPTY){
				g.setColor(Color.black);
				g.fillOval(154, 243, 30, 30);
			}
		}
		else{
			g.drawImage(p2, 90, 225, null);
		}
		
		// Magazine puck stack: cap.png was a single flat image showing a fixed
		// 4-puck stack with no way to remove pucks individually, so it's
		// drawn programmatically instead - one puck per remaining cap. The
		// loader.png tube's actual white interior runs y=116-232 (measured
		// directly from the image, loader.png is drawn at (0,100)); 5 slots
		// pitched at 22px fit that with margin. Slots are numbered top (0) to
		// bottom (4), and only the bottom CAP_COUNT slots are drawn - the
		// magazine empties top-down, feeding from the bottom pickup end, so
		// the slot closest to the pickup point is the last to disappear.
		int puckW = 32, puckH = 18, slotPitch = 22, tubeTop = 118;
		for (int i = 0; i < 5; i++) {
			if (i >= 5 - States.CAP_COUNT) {
				g.setColor(Color.black);
				g.fillRect(152, tubeTop + i * slotPitch, puckW, puckH);
			}
		}

		// Fault indicator - always drawn (grey when off), matching FillerCanvas
		g.setColor(States.FAULTED ? Color.red : Color.lightGray);
		g.fillOval(10, 10, 16, 16);
		g.setColor(Color.black);
		g.drawString("FAULT", 30, 22);

		// Redundant-line rerouting from the IP report (section 6): lit once
		// the station has faulted out and handed off to its physical backup
		// unit, and stays lit across subsequent bottles until Clear Fault
		// returns the station to its primary unit.
		g.setColor(States.BACKUP_ACTIVE ? Color.orange : Color.lightGray);
		g.fillOval(10, 32, 16, 16);
		g.setColor(Color.black);
		g.drawString("BACKUP MODE", 30, 44);
	}
}
