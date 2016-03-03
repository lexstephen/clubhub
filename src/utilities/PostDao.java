package utilities;
/****************************************************************************************************
* Project: ClubHub
* Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
* Student Number: 100563954, 100911472, 100898311
* Date: Feb 03, 2016
* Description: PostDao - prepares a database access object for the Post model
****************************************************************************************************/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Post;
import model.User;
import utilities.DatabaseAccess;

public class PostDao {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public PostDao() {
		try {
			connect = DatabaseAccess.connectDataBase();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean isInDatabase(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    try {
			String id = request.getParameter("id");
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from ch_post where id = \"" + id + "\""); 
			// if there result set is before the first item, there are entries
			// if it is not, there are not
			if (!resultSet.isBeforeFirst() ) {    
				return false;
			} else {
				return true;
			}
	    } catch (Exception e) {
	    	throw e;
	    } 
	}
	
	public void addToDatabase(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    try {
			HttpSession session = request.getSession();
			// this is temp  v v v
			session.setAttribute("userID", "2");
			// this is temp  ^ ^ ^
	      statement = connect.createStatement();
	      preparedStatement = connect.prepareStatement("insert into ch_post values (default, ?, ?, ?, ?, ?, ?)");
	      // columns are title, content, Userid, Posttypeid, Accessid, Categoryid
	      // Parameters start with 1 because we are sending 'default' to the auto incrementing id
	      preparedStatement.setString(1, request.getParameter("blogTitle"));	// title
	      preparedStatement.setString(2, request.getParameter("blogContent")); // content
	      preparedStatement.setString(3, (String)session.getAttribute("userID"));	// Userid
	      preparedStatement.setString(4, request.getParameter("pageType")); // Posttypeid
	      preparedStatement.setString(5, request.getParameter("accessLevel")); // Accessid
	      preparedStatement.setString(6, request.getParameter("pageCategory")); // Categoryid
	      preparedStatement.executeUpdate();
	    } catch (Exception e) {
	      throw e;
	    }
	}

	public void listAll(HttpServletRequest request) throws Exception {
		  List<Post> posts = new ArrayList<Post>();
		  	try{
		  		statement = connect.createStatement();
			    resultSet = statement.executeQuery("SELECT post.title, post.content, post.Userid, post.id, user.username, user.firstName, user.lastName, posttype.type, access.type, category.type " 
				+ "FROM clubhub.ch_post post "
				+ "JOIN clubhub.ch_posttype posttype "
				+ "ON post.Posttypeid = posttype.id "
				+ "JOIN clubhub.ch_user user "
				+ "ON post.Userid = user.id "
				+ "JOIN clubhub.ch_access access "
				+ "ON post.Accessid = access.id "
				+ "JOIN clubhub.ch_category category "
				+ "ON post.Categoryid = category.id ");
			      
			    while (resultSet.next()) {
			    	  Post post = new Post();
			    	  post.setTitle(resultSet.getString("title"));
			    	  post.setContent(resultSet.getString("content"));
			    	  post.setId(resultSet.getString("id"));
			    	  post.setUserid(resultSet.getString("Userid"));
			    	  post.setUserFirstName(resultSet.getString("user.firstName"));
			    	  post.setUserLastName(resultSet.getString("user.lastName"));
			    	  post.setPostType(resultSet.getString("posttype.type"));
			    	  post.setAccessLevel(resultSet.getString("access.type"));
			    	  post.setCategory(resultSet.getString("category.type"));			    	  
			    	  post.setPostMatchUser(post.getUserid().equals("2"));       // change to loggedInUser
			    	  
			    	  if(!(post.getAccessLevel().equals("Private") && post.isPostMatchUser() == false)) {
			    		  posts.add(post);
			    	  } 
			    }
		    } catch (SQLException e) {
			      throw e;
			}
		  	request.setAttribute("posts", posts);
	} 
	
