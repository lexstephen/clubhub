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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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
			
			statement = connect.createStatement();
			preparedStatement = connect.prepareStatement("insert into ch_post values (default, ?, ?, ?, ?, ?, ?, ?)");
			// columns are title, content, Userid, Posttypeid, Accessid, Categoryid
			// Parameters start with 1 because we are sending 'default' to the auto incrementing id
		    preparedStatement.setString(1, request.getParameter("blogTitle"));	// title
		    preparedStatement.setString(2, request.getParameter("blogContent")); // content
		    preparedStatement.setString(3, (String)session.getAttribute("loggedInUserID"));	// Userid
		    preparedStatement.setString(4, request.getParameter("pageType")); // Posttypeid
		    preparedStatement.setString(5, request.getParameter("accessLevel")); // Accessid
		    preparedStatement.setString(6, (request.getParameter("pageCategory") != null) ? request.getParameter("pageCategory"): "1"); // Categoryid)
		    preparedStatement.setString(7,  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		    preparedStatement.executeUpdate();
		    } catch (Exception e) {
		      throw e;
		    }
	}

	public void listAll(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();  
		List<Post> posts = new ArrayList<Post>();
		  	try{
		  		statement = connect.createStatement();
			    resultSet = statement.executeQuery("SELECT post.title, post.content, post.Userid, post.id, post.Postdate, user.username, user.firstName, user.lastName, posttype.type, access.type, category.type " 
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
			    	  post.setPostDate(resultSet.getString("Postdate"));
			    	  post.setPostMatchUser(post.getUserid().equals((String)session.getAttribute("loggedInUserID")));    
			    	  
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
		  boolean isLoggedIn = false;
		  HttpSession session = request.getSession();

		  if (session.getAttribute("isLoggedIn") != null) {
			  isLoggedIn = (((Boolean) session.getAttribute("isLoggedIn")).booleanValue());
		  }

		  	try{
		  		statement = connect.createStatement();
			    resultSet = statement.executeQuery("SELECT post.title, post.content, post.Userid, post.id, post.Postdate, user.username, user.firstName, user.lastName, posttype.type, access.type, category.type " 
				+ "FROM clubhub.ch_post post "
				+ "JOIN clubhub.ch_posttype posttype "
				+ "ON post.Posttypeid = posttype.id "
				+ "JOIN clubhub.ch_user user "
				+ "ON post.Userid = user.id "
				+ "JOIN clubhub.ch_access access "
				+ "ON post.Accessid = access.id "
				+ "JOIN clubhub.ch_category category "
				+ "ON post.Categoryid = category.id "
				+ "WHERE posttype.id = 1 ORDER BY Postdate");			// Where post is blog
			      
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
			    	  post.setPostDate(resultSet.getString("Postdate"));
			    	  post.setPostMatchUser(post.getUserid().equals((String)session.getAttribute("loggedInUserID")));
			    	  
			    	  if (post.getAccessLevel().equals("Public")) {
			    		  posts.add(post);		
			    	  } else if (post.getAccessLevel().equals("Members") && ((isLoggedIn == true))){
			    		  posts.add(post);	
			    	  } else if (post.getAccessLevel().equals("Private") && post.isPostMatchUser() == true) {
			    		  posts.add(post);		
			    	  }
			    	  			    	  
			    }
		    } catch (SQLException e) {
			      throw e;
			}
		  	request.setAttribute("blogs", posts);
	} 
	
	public void listStatic(HttpServletRequest request) throws Exception {
		  List<Post> posts = new ArrayList<Post>();
		  boolean isLoggedIn = false;
		  HttpSession session = request.getSession();

		  if (session.getAttribute("isLoggedIn") != null) {
			  isLoggedIn = (((Boolean) session.getAttribute("isLoggedIn")).booleanValue());
		  }

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
				+ "WHERE posttype.id = 2 AND NOT access.id = 3");			// Where post is Static Content, and not private
			      
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
			    	  post.setPostMatchUser(post.getUserid().equals((String)session.getAttribute("loggedInUserID")));
			    	  
			    	  if (post.getAccessLevel().equals("Public")) {
			    		  posts.add(post);		
			    	  } else if(post.getAccessLevel().equals("Members") && ((isLoggedIn == true))){
			    		  posts.add(post);	
			    	  } else if(post.getAccessLevel().equals("Private") && post.isPostMatchUser() == true) {
			    			posts.add(post);		
			    	  }
			    	  		    	  
			    }
		    } catch (SQLException e) {
			      throw e;
			}
		  	request.setAttribute("posts", posts);
	} 
	
	public void deletePost(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// if coming from batchDelte, attribute will be set. otherwise, parameter will be set
		String postID = (request.getAttribute("postID")) == null ? request.getParameter("postID") : (String) request.getAttribute("postID");
				
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
			    resultSet = statement.executeQuery("SELECT post.title, post.content, post.id, post.Postdate, user.username, user.firstName, user.lastName, posttype.type, access.type, category.type " 
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
			    	  post.setPostDate(resultSet.getString("Postdate"));
			    	  
			    	  System.out.println("PostDate = " + post.getPostDate());
			    }
			} catch (SQLException e) {
			      throw e;
			}
		  	request.setAttribute("post", post);
		  	request.setAttribute("postTitle", post.getTitle());
	} 
	
	public void editPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {			
			String postID = request.getParameter("postID");
		    String title = request.getParameter("blogTitle");	// title
		    String content = request.getParameter("blogContent"); // content
		    String pageType = request.getParameter("pageType"); // Posttypeid
		    String accessLevel = (request.getParameter("accessLevel")!= null) ? request.getParameter("accessLevel"): "1"; // Accessid
		    String category = (request.getParameter("pageCategory") != null) ? request.getParameter("pageCategory"): "1"; // Categoryid
	      
		    statement = connect.createStatement();
		    preparedStatement = connect.prepareStatement("UPDATE ch_post SET title = ?, content = ?, Posttypeid = ?, "
		    		+ "Accessid= ?, Categoryid= ?, Postdate= ? WHERE id='" + postID + "'");
		    
		    preparedStatement.setString(1, title);
		    preparedStatement.setString(2, content);
		    preparedStatement.setString(3, pageType);
		    preparedStatement.setString(4, accessLevel);
		    preparedStatement.setString(5, category);
		    preparedStatement.setString(6,  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		    
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
	
	public void getLastBlogs(HttpServletRequest request, HttpServletResponse response) throws Exception { 
		// this method returns the latest 3 blog posts (Posttypeid = 1) in ch_post
		// we do all the pagination coding here. first off, we pull in all viable blog posts and then reverse it to sort by newest.
		// we count the number of entries and how many 'pages' will be needed to display all entries. 
		
		HttpSession session = request.getSession();
		listAllBlogs(request);
		
		int pageCnt = (session.getAttribute("pageCnt") == null) ? 0 : Integer.parseInt(session.getAttribute("pageCnt").toString());  // Page count, 0 if null
		int numOfPages = 0, ppp = 3;   // Posts Per Page. this can be changed, maybe in preferences even?
		double numOfRows = 0;
		String pageNav = (request.getAttribute("pageNav") == null ? "first" : request.getAttribute("pageNav").toString()) ;
		System.out.println(pageNav);
		List<Post> posts = new ArrayList<Post>();	
		@SuppressWarnings("unchecked")
		List<Post> allBlogs = (List<Post>) request.getAttribute("blogs");
		Collections.reverse(allBlogs);  // after this line, we have all our blogs in reverse order as list items

		numOfRows = allBlogs.size();
		numOfPages = (int)Math.ceil(numOfRows/ppp); // number of pages needed is rounded up to the nearest whole number
		
		switch (pageNav) {
		case "first": 
			posts = allBlogs.subList(0, ppp > (int)numOfRows ? (int)numOfRows : ppp); // select the first ppp(3) items, unless the ppp is smaller than the number of rows
			pageCnt = 1;
			break;
		case "previous":
			if (pageCnt > 1) {
				pageCnt--;
				posts = allBlogs.subList(pageCnt*ppp-ppp, pageCnt*ppp);
			} else {
				posts = allBlogs.subList(0, ppp > (int)numOfRows ? (int)numOfRows : ppp);
				pageCnt = 1;
			}
			break;
		case "next":
			if (pageCnt >= numOfPages-1) {
				pageCnt = numOfPages;
				posts = allBlogs.subList(((int)numOfRows - ppp) < 0 ? 0:(int)numOfRows - ppp, (int)numOfRows);
			} else {
				posts = allBlogs.subList(pageCnt*ppp, (pageCnt*ppp+ppp) > (int)numOfRows ? (int)numOfRows : pageCnt*ppp+ppp);
				pageCnt++;
			}
			break;
		case "last":
			pageCnt = numOfPages;
			posts = allBlogs.subList(((int)numOfRows - ppp) < 0 ? 0:(int)numOfRows - ppp, (int)numOfRows);
			break;
		}

		session.setAttribute("pageCnt", pageCnt);
		request.setAttribute("blogs", posts);
		request.removeAttribute("pageNav");		

	}

	public void searchPosts(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		
		List<Post> posts = new ArrayList<Post>();
		boolean isLoggedIn = false;
		String searchTerm = request.getParameter("searchTerm");
		System.out.println("searchTerm = " + searchTerm);
		
		HttpSession session = request.getSession();
	
		if (session.getAttribute("isLoggedIn") != null) {
			isLoggedIn = (((Boolean) session.getAttribute("isLoggedIn")).booleanValue());
		}
	
	  		try{
	  			statement = connect.createStatement();
			    resultSet = statement.executeQuery("SELECT post.title, post.content, post.Userid, post.id, post.Postdate, user.username, user.firstName, user.lastName, posttype.type, access.type, category.type " 
				+ "FROM clubhub.ch_post post "
				+ "JOIN clubhub.ch_posttype posttype "
				+ "ON post.Posttypeid = posttype.id "
				+ "JOIN clubhub.ch_user user "
				+ "ON post.Userid = user.id "
				+ "JOIN clubhub.ch_access access "
				+ "ON post.Accessid = access.id "
				+ "JOIN clubhub.ch_category category "
				+ "ON post.Categoryid = category.id "
				+ "WHERE posttype.id = 1 AND content LIKE '%" + searchTerm + "%'");			// Where post is blog
			      
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
			    	  post.setPostDate(resultSet.getString("Postdate"));
			    	  post.setPostMatchUser(post.getUserid().equals((String)session.getAttribute("loggedInUserID")));
			    	  
			    	  if (post.getAccessLevel().equals("Public")) {
			    		  posts.add(post);		
			    	  } else if(post.getAccessLevel().equals("Members") && ((isLoggedIn == true))){
			    		  posts.add(post);	
			    	  } else if(post.getAccessLevel().equals("Private") && post.isPostMatchUser() == true) {
			    			posts.add(post);		
			    	  }
			    	  			    	  
			    }
		    } catch (SQLException e) {
			      throw e;
			}
		  	request.setAttribute("blogs", posts);

		
	}
}

