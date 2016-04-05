package model;
/****************************************************************************************************
* Project: ClubHub
* Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
* Student Number: 100563954, 100911472, 100898311
* Date: March 06, 2016
* Description: Game model
****************************************************************************************************/
public class Game {
	private String id, gameid, scheduledDate, week, seasonId; 
	private int schedMonth;
	private String year, season, gender, startDate, startTime, dayOfWeek, duration;
	private String scheduledDateFullYear;
	
	

	public String getScheduledDateFullYear() {
		return scheduledDateFullYear;
	}

	public void setScheduledDateFullYear(String scheduledDateFullYear) {
		this.scheduledDateFullYear = scheduledDateFullYear;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getGameid() {
		return gameid;
	}

	public void setGameid(String gameid) {
		this.gameid = gameid;
	}

	
	public int getSchedMonth() {
		return schedMonth;
	}

	public void setSchedMonth(int i) {
		this.schedMonth = i;
	}

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