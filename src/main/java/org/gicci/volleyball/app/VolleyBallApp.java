package org.gicci.volleyball.app;

import javax.swing.JFrame;

import org.gicci.volleyball.gui.VolleyBallFrame;

public class VolleyBallApp {

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			VolleyBallFrame frame = new VolleyBallFrame();

			@Override
			public void run() {
				frame.setVisible(true);
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
            
        });
	}

}
