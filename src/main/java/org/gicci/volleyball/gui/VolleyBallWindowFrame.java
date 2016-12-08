package org.gicci.volleyball.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker.StateValue;

import org.gicci.volleyball.model.Team;
import org.gicci.volleyball.service.VolleyBallService;
import org.gicci.volleyball.service.VolleyBallServiceManager;
import org.gicci.volleyball.thread.MatchExecutor;

public class VolleyBallWindowFrame extends JFrame implements ActionListener, PropertyChangeListener {

	private static final long serialVersionUID = -3424711476025777917L;
	private JPanel panel;
	private JLabel lblHome, lblVisitor, lblNumGames, lblNumPoint, lblDecidePoint;
	private JTextField txtHome, txtVisitor, txtNumGames, txtNumPoint, txtDecidePoint;
	private JTextArea txtPrompt;
	private JScrollPane scrollpane;
	private JButton btnSimulate, btnAbout;
	private MatchExecutor match;
	
	/**
	 * Constructor
	 * 
	 * Setup the frame properties.
	 */
	public VolleyBallWindowFrame() {
		super("Volley Ball Simulation");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int INSET = 150;
        setBounds(INSET * 2, INSET,
                  screenSize.width  - INSET * 4,
                  screenSize.height - INSET * 2);
        initComponents();
        add(panel);
	}
	
	/**
	 * Initialize the Frame components and layout manager.
	 */
	private void initComponents() {
		panel = new JPanel();
		lblHome = new JLabel("Home Team: ");
		lblVisitor = new JLabel("Visitor: ");
		lblNumGames = new JLabel("Games Set");
		lblNumPoint = new JLabel("Max Point");
		lblDecidePoint = new JLabel("Tie Break Point");
		txtHome = new JTextField(30);
		txtVisitor = new JTextField(30);
		txtNumGames = new JTextField(10);
		txtNumGames.setText("3");
		txtNumPoint = new JTextField(10);
		txtNumPoint.setText("21");
		txtDecidePoint = new JTextField(10);
		txtDecidePoint.setText("15");
		txtPrompt = new JTextArea();
		txtPrompt.setEditable(false);
		txtPrompt.setAutoscrolls(true);
		scrollpane = new JScrollPane(txtPrompt);
		btnSimulate = new JButton("Start");
		btnSimulate.addActionListener(this);
		btnAbout = new JButton("About");
		btnAbout.addActionListener(this);
		
		GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup()
            	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        		.addGroup(layout.createSequentialGroup()
	            		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            			.addComponent(lblHome)
	            			.addComponent(lblVisitor)
	            		)
	            		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            			.addComponent(txtHome)
	            			.addComponent(txtVisitor)
	            		)
	        		)
        		)
            	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        		.addGroup(layout.createSequentialGroup()
	    				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            			.addComponent(lblNumGames)
	            			.addComponent(txtNumGames)
	               		)
	                	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            			.addComponent(lblNumPoint)
	            			.addComponent(txtNumPoint)
	                	)
	                	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            			.addComponent(lblDecidePoint)
	            			.addComponent(txtDecidePoint)
	                    )
	        		)
        		)
                .addComponent(scrollpane)
            )
            .addGroup(layout.createParallelGroup()
            	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)    
            		.addComponent(btnSimulate)
            		.addComponent(btnAbout)
            	)
            )
        );
        
        layout.linkSize(SwingConstants.HORIZONTAL, btnSimulate, btnAbout);
        
        layout.setVerticalGroup(layout.createSequentialGroup()  
            .addGroup(layout.createSequentialGroup()    
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHome)
                    .addComponent(txtHome)
                    .addComponent(btnSimulate)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblVisitor)
                    .addComponent(txtVisitor)
                    .addComponent(btnAbout)
                )
                .addGroup(layout.createSequentialGroup()
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNumGames)
                        .addComponent(lblNumPoint)
                        .addComponent(lblDecidePoint)
	                )
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNumGames)
                        .addComponent(txtNumPoint)
                        .addComponent(txtDecidePoint)
	                )
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(scrollpane)
                )
            )        
        );
	}
	
	/**
	 * Select the ActionEvent to perform the button process.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnSimulate)) performSimulateButton();
		if (e.getSource().equals(btnAbout)) performAboutButton();
	}
	
	/**
	 * 
	 * Perform Simulate Button.
	 * 
	 * Rules - All text fields must be filled.
	 */
	private void performSimulateButton() throws NumberFormatException {
		if (txtHome.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Enter Home team name!", "Error", JOptionPane.ERROR_MESSAGE);
		} else if (txtVisitor.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Enter Visitor team name!", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				processTheGame();
			} catch(IllegalArgumentException ex) {
				JOptionPane.showMessageDialog(this, "Invalid param! \n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**
	 * 
	 * Perform the VolleyBall Game.
	 */
	private void processTheGame() {
		Integer gameSetValue = Integer.parseInt(txtNumGames.getText());
		Integer maxPointValue = Integer.parseInt(txtNumPoint.getText());
		Integer tieBreakValue = Integer.parseInt(txtDecidePoint.getText());
		
		if (gameSetValue < 0 || maxPointValue < 0 || tieBreakValue < 0) throw new IllegalArgumentException("Negative number");
		if (gameSetValue < 1 || maxPointValue < 1 || tieBreakValue < 1) throw new IllegalArgumentException("Zero value number");
		
		VolleyBallService volleyBallService = new VolleyBallServiceManager();
		volleyBallService.setup(new Team("", txtHome.getText()), new Team("", txtVisitor.getText()), gameSetValue, maxPointValue, tieBreakValue);
		
		match = new MatchExecutor(volleyBallService) {
			@Override
			protected void process(List<String> chunks) {
				for (final String msg : chunks) {
					txtPrompt.append(msg);
					txtPrompt.append("\n");
				}
			}
		};
		txtPrompt.setText("");
		txtPrompt.requestFocusInWindow();
		match.addPropertyChangeListener(this);
		match.execute();
	}
		
	/**
	 * 
	 * Perform About Button.
	 */
	private void performAboutButton() {
		JOptionPane.showMessageDialog(this, this.getTitle() + "\n" +
                					  "Author: Fernando Gicci Hernandes\n" + 
                					  "Date: 08/12/2016\n" +
                					  "Version: 1.00\n", 
                					  "About", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Control the Thread state value.
	 * 
	 * Rules - Set simulate button disabled when the process is running.
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		switch (evt.getPropertyName()) {
			case "state":
				switch ((StateValue) evt.getNewValue()) {
					case STARTED: this.btnSimulate.setEnabled(false); break;
					case PENDING: 
					case DONE: this.btnSimulate.setEnabled(true); break;
				}
		}
	}

}
