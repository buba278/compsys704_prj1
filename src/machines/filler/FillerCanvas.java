package machines.filler;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class FillerCanvas extends JPanel {

      private static final int SCALE_MAX_ML = 500;   // largest DEV preset, just for bar scaling
      private static final int BAR_HEIGHT = 200;

      @Override
      protected void paintComponent(Graphics g) {
              super.paintComponent(g);

              g.setColor(Color.BLACK);
              g.drawRect(40, 20, 30, BAR_HEIGHT);
              int filled = (int) (FillerState.FILL_LEVEL / (double) SCALE_MAX_ML * BAR_HEIGHT);
              g.setColor(Color.CYAN);
              g.fillRect(41, 20 + BAR_HEIGHT - filled, 29, filled);

              g.setColor(FillerState.VALVE1_OPEN ? Color.GREEN : Color.LIGHT_GRAY);
              g.fillOval(10, 10, 15, 15);
              g.setColor(FillerState.VALVE2_OPEN ? Color.GREEN : Color.LIGHT_GRAY);
              g.fillOval(10, 230, 15, 15);

              g.setColor(FillerState.FILL_DONE ? Color.GREEN : Color.LIGHT_GRAY);
              g.drawString("fillDone", 90, 20);
      }
}