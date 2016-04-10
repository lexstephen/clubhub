package model;
/****************************************************************************************************
* Project: ClubHub
* Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
* Student Number: 100563954, 100911472, 100898311
* Date: March 15, 2016
* Description: Slot model
****************************************************************************************************/
public class Slot {
	private String id, dayOfWeek, week, gender, players, scheduledDate, gameID, seasonName;
	private String time, year, scheduledDateWithYear;
	private int status, conflict;
	
	public String getScheduledDateWithYear() {
		return scheduledDateWithYear;
	}

	public void setScheduledDateWithYear(String scheduledDateWithYear) {
		this.scheduledDateWithYear = scheduledDateWithYear;
	}

	public String  getYear() {
		return year;
	}

	public void setYear(String  year) {
		this.year = year;
	}

	public String getSeasonName() {
		return seasonName;
	}

	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
	}

	public int getConflict() {
		return conflict;
	}

	public void setConflict(int conflict) {
		this.conflict = conflict;
	}

	public String getScheduledDate() {
		return scheduledDate;
	}

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
	
	public String getGameID() {
		return gameID;
	}

	public void setGameID(String gameID) {
		this.gameID = gameID;
	}


	@Override
	public boolean equals(Object obj) {
	Slot other = (Slot) obj;
	    if(this.id.equals(other.id)){
	          return true;
	    }else{
	        return false;
	    }
	}

	public boolean equalsConflict(Object obj) {
	Slot other = (Slot) obj;
	    if(this.id.equals(other.id) && (this.conflict == other.conflict)){
	          return true;
	    }else{
	        return false;
	    }
	}

	public boolean sameSlotDifferentConflict(Slot other) {
        /* if(!this.id.equals(other.id))
            return false; */
		System.out.println("I compare the slots and find id " + this.id + "|" + other.id + " and conflict " + this.conflict + "|" + other.conflict);
		System.out.println("That's a " + this.id.equals(other.id) + " and " + (this.conflict != other.conflict));
        return (this.id.equals(other.id) && (this.conflict != other.conflict));
    }

}