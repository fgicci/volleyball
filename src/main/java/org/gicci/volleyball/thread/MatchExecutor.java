package org.gicci.volleyball.thread;

import java.util.Random;

import javax.swing.SwingWorker;

import org.gicci.volleyball.service.VolleyBallService;

public class MatchExecutor extends SwingWorker<Void, String> {

	private Boolean homeScored;
	private Boolean visitorScored;
	private VolleyBallService volleyBallService;
	private StringBuilder strbuilder;
	
	public MatchExecutor(VolleyBallService volleyBallService) {
		this.volleyBallService = volleyBallService;
		this.strbuilder = new StringBuilder();
	}
	
	@Override
	protected Void doInBackground() throws Exception {
		displayInitialMessages();
		do {
			this.strbuilder = new StringBuilder();
			strbuilder.append("\nGame Set: " + volleyBallService.getCurrentGameSet());
			if (volleyBallService.isTieBreak()) strbuilder.append(" - Tie Break!!!");
			publish(strbuilder.toString() + "\n");
			
			if (homeStarts()) processHomeServing();
			else processVisitorServing();
			
			do {
				Thread.sleep(500);
				if (isHomeScored()) processHomeServing();
				else if (isVisitorScored()) processVisitorServing();
			} while (!volleyBallService.isGameEnd());
			
			volleyBallService.closeGameSet(); 
			
		} while (!volleyBallService.isMatchEnd());
		
		return null;
	}

	/**
	 * Gives the result match at the end process.
	 */
	protected void done() {
		this.strbuilder = new StringBuilder();
		strbuilder.append("Match End! "); 
		strbuilder.append(volleyBallService.getWinner().toString());
		strbuilder.append(" won!\n"); // + volleyset.getTotalHomeScore() + "-" + volleyset.getTotalVisitorScore() + " (");
		/**
		for (int index = 0; index < volleyset.getTotalSetGames(); index++) {
			strbuilder.addMessage(volleyset.getHomeScore(index) + "-" + volleyset.getVisitorScore(index));
			if (index < volleyset.getTotalSetGames() - 1) strbuilder.append(",");
		}
		strbuilder.append(")");
		*/
		publish(strbuilder.toString());
	}
	
	/**
	 * Verify if the home team starts the game serving
	 * 
	 * @return True or False
	 */
	private Boolean homeStarts() {
		Random random = new Random();
		return random.nextBoolean();
	}
	
	/**
	 * Verify if the home team starts the game serving
	 * 
	 * @return True or False
	 */
	private Boolean serve() {
		Random random = new Random();
		return random.nextBoolean();
	}
	
	/**
	 * Process serving by Home team.
	 */
	private void processHomeServing() {
		this.strbuilder = new StringBuilder();
		strbuilder.append(volleyBallService.getHomeName() + " serving...");
		Boolean servingResult = serve();
		if (servingResult) strbuilder.append("Point!\n");
		else strbuilder.append("Fail!\n");
		setHomeScored(servingResult);
		publishCurrentScore();
	}
	
	/**
	 * Process serving by Visitor team.
	 */
	private void processVisitorServing() {
		this.strbuilder = new StringBuilder();
		strbuilder.append(volleyBallService.getVisitorName() + " serving...");
		Boolean servingResult = serve();
		if (servingResult) strbuilder.append("Point!\n");
		else strbuilder.append(" Fail!\n");
		setVisitorScored(servingResult);
		publishCurrentScore();
	}
	
	private void publishCurrentScore() {
		this.strbuilder.append(volleyBallService.getCurrentHomeScore() + " - " + volleyBallService.getCurrentVisitorScore());
		publish(this.strbuilder.toString());
	}
	/**
	 * Verify if the home team did the point after the serve
	 * 
	 * @return True or False
	 */
	private Boolean isHomeScored() {
		return this.homeScored;
	}

	/**
	 * Apply the result of point. If true, set the score to home team, else, set the score to the visitor team
	 * 
	 * @param Scored result
	 */
	private void setHomeScored(Boolean homescored) {
		this.strbuilder = new StringBuilder();
		if (homescored) {
			volleyBallService.scoreToHomeTeam(1);
			strbuilder.append("Point for " + volleyBallService.getHomeName() + "!\n");
		//	this.volleyset.addHomeScore(this.volleyset.getCurrentGameSet(), this.home.getScore());
		} else {
			volleyBallService.scoreToVisitorTeam(1);
			strbuilder.append("Point for " + volleyBallService.getVisitorName() + "!\n");
		//	this.volleyset.addVisitorScore(this.volleyset.getCurrentGameSet(), this.visitor.getScore());
		}
		this.homeScored = homescored;
		this.visitorScored = !homescored;
		// strbuilder.append(" -> " + volleyBallService.getVisitorName() + " " + volleyBallService. + " - " + visitor.getScore() + " " + volleyBallService.getVisitorName() + " ");
	}

	/**
	 * Verify if the visitor team did the point after the serve
	 * 
	 * @return True or False
	 */
	private Boolean isVisitorScored() {
		return visitorScored;
	}

	/**
	 * Apply the result of point. If true, set the score to visitor team, else, set the score to the home team
	 * 
	 * @param Scored result
	 */
	private void setVisitorScored(Boolean visitorscored) {
		this.strbuilder = new StringBuilder();
		if (visitorscored) {
			volleyBallService.scoreToVisitorTeam(1);
			strbuilder.append("Point for " + volleyBallService.getVisitorName() + "!\n");
		//	this.volleyset.addVisitorScore(this.volleyset.getCurrentGameSet(), this.visitor.getScore());
		} else {
			volleyBallService.scoreToHomeTeam(1);
			strbuilder.append("Point for " + volleyBallService.getHomeName() + "!\n");
		//	this.volleyset.addHomeScore(this.volleyset.getCurrentGameSet(), this.home.getScore());
		}
		this.visitorScored = visitorscored;
		this.homeScored = !visitorscored;
		// strbuilder.append(" -> " + home.getName() + " " + home.getScore() + " - " + visitor.getScore() + " " + visitor.getName() + " ");
	}
	/**
	 * Display initial message
	 */	
	private void displayInitialMessages() {
		this.strbuilder = new StringBuilder();
		strbuilder.append("Here is the " + volleyBallService.getHomeName() + "!!!\n");
		strbuilder.append("Here is the " + volleyBallService.getVisitorName() + "!!!\n");
		strbuilder.append("Starting match: " + volleyBallService.getHomeName() + " vs " + volleyBallService.getVisitorName());
		publish(strbuilder.toString());
	}
}
