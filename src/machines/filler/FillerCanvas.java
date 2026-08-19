package machines.filler;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class FillerCanvas extends JPanel {

      private static final double PIXELS_PER_ML = 0.3;
      private static final int CONTAINER_BOTTOM_Y = 230;
      private static final int CONTAINER_X = 160;
      private static final int CONTAINER_WIDTH = 30;

      private static final int INDICATOR_X = 10;
      private static final int LABEL_X = 30;
      private static final int VALVE1_Y = 10;
      private static final int VALVE2_Y = 35;
      private static final int FILL_DONE_Y = 60;

      @Override
      protected void paintComponent(Graphics g) {
              super.paintComponent(g);

              g.setColor(FillerState.VALVE1_OPEN ? Color.GREEN : Color.LIGHT_GRAY);
              g.fillOval(INDICATOR_X, VALVE1_Y, 15, 15);
              g.setColor(Color.BLACK);
              g.drawString("Valve 1 (Liquid A)", LABEL_X, VALVE1_Y + 12);

              g.setColor(FillerState.VALVE2_OPEN ? Color.GREEN : Color.LIGHT_GRAY);
              g.fillOval(INDICATOR_X, VALVE2_Y, 15, 15);
              g.setColor(Color.BLACK);
              g.drawString("Valve 2 (Liquid B)", LABEL_X, VALVE2_Y + 12);

              g.setColor(FillerState.FILL_DONE ? Color.GREEN : Color.LIGHT_GRAY);
              g.fillOval(INDICATOR_X, FILL_DONE_Y, 15, 15);
              g.setColor(Color.BLACK);
              g.drawString("Fill Done", LABEL_X, FILL_DONE_Y + 12);

              int containerHeight = (int) (FillerState.TARGET_VOLUME_ML * PIXELS_PER_ML);
              int containerTop = CONTAINER_BOTTOM_Y - containerHeight;

              g.drawString(FillerState.TARGET_VOLUME_ML + " ml", CONTAINER_X, containerTop - 6);
              g.drawRect(CONTAINER_X, containerTop, CONTAINER_WIDTH, containerHeight);

              int filled = (int) Math.min(containerHeight, FillerState.FILL_LEVEL * PIXELS_PER_ML);
              g.setColor(Color.CYAN);
              g.fillRect(CONTAINER_X + 1, CONTAINER_BOTTOM_Y - filled, CONTAINER_WIDTH - 1, filled);
      }
}
