package model;
/****************************************************************************************************
* Project: ClubHub
* Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
* Student Number: 100563954, 100911472, 100898311
* Date: March 15, 2016
* Description: Slot model
****************************************************************************************************/
public class Slot {
	private String id, scheduledDate, week, seasonId;
	
	
	public String getWeek() {
		return week;
	}
	
	public void setWeek(String week) {
		this.week = week;
	}

	public String getScheduledDate() {
		return scheduledDate;
	}
	
	public void setScheduledDate(String scheduledDate) {
		this.scheduledDate = scheduledDate;
	}
	
	public String getSeasonId() {
		return seasonId;
	}
	
	public void setSeasonId(String seasonId) {
		this.seasonId = seasonId;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}