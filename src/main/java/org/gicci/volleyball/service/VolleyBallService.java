package org.gicci.volleyball.service;

import java.util.List;

import org.gicci.volleyball.model.ScoreTable;
import org.gicci.volleyball.model.Team;

public interface VolleyBallService {

	public void init(Team home, Team visitor, Integer periods);
	public void scoreToHomeTeam(Integer value);
	public void scoreToVisitorTeam(Integer value);
	public boolean isGameEnd();
	public boolean isMatchEnd();
	public boolean isTieBreak();
	public List<ScoreTable> getResults();
	public Team getWinner();
	
}
