package org.gicci.volleyball.model;

public class ScoreTable {

	private Integer period;
	private Integer homeScore;
	private Integer visitorScore;
	private Integer homeGame;
	private Integer visitorGame;
	
	public ScoreTable() {}

	public ScoreTable(Integer period, Integer homeScore, Integer visitorScore) {
		this.period = period;
		this.homeScore = homeScore;
		this.visitorScore = visitorScore;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public Integer getHomeScore() {
		return homeScore;
	}

	public void setHomeScore(Integer homeScore) {
		this.homeScore = homeScore;
	}

	public Integer getVisitorScore() {
		return visitorScore;
	}

	public void setVisitorScore(Integer visitorScore) {
		this.visitorScore = visitorScore;
	}

	public Integer getHomeGame() {
		return homeGame;
	}

	public void setHomeGame(Integer homeGame) {
		this.homeGame = homeGame;
	}

	public Integer getVisitorGame() {
		return visitorGame;
	}

	public void setVisitGame(Integer visitorGame) {
		this.visitorGame = visitorGame;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((period == null) ? 0 : period.hashCode());
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
		ScoreTable other = (ScoreTable) obj;
		if (period == null) {
			if (other.period != null)
				return false;
		} else if (!period.equals(other.period))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ScoreTable [period=" + period + ", home=" + homeScore + ", visitor=" + visitorScore
				+ ", home game=" + homeGame + ", visitor game=" + visitorGame + "]";
	}
}
