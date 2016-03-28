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
	private String image_logo, image_small_logo, featured_image_01, featured_image_02, featured_image_03, featured_image_04, featured_image_05;
	private int featured_images;
	public int getFeatured_images() {
		return featured_images;
	}
	public void setFeatured_images(int featured_images) {
		this.featured_images = featured_images;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	private String featured_image_06, featured_image_07, featured_image_08, featured_image_09, featured_image_10;
	private int colour_schemeid;
	private float tax_rate;
	
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
	public String getFeatured_image_01() {
		return featured_image_01;
	}
	public void setFeatured_image_01(String featured_image_01) {
		this.featured_image_01 = featured_image_01;
	}
	public String getFeatured_image_02() {
		return featured_image_02;
	}
	public void setFeatured_image_02(String featured_image_02) {
		this.featured_image_02 = featured_image_02;
	}
	public String getFeatured_image_03() {
		return featured_image_03;
	}
	public void setFeatured_image_03(String featured_image_03) {
		this.featured_image_03 = featured_image_03;
	}
	public String getFeatured_image_04() {
		return featured_image_04;
	}
	public void setFeatured_image_04(String featured_image_04) {
		this.featured_image_04 = featured_image_04;
	}
	public String getFeatured_image_05() {
		return featured_image_05;
	}
	public void setFeatured_image_05(String featured_image_05) {
		this.featured_image_05 = featured_image_05;
	}
	public String getFeatured_image_06() {
		return featured_image_06;
	}
	public void setFeatured_image_06(String featured_image_06) {
		this.featured_image_06 = featured_image_06;
	}
	public String getFeatured_image_07() {
		return featured_image_07;
	}
	public void setFeatured_image_07(String featured_image_07) {
		this.featured_image_07 = featured_image_07;
	}
	public String getFeatured_image_08() {
		return featured_image_08;
	}
	public void setFeatured_image_08(String featured_image_08) {
		this.featured_image_08 = featured_image_08;
	}
	public String getFeatured_image_09() {
		return featured_image_09;
	}
	public void setFeatured_image_09(String featured_image_09) {
		this.featured_image_09 = featured_image_09;
	}
	public String getFeatured_image_10() {
		return featured_image_10;
	}
	public void setFeatured_image_10(String featured_image_10) {
		this.featured_image_10 = featured_image_10;
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
