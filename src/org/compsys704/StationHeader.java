package org.compsys704;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/** A consistent, hard-to-miss "STATION N - NAME" banner for each station's
 *  GUI window, so it's obvious at a glance which rotary table position (see
 *  RotaryTable's own 1-6 legend) a given window corresponds to - added after
 *  user feedback that the windows were hard to tell apart.
 */
public class StationHeader {
	public static JLabel make(String text) {
		JLabel label = new JLabel(text, SwingConstants.CENTER);
		label.setFont(label.getFont().deriveFont(Font.BOLD, 16f));
		label.setOpaque(true);
		label.setBackground(new Color(30, 40, 60));
		label.setForeground(Color.WHITE);
		label.setBorder(new EmptyBorder(6, 6, 6, 6));
		return label;
	}
}
