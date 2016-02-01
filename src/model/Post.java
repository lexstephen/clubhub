package model;
/****************************************************************************************************
* Project: Hackers 1995
* Assignment: COMP 3095 Assignment 2
* Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
* Student Number: 100563954, 100911472, 100898311
* Date: December 4th, 2016
* Description: Post model
****************************************************************************************************/
public class Post {
	private String id, title, post_date, content, Userid, userFirstName, userLastName, username;
	Object comments = null;

	public Object getComments() {
		return comments;
	}

	public void setComments(Object comments) {
		this.comments = comments;
	}

	public String getUsername() {
		return userFirstName + " " + userLastName;
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

	public String getPost_date() {
		return post_date;
	}

	public void setPost_date(String post_date) {
		this.post_date = post_date;
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
