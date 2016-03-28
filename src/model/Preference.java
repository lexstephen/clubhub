package model;
/****************************************************************************************************
* Project: ClubHub
* Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
* Student Number: 100563954, 100911472, 100898311
* Date: Feb 03, 2016
* Description: Preference model
****************************************************************************************************/
public class Preference {
	private String id, preference_name, club_name_long, club_name_short, status, country;
	private String image_logo, image_small_logo;
	private int colour_schemeid;
	private float tax_rate;

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPreference_name() {
		return preference_name;
	}
	public void setPreference_name(String preference_name) {
		this.preference_name = preference_name;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImage_logo() {
		return image_logo;
	}
	public void setImage_logo(String image_logo) {
		this.image_logo = image_logo;
	}
	public String getImage_small_logo() {
		return image_small_logo;
	}
	public void setImage_small_logo(String image_small_logo) {
		this.image_small_logo = image_small_logo;
	}
	public String getClub_name_long() {
		return club_name_long;
	}
	public void setClub_name_long(String club_name_long) {
		this.club_name_long = club_name_long;
	}
	public String getClub_name_short() {
		return club_name_short;
	}
	public void setClub_name_short(String club_name_short) {
		this.club_name_short = club_name_short;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getColour_schemeid() {
		return colour_schemeid;
	}
	public void setColour_schemeid(int colour_schemeid) {
		this.colour_schemeid = colour_schemeid;
	}
	public float getTax_rate() {
		return tax_rate;
	}
	public void setTax_rate(float tax_rate) {
		this.tax_rate = tax_rate;
	}
}
