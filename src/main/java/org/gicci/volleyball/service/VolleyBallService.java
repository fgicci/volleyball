package org.gicci.volleyball.service;

import java.util.List;

import org.gicci.volleyball.model.ScoreTable;
import org.gicci.volleyball.model.Team;

public interface VolleyBallService {

	public void setup(Team home, Team visitor, Integer periods, Integer maxGameSetScore, Integer maxGameSetDecisionScore);
	public String getHomeName();
	public String getVisitorName();
	public void scoreToHomeTeam(Integer value);
	public void scoreToVisitorTeam(Integer value);
	public boolean isGameEnd();
	public void closeGameSet();
	public boolean isMatchEnd();
	public boolean isTieBreak();
	public List<ScoreTable> getResults();
	public Team getWinner();
	public Integer getCurrentGameSet();
	public Integer getCurrentHomeScore();
	public Integer getCurrentVisitorScore();
	
}