package org.gicci.volleyball.model;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoard {

	private Team home;
	private Team visitor;
	private Integer currentPeriod;
	private List<ScoreTable> scoretables;
	
	public ScoreBoard() {
		this.home = new Team("HOM", "Home");
		this.visitor = new Team("VIS", "Visitors");
		this.scoretables = new ArrayList<ScoreTable>();
	}
	
	public ScoreBoard(Team home, Team visitor) {
		this.home = home;
		this.visitor = visitor;
	}

	public Team getHome() {
		return home;
	}

	public void setHome(Team home) {
		this.home = home;
	}

	public Team getVisitor() {
		return visitor;
	}

	public void setVisitor(Team visitor) {
		this.visitor = visitor;
	}
	
	public List<ScoreTable> getScoretables() {
		return scoretables;
	}

	public void setScoretables(List<ScoreTable> scoretables) {
		this.scoretables = scoretables;
	}

	public ScoreTable addScoreTable(ScoreTable scoreTable) {
		getScoretables().add(scoreTable);
		return scoreTable;
	}
	
	public ScoreTable removeScoreTable(ScoreTable scoreTable) {
		if (this.currentPeriod == scoreTable.getPeriod() && this.currentPeriod == getScoretables().size()) this.currentPeriod--;
		getScoretables().remove(scoreTable);
		return null;
	}
	
	public void setCurrentPeriod(Integer currentPeriod) {
		this.currentPeriod = currentPeriod;
	}

	public Integer getCurrentPeriod() {
		return this.currentPeriod;
	}
	
	public void increasePeriod() {
		this.currentPeriod++;
	}
	
	public Integer getScoreDifference() {
		Integer difference = getScoretables().get(this.currentPeriod).getHomeScore() - getScoretables().get(this.currentPeriod).getVisitorScore();
		return ((difference < 0) ? -difference : difference);
	}
	
	public Integer getCurrentHomeScore() {
		return getScoretables().get(this.currentPeriod).getHomeScore();
	}
	
	public Integer getCurrentVisitorScore() {
		return getScoretables().get(this.currentPeriod).getVisitorScore();
	}
	
	public Integer getCurrentHomeGame() {
		return getScoretables().get(this.currentPeriod).getHomeGame();
	}
	
	public Integer getCurrentVisitorGame() {
		return getScoretables().get(this.currentPeriod).getVisitorGame();
	}
	
	public Integer getNumberOfGameSet() {
		return getScoretables().size();
	}
}
