package model;
/****************************************************************************************************
* Project: ClubHub
* Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
* Student Number: 100563954, 100911472, 100898311
* Date: March 15, 2016
* Description: Slot model
****************************************************************************************************/
public class Slot {
	private String id, dayOfWeek, week, gender, players, scheduledDate;
	String time;
	private int status;
	
	public String getPlayers(){
		return players;
	}
	
	public void setPlayers(String players){
		this.players = players;
	}
	
	public String getscheduledDate(){
		return scheduledDate;
	}
	
	public void setScheduledDate(String scheduledDate){
		this.scheduledDate = scheduledDate;
	}
	
	public String getDayOfWeek(){
		return dayOfWeek;
	}
	
	public void setDayOfWeek(String dayOfWeek){
		this.dayOfWeek = dayOfWeek;
	}
	
	public String getWeek() {
		return week;
	}
	
	public void setWeek(String week) {
		this.week = week;
	}

	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}