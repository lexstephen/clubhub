package model;

/****************************************************************************************************
* Project: ClubHub
* Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
* Student Number: 100563954, 100911472, 100898311
* Date: Feb 03, 2016
* Description: Post model
****************************************************************************************************/
public class Post {
	private String id, title, content, content_short, Userid, userFirstName, userLastName, postType, accessLevel, category, postDate;
	private boolean postMatchUser;
	
	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	
	public void setPostMatchUser(boolean postMatchUser) {
		this.postMatchUser = postMatchUser;
	}

	public boolean isPostMatchUser() {
		return postMatchUser;
	}

	public boolean postMatchUser(String loggedInId) {
		return (Userid == loggedInId);
	}

	public String getContent_short() {
		int n = 250;
		content_short = content.substring(0, Math.min(content.length(), n));     // change 20 to something bigger
		if (content_short.length() > n-3)
			content_short += "...";
		return content_short;
	}

	public String getUsername() {
		return userFirstName + " " + userLastName;
	}
	
	public String getPostType() {
		return postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
	}

	public String getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserid() {
		return Userid;
	}

	public void setUserid(String userid) {
		Userid = userid;
	}	
}
