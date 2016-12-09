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
		initScoreTable();
	}
	
	public ScoreBoard(Team home, Team visitor) {
		this.home = home;
		this.visitor = visitor;
		initScoreTable();
	}

	private void initScoreTable() {
		this.scoretables = new ArrayList<ScoreTable>();
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currentPeriod == null) ? 0 : currentPeriod.hashCode());
		result = prime * result + ((home == null) ? 0 : home.hashCode());
		result = prime * result + ((scoretables == null) ? 0 : scoretables.hashCode());
		result = prime * result + ((visitor == null) ? 0 : visitor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScoreBoard other = (ScoreBoard) obj;
		if (currentPeriod == null) {
			if (other.currentPeriod != null)
				return false;
		} else if (!currentPeriod.equals(other.currentPeriod))
			return false;
		if (home == null) {
			if (other.home != null)
				return false;
		} else if (!home.equals(other.home))
			return false;
		if (scoretables == null) {
			if (other.scoretables != null)
				return false;
		} else if (!scoretables.equals(other.scoretables))
			return false;
		if (visitor == null) {
			if (other.visitor != null)
				return false;
		} else if (!visitor.equals(other.visitor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ScoreBoard [home=" + home + ", visitor=" + visitor + ", currentPeriod=" + currentPeriod
				+ ", scoretables=" + scoretables + "]";
	}
}
