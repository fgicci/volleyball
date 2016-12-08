package org.gicci.volleyball.service;

import java.util.List;

import org.gicci.volleyball.model.ScoreBoard;
import org.gicci.volleyball.model.ScoreTable;
import org.gicci.volleyball.model.Team;

public class VolleyBallServiceManager implements VolleyBallService {

	private final int MAX_SCORE_DIF = 2;
	private ScoreBoard scoreBoard;
	
	@Override
	public void init(Team home, Team visitor, Integer periods) {
		this.scoreBoard = new ScoreBoard(home, visitor);
		for (int i = 1; i <= periods; i++) {
			ScoreTable scoreTable = new ScoreTable(i, 0, 0);
			scoreBoard.addScoreTable(scoreTable);
		}
		this.scoreBoard.setCurrentPeriod(1);
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
	public List<ScoreTable> getResults() {
		return this.scoreBoard.getScoretables();
	}

	@Override
	public boolean isGameEnd() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isMatchEnd() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTieBreak() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Team getWinner() {
		Integer totalOfHomeSetScore = 0;
		Integer totalOfVisitorSetScore = 0;
		for (ScoreTable scoreTable : scoreBoard.getScoretables()) {
			totalOfHomeSetScore = scoreTable.getHomeGame();
			totalOfVisitorSetScore = scoreTable.getVisitorGame();
		}
		return totalOfHomeSetScore > totalOfVisitorSetScore ? scoreBoard.getHome() : scoreBoard.getVisitor();
	}

}
