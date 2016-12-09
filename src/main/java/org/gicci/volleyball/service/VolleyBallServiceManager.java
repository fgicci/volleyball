package org.gicci.volleyball.service;

import java.util.List;

import org.gicci.volleyball.model.ScoreBoard;
import org.gicci.volleyball.model.ScoreTable;
import org.gicci.volleyball.model.Team;

public class VolleyBallServiceManager implements VolleyBallService {

	private final int MAX_SCORE_DIF = 2;
	private Integer remainingGameSet;
	private Integer maxGameSetScore;
	private Integer maxGameSetDecisionScore;
	private ScoreBoard scoreBoard;

	public VolleyBallServiceManager() {}
	
	@Override
	public void setup(Team home, Team visitor, Integer periods, Integer maxGameSetScore, Integer maxGameSetDecisionScore) {
		if (periods % 2 == 0) periods++;
		this.scoreBoard = new ScoreBoard(home, visitor);
		for (int i = 0; i < periods; i++) {
			ScoreTable scoreTable = new ScoreTable(i, 0, 0, 0, 0);
			scoreBoard.addScoreTable(scoreTable);
		}
		this.scoreBoard.setCurrentPeriod(0);
		this.remainingGameSet = scoreBoard.getNumberOfGameSet() / 2;
		this.maxGameSetScore = maxGameSetScore;
		this.maxGameSetDecisionScore = maxGameSetDecisionScore;
		System.out.println(scoreBoard);
	}

	@Override
	public String getHomeName() {
		return this.scoreBoard.getHome().toString();
	}
	
	@Override
	public String getVisitorName() {
		return this.scoreBoard.getVisitor().toString();
	}
	
	@Override
	public void scoreToHomeTeam(Integer value) {
		ScoreTable scoreTable = this.scoreBoard.getScoretables().get(this.scoreBoard.getCurrentPeriod());
		scoreTable.setHomeScore(scoreTable.getHomeScore() + value);
	}

	@Override
	public void scoreToVisitorTeam(Integer value) {
		ScoreTable scoreTable = this.scoreBoard.getScoretables().get(this.scoreBoard.getCurrentPeriod());
		scoreTable.setVisitorScore(scoreTable.getVisitorScore() + value);
	}
	
	@Override
	public Integer getCurrentHomeScore() {
		ScoreTable scoreTable = this.scoreBoard.getScoretables().get(this.scoreBoard.getCurrentPeriod());
		return scoreTable.getHomeScore();
	}

	@Override
	public Integer getCurrentVisitorScore() {
		ScoreTable scoreTable = this.scoreBoard.getScoretables().get(this.scoreBoard.getCurrentPeriod());
		return scoreTable.getVisitorScore();
	}

	@Override
	public List<ScoreTable> getResults() {
		return this.scoreBoard.getScoretables();
	}

	@Override
	public boolean isGameEnd() {
		Integer currentHomeScore = scoreBoard.getCurrentHomeScore();
		Integer currentVisitorScore = scoreBoard.getCurrentVisitorScore();
		Integer maxScore = getCurrentGameSet() == scoreBoard.getNumberOfGameSet() ? this.maxGameSetScore : this.maxGameSetDecisionScore;
		
		return ((currentHomeScore == maxScore || currentVisitorScore == maxScore) && // One of teams reach the max score
				(currentHomeScore < maxScore || currentVisitorScore < maxScore) &&	// One of teams does not over the max score
				(scoreBoard.getScoreDifference() >= MAX_SCORE_DIF) // The difference of scores between the teams is greater and equal then max score difference 
				|| // OR
				(currentHomeScore > maxScore || currentVisitorScore > maxScore) &&  // One of teams reach more then the max score
				(scoreBoard.getScoreDifference() >= MAX_SCORE_DIF)); // The difference of scores between the teams is greater and equal then max score difference
	}

	@Override
	public boolean isMatchEnd() {
		Integer currentPeriod = scoreBoard.getCurrentPeriod();
		
		int maxToWinTheGame = (scoreBoard.getNumberOfGameSet() - 1) / 2; // get the number of the last game set.
		if (currentPeriod > this.remainingGameSet) this.remainingGameSet--; // Reduce the remaning sets to finish the match.
		
		return (currentPeriod > maxToWinTheGame && scoreBoard.getScoreDifference() > remainingGameSet) 
				||
				currentPeriod > scoreBoard.getNumberOfGameSet();
	}

	@Override
	public boolean isTieBreak() {
		return getCurrentGameSet() == scoreBoard.getNumberOfGameSet();
	}

	@Override
	public Team getWinner() {
		Integer totalOfHomeSetScore = 0;
		Integer totalOfVisitorSetScore = 0;
		for (ScoreTable scoreTable : scoreBoard.getScoretables()) {
			totalOfHomeSetScore += scoreTable.getHomeGame();
			totalOfVisitorSetScore += scoreTable.getVisitorGame();
		}
		System.out.println("totalOfHomeSetScore -> " + totalOfHomeSetScore);
		System.out.println("totalOfVisitorSetScore -> " + totalOfVisitorSetScore);
		return totalOfHomeSetScore > totalOfVisitorSetScore ? scoreBoard.getHome() : scoreBoard.getVisitor();
	}

	@Override
	public Integer getCurrentGameSet() {
		return scoreBoard.getCurrentPeriod() + 1;
	}

	@Override
	public void closeGameSet() {
		Integer currentHomeScore = scoreBoard.getCurrentHomeScore();
		Integer currentVisitorScore = scoreBoard.getCurrentVisitorScore();
		if (currentHomeScore > currentVisitorScore) { 
			ScoreTable scoreTable = this.scoreBoard.getScoretables().get(this.scoreBoard.getCurrentPeriod());
			scoreTable.setHomeGame(1);
		} else {
			ScoreTable scoreTable = this.scoreBoard.getScoretables().get(this.scoreBoard.getCurrentPeriod());
			scoreTable.setVisitorGame(1);
		}
		scoreBoard.increasePeriod();
	}
}
