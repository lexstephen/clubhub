package model;
/****************************************************************************************************
* Project: ClubHub
* Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
* Student Number: 100563954, 100911472, 100898311
* Date: Feb 03, 2016
* Description: Preference model
****************************************************************************************************/
public class Preference {
	private int colour_schemeid;
	private String id, preference_name, club_name_long, club_name_short, clubURL, status;
	private String image_logo, image_small_logo;
	private float tax_rate;
	private String telephone, formatted_telephone, address, city, country, province, postalcode, contactName, emailAddress;

	public String getClubURL() {
		return clubURL;
	}
	public void setClubURL(String clubURL) {
		this.clubURL = clubURL;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getFormatted_telephone() {
		return formatted_telephone;
	}
	public void setFormatted_telephone(String formatted_telephone) {
		this.formatted_telephone = formatted_telephone;
	}
	public String getAddress() {
		return address;
	}
	public String getCity() {
		return city;
	}
	public String getClub_name_long() {
		return club_name_long;
	}
	public String getClub_name_short() {
		return club_name_short;
	}
	public int getColour_schemeid() {
		return colour_schemeid;
	}
	public String getCountry() {
		return country;
	}
	public String getId() {
		return id;
	}
	public String getImage_logo() {
		return image_logo;
	}
	public String getImage_small_logo() {
		return image_small_logo;
	}
	public String getPostalcode() {
		return postalcode;
	}
	public String getPreference_name() {
		return preference_name;
	}
	public String getProvince() {
		return province;
	}
	public String getStatus() {
		return status;
	}
	public float getTax_rate() {
		return tax_rate;
	}
	
	public String getTelephone() {
		return telephone;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setClub_name_long(String club_name_long) {
		this.club_name_long = club_name_long;
	}
	public void setClub_name_short(String club_name_short) {
		this.club_name_short = club_name_short;
	}
	public void setColour_schemeid(int colour_schemeid) {
		this.colour_schemeid = colour_schemeid;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setImage_logo(String image_logo) {
		this.image_logo = image_logo;
	}
	public void setImage_small_logo(String image_small_logo) {
		this.image_small_logo = image_small_logo;
	}
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}
	public void setPreference_name(String preference_name) {
		this.preference_name = preference_name;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setTax_rate(float tax_rate) {
		this.tax_rate = tax_rate;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
}
