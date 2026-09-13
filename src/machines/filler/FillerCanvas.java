package machines.filler;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class FillerCanvas extends JPanel {

      private static final double PIXELS_PER_ML = 0.3;
      private static final int CONTAINER_BOTTOM_Y = 230;
      private static final int BASE_VOLUME_ML = 200;
      private static final int CONTAINER_HEIGHT = (int) (BASE_VOLUME_ML * PIXELS_PER_ML);
      private static final double CONTAINER_WIDTH_PER_ML = 30.0 / BASE_VOLUME_ML;

      private static final int INDICATOR_X = 10;
      private static final int LABEL_X = 30;
      private static final int VALVE1_Y = 10;
      private static final int VALVE2_Y = 35;
      private static final int FILL_DONE_Y = 60;
      private static final int FAULT_Y = 85;

      private static final Color LIQUID_A_COLOR = new Color(135, 190, 255);  // light blue
      private static final Color LIQUID_B_COLOR = new Color(255, 195, 130);  // light orange

      @Override
      protected void paintComponent(Graphics g) {
              super.paintComponent(g);

              g.setColor(FillerState.VALVE1_OPEN ? LIQUID_A_COLOR : Color.LIGHT_GRAY);
              g.fillOval(INDICATOR_X, VALVE1_Y, 15, 15);
              g.setColor(Color.BLACK);
              g.drawString("Valve 1 (Liquid A)", LABEL_X, VALVE1_Y + 12);

              g.setColor(FillerState.VALVE2_OPEN ? LIQUID_B_COLOR : Color.LIGHT_GRAY);
              g.fillOval(INDICATOR_X, VALVE2_Y, 15, 15);
              g.setColor(Color.BLACK);
              g.drawString("Valve 2 (Liquid B)", LABEL_X, VALVE2_Y + 12);

              g.setColor(FillerState.FILL_DONE ? Color.GREEN : Color.LIGHT_GRAY);
              g.fillOval(INDICATOR_X, FILL_DONE_Y, 15, 15);
              g.setColor(Color.BLACK);
              g.drawString("Fill Done", LABEL_X, FILL_DONE_Y + 12);

              g.setColor(FillerState.FAULT ? Color.RED : Color.LIGHT_GRAY);
              g.fillOval(INDICATOR_X, FAULT_Y, 15, 15);
              g.setColor(Color.BLACK);
              g.drawString("FAULT", LABEL_X, FAULT_Y + 12);

              // Container height is fixed (sized for BASE_VOLUME_ML); bigger targets widen it
              // instead, and it stays centered so widening never pushes it off-canvas.
              int containerTop = CONTAINER_BOTTOM_Y - CONTAINER_HEIGHT;
              int containerWidth = (int) (FillerState.TARGET_VOLUME_ML * CONTAINER_WIDTH_PER_ML);
              int containerX = (getWidth() - containerWidth) / 2;

              g.drawString(FillerState.TARGET_VOLUME_ML + " ml", containerX, containerTop - 6);
              g.drawRect(containerX, containerTop, containerWidth, CONTAINER_HEIGHT);

              double fraction = FillerState.FILL_LEVEL / (double) FillerState.TARGET_VOLUME_ML;
              double boundaryFraction = FillerState.PHASE1_END_LEVEL / (double) FillerState.TARGET_VOLUME_ML;
              int filled = (int) Math.min(CONTAINER_HEIGHT, fraction * CONTAINER_HEIGHT);
              int boundary = (int) Math.min(filled, boundaryFraction * CONTAINER_HEIGHT);

              if (boundary > 0) {
                    g.setColor(LIQUID_A_COLOR);
                    g.fillRect(containerX + 1, CONTAINER_BOTTOM_Y - boundary, containerWidth - 1, boundary);
              }
              if (filled > boundary) {
                    Color topColor = FillerState.PHASE1_END_LEVEL > 0 ? LIQUID_B_COLOR : LIQUID_A_COLOR;
                    g.setColor(topColor);
                    g.fillRect(containerX + 1, CONTAINER_BOTTOM_Y - filled, containerWidth - 1, filled - boundary);
              }
      }
}