	public void listAllBlogs(HttpServletRequest request) throws Exception {
		  List<Post> posts = new ArrayList<Post>();
		  
		  HttpSession session = request.getSession();
		  boolean isLoggedIn = (((Boolean) session.getAttribute("isLoggedIn")).booleanValue());
		  
		  	try{
		  		statement = connect.createStatement();
			    resultSet = statement.executeQuery("SELECT post.title, post.content, post.Userid, post.id, user.username, user.firstName, user.lastName, posttype.type, access.type, category.type " 
				+ "FROM clubhub.ch_post post "
				+ "JOIN clubhub.ch_posttype posttype "
				+ "ON post.Posttypeid = posttype.id "
				+ "JOIN clubhub.ch_user user "
				+ "ON post.Userid = user.id "
				+ "JOIN clubhub.ch_access access "
				+ "ON post.Accessid = access.id "
				+ "JOIN clubhub.ch_category category "
				+ "ON post.Categoryid = category.id "
				+ "WHERE posttype.id = 1 AND NOT access.id = 3");			// Where post is blog, and not private
			      
			    while (resultSet.next()) {
			    	  Post post = new Post();
			    	  post.setTitle(resultSet.getString("title"));
			    	  post.setContent(resultSet.getString("content"));
			    	  post.setId(resultSet.getString("id"));
			    	  post.setUserid(resultSet.getString("Userid"));
			    	  post.setUserFirstName(resultSet.getString("user.firstName"));
			    	  post.setUserLastName(resultSet.getString("user.lastName"));
			    	  post.setPostType(resultSet.getString("posttype.type"));
			    	  post.setAccessLevel(resultSet.getString("access.type"));
			    	  post.setCategory(resultSet.getString("category.type"));
			    	  post.setPostMatchUser(post.getUserid().equals("2"));       // change to loggedInUser
			    	  
			    	  if (post.getAccessLevel().equals("Public")) {
			    		  posts.add(post);		
			    	  } else if(post.getAccessLevel().equals("Members") && ((isLoggedIn == true))){
			    		  posts.add(post);	
			    	  } else if(post.getAccessLevel().equals("Private") && post.isPostMatchUser() == true) {
			    			posts.add(post);		
			    	  }
			    	  
			    	  request.setAttribute("postID", post.getId());			    	  
			    }
		    } catch (SQLException e) {
			      throw e;
			}
		  	request.setAttribute("posts", posts);
	} 
	
	public void deletePost(HttpServletRequest request, HttpServletResponse response) throws Exception {


		String postID = (String)request.getParameter("postID").toString();
				
		  try {
			  statement = connect.createStatement();
			  statement.executeUpdate("delete from ch_post where id =" + postID); 
			  System.out.println("delte postID = " + postID);
		  } catch (SQLException e) {
		      throw e;
		  }
	}
	
	public void batchDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String [] markedForDeletion = request.getParameterValues("postSelected");
		for (String postID : markedForDeletion) {
			request.setAttribute("postID", postID);
			System.out.println("batchDelete postID: " + request.getAttribute("postID"));
			deletePost(request, response);
		}		
	}
	
	public void findPost(HttpServletRequest request, String postID) throws Exception {
		  Post post = new Post();
		  
		  	try{
			    statement = connect.createStatement();
			    resultSet = statement.executeQuery("SELECT post.title, post.content, post.id, user.username, user.firstName, user.lastName, posttype.type, access.type, category.type " 
				+ "FROM clubhub.ch_post post "
				+ "JOIN clubhub.ch_posttype posttype "
				+ "ON post.Posttypeid = posttype.id "
				+ "JOIN clubhub.ch_user user "
				+ "ON post.Userid = user.id "
				+ "JOIN clubhub.ch_access access "
				+ "ON post.Accessid = access.id "
				+ "JOIN clubhub.ch_category category "
				+ "ON post.Categoryid = category.id "
				+ "WHERE post.id = " + postID);
			    
			    while (resultSet.next()) {
			    	  post.setTitle(resultSet.getString("title"));
			    	  post.setContent(resultSet.getString("content"));
			    	  post.setId(resultSet.getString("id"));
			    	  post.setUserFirstName(resultSet.getString("user.firstName"));
			    	  post.setUserLastName(resultSet.getString("user.lastName"));
			    	  post.setPostType(resultSet.getString("posttype.type"));
			    	  post.setAccessLevel(resultSet.getString("access.type"));
			    	  post.setCategory(resultSet.getString("category.type"));
			    }
			} catch (SQLException e) {
			      throw e;
			}
		  	request.setAttribute("post", post);
		  	request.setAttribute("accessLevel", post.getAccessLevel());
		  	request.setAttribute("postType", post.getPostType());
		  	request.setAttribute("pageCategory", post.getCategory());
	} 
	
