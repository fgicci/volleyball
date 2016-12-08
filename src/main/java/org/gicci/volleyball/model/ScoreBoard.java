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
}
