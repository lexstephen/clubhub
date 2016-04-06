package model;
/****************************************************************************************************
* Project: ClubHub
* Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
* Student Number: 100563954, 100911472, 100898311
* Date: March 06, 2016
* Description: Game model
****************************************************************************************************/
public class UserGame {
	private String userID, gameID, team, score, seasonID, outcome;

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public String getSeasonID() {
		return seasonID;
	}

	public void setSeasonID(String seasonID) {
		this.seasonID = seasonID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getGameID() {
		return gameID;
	}

	public void setGameID(String gameID) {
		this.gameID = gameID;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	} 
	
	
}