	public void editPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    try {			
			String postID = request.getParameter("postID");
		    String title = request.getParameter("blogTitle");	// title
		    String content = request.getParameter("blogContent"); // content
		    String pageType = request.getParameter("pageType"); // Posttypeid
		    String accessLevel = request.getParameter("accessLevel"); // Accessid
		    String category = (request.getParameter("pageCategory") != null) ? request.getParameter("pageCategory"): "1"; // Categoryid
	      
		    statement = connect.createStatement();
		    preparedStatement = connect.prepareStatement("UPDATE ch_post SET title = ?, content = ?, Posttypeid = ?, "
		    		+ "Accessid= ?, Categoryid= ? WHERE id='" + postID + "'");
		    
		    preparedStatement.setString(1, title);
		    preparedStatement.setString(2, content);
		    preparedStatement.setString(3, pageType);
		    preparedStatement.setString(4, accessLevel);
		    preparedStatement.setString(5, category);
		    
		    preparedStatement.executeUpdate();

	    } catch (Exception e) {
	      throw e;
	    }
	}

	public void batchEdit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// this method edits the postType, accessLevel, and/or category in ch_post
		
		String [] markedForEdit = request.getParameterValues("postSelected");
		String postID, pageType, accessLevel, category, executeString = "";
		
		pageType = request.getParameter("pageType"); // Posttypeid
	    accessLevel = request.getParameter("accessLevel"); // Accessid
	    category = request.getParameter("pageCategory"); // Categoryid
	    
	    if (!pageType.equals("0")) {
	    	executeString = "Posttypeid='" + pageType;
	    }
	    if (!accessLevel.equals("0") && !pageType.equals("0")) {
	    	executeString += "', Accessid='" + accessLevel;
	    } else if(!accessLevel.equals("0")) 
	    {
	    	executeString = "Accessid='" + accessLevel;
	    }
	    if (!category.equals("0") && (!accessLevel.equals("0") || !pageType.equals("0"))) {
	    	executeString += "', Categoryid='" + category;
	    } else if(!category.equals("0")) 
	    {
	    	executeString = "Categoryid='" + category;
	    }
	    
	    if (!executeString.equals(""))
	    {
	    	System.out.println("executeString is not null. Here's pageType, accessLevel, pageCategory values: " + pageType + accessLevel + category);
	    	System.out.println("And here's executeString: " + executeString);
			for (String x : markedForEdit) 
			{
				postID = x;
			    statement = connect.createStatement();
				statement.executeUpdate("UPDATE ch_post SET " + executeString + "' WHERE id='" + postID + "'");
			}
		}				
	}
	
	public List<String> getLastBlogs(HttpServletRequest request, HttpServletResponse response) throws Exception { 
		//this method returns the latest 3 blog posts (Posttypeid = 1) in ch_post
		
		int postsMod, pageCnt, numOfPages = 0, ppp = 3;   // Posts Per Page
		double numOfRows;
		List<String> postIDs = new ArrayList<String>();		
		String query = "";
		
		// ALWAYS RETURNING NULL WTF FUCK YOU GO TO HELL AND STAY THERE FOR THE LOVE OF GOD WHY ARE YOU DOING THIS TO ME //
		// ill fix it tomorrow
		pageCnt = (request.getAttribute("pageCnt") == null) ? 69 : Integer.parseInt(request.getAttribute("pageCnt").toString());  // Page count, 0 if null
		String pageNav = (request.getAttribute("pageNav") == null ? "first" : request.getAttribute("pageNav").toString()) ;
		
		System.out.println("pageCnt = " + pageCnt);
		System.out.println("pageNav = " + pageNav);
		
		try {			
			statement = connect.createStatement();
			resultSet = statement.executeQuery("SELECT COUNT(*) FROM ch_post");
			
			while (resultSet.next()) {
				numOfRows = Integer.parseInt(resultSet.getString(1));
				numOfPages = (int)Math.ceil(numOfRows/3);
				postsMod = (int) (numOfRows % 3);
				System.out.println("postsMod = " + postsMod);
				System.out.println("numOfPages = " + numOfPages);
				System.out.println("numOfRows = "+ numOfRows);
			}
		} catch (SQLException e) {
			throw e;
		}
		
		switch (pageNav) {
		case "first": 
			pageCnt = 0;
			query = "SELECT id FROM ch_post WHERE Posttypeid = '1' AND NOT Accessid = '3' ORDER BY id DESC LIMIT " + pageCnt + ", " + ppp;
			break;
		case "previous":
			pageCnt--;
			query = "SELECT id FROM ch_post WHERE Posttypeid = '1' AND NOT Accessid = '3' ORDER BY id DESC LIMIT " + (pageCnt*ppp) + ", " + ppp;
			break;
		case "next":
			pageCnt++;
			break;
		case "last":
			pageCnt = numOfPages;
			query = "SELECT id FROM ch_post LIMIT " + ppp;
			System.out.println("last pageCnt = " + pageCnt);
		break;
		}
		
		
		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery(query);
			  
			while (resultSet.next()) {
				postIDs.add(resultSet.getString(1));
			}
		} catch (SQLException e) {
			throw e;
		}

		request.setAttribute("pageCnt", pageCnt);
		request.removeAttribute("pageNav");
		
		return postIDs;

	}
}

