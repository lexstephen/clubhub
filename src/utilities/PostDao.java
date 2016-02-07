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
			    resultSet = statement.executeQuery("SELECT post.title, post.content, post.id, user.username, user.firstName, user.lastName, posttype.type, access.type, category.type " 
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
			    	  post.setUserFirstName(resultSet.getString("user.firstName"));
			    	  post.setUserLastName(resultSet.getString("user.lastName"));
			    	  post.setPostType(resultSet.getString("posttype.type"));
			    	  post.setAccessLevel(resultSet.getString("access.type"));
			    	  post.setCategory(resultSet.getString("category.type"));
			    	  
			    	  request.setAttribute("postID", post.getId());

			    	  posts.add(post);
			    }
		    } catch (SQLException e) {
			      throw e;
			}
		  	request.setAttribute("posts", posts);
	} 
	
	public void deletePost(HttpServletRequest request, HttpServletResponse response, String postID) throws Exception {

		  try {
			  statement = connect.createStatement();
			  statement.executeUpdate("delete from ch_post where id =" + postID); 
		  } catch (SQLException e) {
		      throw e;
		  }
	}
	
	public void batchDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String [] markedForDeletion = request.getParameterValues("postSelected");
		for (String x : markedForDeletion) {
			deletePost(request, response, x);
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
	} 
	
	public void editPost(HttpServletRequest request, HttpServletResponse response, String _postID) throws Exception {
	    try {			
			String postID = request.getParameter("postID");
		    String title = request.getParameter("blogTitle");	// title
		    String content = request.getParameter("blogContent"); // content
		    String pageType = request.getParameter("pageType"); // Posttypeid
		    String accessLevel = request.getParameter("accessLevel"); // Accessid
		    String category = request.getParameter("pageCategory"); // Categoryid
			
	      
		    /*UPDATE `clubhub`.`ch_post` SET `title`='blogtitle', `content`='schoop doopy', 
	    		  `Userid`='1', `Posttypeid`='2', `Accessid`='2', `Categoryid`='2' WHERE `id`='6';*/
	      
			statement = connect.createStatement();
			statement.executeUpdate("UPDATE ch_post SET title='" + title + "', content='" + content + "', Posttypeid='" + pageType + 
					"', Accessid='" + accessLevel + "', Categoryid='" + category + "' WHERE id='" + postID + "'");
	      
	      //preparedStatement.executeUpdate();
	    } catch (Exception e) {
	      throw e;
	    }
	}

	public void batchEdit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String [] markedForEdit = request.getParameterValues("postSelected");
		String postID, pageType, accessLevel, category;
		
		pageType = request.getParameter("pageType"); // Posttypeid
	    accessLevel = request.getParameter("accessLevel"); // Accessid
	    category = request.getParameter("pageCategory"); // Categoryid
		
		for (String x : markedForEdit) {
			postID = x;
		    
			statement = connect.createStatement();
			statement.executeUpdate("UPDATE ch_post SET Posttypeid='" + pageType + 
					"', Accessid='" + accessLevel + "', Categoryid='" + category + "' WHERE id='" + postID + "'");
		}				
	}
}

