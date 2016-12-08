package org.gicci.volleyball.app;

import javax.swing.JFrame;

import org.gicci.volleyball.gui.VolleyBallWindowFrame;

public class VolleyBallApp {

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(() -> {
			VolleyBallWindowFrame frame = new VolleyBallWindowFrame();
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
	}
}
