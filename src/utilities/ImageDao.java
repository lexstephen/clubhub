package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImageDao
 */
@WebServlet("/ImageDao")
@MultipartConfig(maxFileSize = 10177215) // upload file's size up to 16MB
public class ImageDao extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageDao() {
        super();
		try {
			connect = DatabaseAccess.connectDataBase();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("t") != null?request.getParameter("t"):(String) request.getAttribute("t");
		String imageId = request.getParameter("id");
		String prefId = request.getParameter("p");
        InputStream sImage;
		switch (type) {
			case "profile":
		        // Check if ID is supplied to the request.
		        if (imageId == null) {
		            // Do your thing if the ID is not supplied to the request.
		            // Throw an exception, or send 404, or show default/warning image, or just ignore it.
		            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
		            return;
		        }
	
		        try{
					statement = connect.createStatement();
					preparedStatement = connect.prepareStatement("SELECT photo from ch_user where id = " + imageId);
					resultSet = preparedStatement.executeQuery();
		            if(resultSet.next()){
		                byte[] bytearray = new byte[1048576];
		                int size=0;
		                sImage = resultSet.getBinaryStream("photo");
		                response.reset();
		                response.setContentType("image/jpeg");
		                while((size = sImage.read(bytearray)) != -1 ){
		                    response.getOutputStream().
		                    write(bytearray,0,size);
		                }
		            } else {
			            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
		            }	
	
		        } catch (Exception e){
		            e.printStackTrace();
		        }
	        break;
			case "image_logo":
				if(prefId != null) {
			        try{
						statement = connect.createStatement();
						preparedStatement = connect.prepareStatement("SELECT image_logo from ch_preferences where id = " + prefId);
						resultSet = preparedStatement.executeQuery();
			            if(resultSet.next()){
			                byte[] bytearray = new byte[1048576];
			                int size=0;
			                sImage = resultSet.getBinaryStream("image_logo");
			                response.reset();
			                response.setContentType("image/jpeg");
			                while((size = sImage.read(bytearray)) != -1 ){
			                    response.getOutputStream().
			                    write(bytearray,0,size);
			                }
			            }

			        } catch (Exception e){
			            e.printStackTrace();
			        }
				} else {
			        try{
						statement = connect.createStatement();
						preparedStatement = connect.prepareStatement("SELECT image_logo from ch_preferences where status = 1");
						resultSet = preparedStatement.executeQuery();
			            if(resultSet.next()){
			                byte[] bytearray = new byte[1048576];
			                int size=0;
			                sImage = resultSet.getBinaryStream("image_logo");
			                response.reset();
			                response.setContentType("image/jpeg");
			                while((size = sImage.read(bytearray)) != -1 ){
			                    response.getOutputStream().
			                    write(bytearray,0,size);
			                }
			            }

			        } catch (Exception e){
			            e.printStackTrace();
			        }
				}
	        break;
			case "image_small_logo":
				if(prefId != null) {
					try{
						statement = connect.createStatement();
						preparedStatement = connect.prepareStatement("SELECT image_small_logo from ch_preferences where id = " + prefId);
						resultSet = preparedStatement.executeQuery();
			            if(resultSet.next()){
			                System.out.println("Inside RS");
			                byte[] bytearray = new byte[1048576];
			                int size=0;
			                sImage = resultSet.getBinaryStream("image_small_logo");
			                response.reset();
			                response.setContentType("image/jpeg");
			                while((size = sImage.read(bytearray)) != -1 ){
			                    response.getOutputStream().
			                    write(bytearray,0,size);
			                }
			            }

			        } catch (Exception e){
			            e.printStackTrace();
			        }
				} else {
					try{
						statement = connect.createStatement();
						preparedStatement = connect.prepareStatement("SELECT image_small_logo from ch_preferences where status = 1");
						resultSet = preparedStatement.executeQuery();
			            if(resultSet.next()){
			                System.out.println("Inside RS");
			                byte[] bytearray = new byte[1048576];
			                int size=0;
			                sImage = resultSet.getBinaryStream("image_small_logo");
			                response.reset();
			                response.setContentType("image/jpeg");
			                while((size = sImage.read(bytearray)) != -1 ){
			                    response.getOutputStream().
			                    write(bytearray,0,size);
			                }
			            }

			        } catch (Exception e){
			            e.printStackTrace();
			        }
				}
	        break;
	        
	        // featured image start
			case "featured_image_1":
				if(prefId != null) {
					try{
						statement = connect.createStatement();
						preparedStatement = connect.prepareStatement("SELECT featured_image_01 from ch_preferences where id = " + prefId);
						resultSet = preparedStatement.executeQuery();
			            if(resultSet.next()){
			                System.out.println("Inside RS");
			                byte[] bytearray = new byte[1048576];
			                int size=0;
			                sImage = resultSet.getBinaryStream("featured_image_01");
			                response.reset();
			                response.setContentType("image/jpeg");
			                while((size = sImage.read(bytearray)) != -1 ){
			                    response.getOutputStream().
			                    write(bytearray,0,size);
			                }
			            }

			        } catch (Exception e){
			            e.printStackTrace();
			        }
				} else {
					try{
						statement = connect.createStatement();
						preparedStatement = connect.prepareStatement("SELECT featured_image_01 from ch_preferences where status = 1");
						resultSet = preparedStatement.executeQuery();
			            if(resultSet.next()){
			                System.out.println("Inside RS");
			                byte[] bytearray = new byte[1048576];
			                int size=0;
			                sImage = resultSet.getBinaryStream("featured_image_01");
			                response.reset();
			                response.setContentType("image/jpeg");
			                while((size = sImage.read(bytearray)) != -1 ){
			                    response.getOutputStream().
			                    write(bytearray,0,size);
			                }
			            }

			        } catch (Exception e){
			            e.printStackTrace();
			        }
				}
	        break;
	        // featured image end
	        
	        // featured image start
			case "featured_image_2":
				if(prefId != null) {
					try{
						statement = connect.createStatement();
						preparedStatement = connect.prepareStatement("SELECT featured_image_02 from ch_preferences where id = " + prefId);
						resultSet = preparedStatement.executeQuery();
			            if(resultSet.next()){
			                System.out.println("Inside RS");
			                byte[] bytearray = new byte[1048576];
			                int size=0;
			                sImage = resultSet.getBinaryStream("featured_image_02");
			                response.reset();
			                response.setContentType("image/jpeg");
			                while((size = sImage.read(bytearray)) != -1 ){
			                    response.getOutputStream().
			                    write(bytearray,0,size);
			                }
			            }

			        } catch (Exception e){
			            e.printStackTrace();
			        }
				} else {
					try{
						statement = connect.createStatement();
						preparedStatement = connect.prepareStatement("SELECT featured_image_02 from ch_preferences where status = 1");
						resultSet = preparedStatement.executeQuery();
			            if(resultSet.next()){
			                System.out.println("Inside RS");
			                byte[] bytearray = new byte[1048576];
			                int size=0;
			                sImage = resultSet.getBinaryStream("featured_image_02");
			                response.reset();
			                response.setContentType("image/jpeg");
			                while((size = sImage.read(bytearray)) != -1 ){
			                    response.getOutputStream().
			                    write(bytearray,0,size);
			                }
			            }

			        } catch (Exception e){
			            e.printStackTrace();
			        }
				}
	        break;
	        // featured image end
	        
	        // featured image start
			case "featured_image_3":
				if(prefId != null) {
					try{
						statement = connect.createStatement();
						preparedStatement = connect.prepareStatement("SELECT featured_image_03 from ch_preferences where id = " + prefId);
						resultSet = preparedStatement.executeQuery();
			            if(resultSet.next()){
			                System.out.println("Inside RS");
			                byte[] bytearray = new byte[1048576];
			                int size=0;
			                sImage = resultSet.getBinaryStream("featured_image_03");
			                response.reset();
			                response.setContentType("image/jpeg");
			                while((size = sImage.read(bytearray)) != -1 ){
			                    response.getOutputStream().
			                    write(bytearray,0,size);
			                }
			            }

			        } catch (Exception e){
			            e.printStackTrace();
			        }
				} else {
					try{
						statement = connect.createStatement();
						preparedStatement = connect.prepareStatement("SELECT featured_image_03 from ch_preferences where status = 1");
						resultSet = preparedStatement.executeQuery();
			            if(resultSet.next()){
			                System.out.println("Inside RS");
			                byte[] bytearray = new byte[1048576];
			                int size=0;
			                sImage = resultSet.getBinaryStream("featured_image_03");
			                response.reset();
			                response.setContentType("image/jpeg");
			                while((size = sImage.read(bytearray)) != -1 ){
			                    response.getOutputStream().
			                    write(bytearray,0,size);
			                }
			            }

			        } catch (Exception e){
			            e.printStackTrace();
			        }
				}
	        break;
	        // featured image end
	        
	        // featured image start
			case "featured_image_4":
				if(prefId != null) {
					try{
						statement = connect.createStatement();
						preparedStatement = connect.prepareStatement("SELECT featured_image_04 from ch_preferences where id = " + prefId);
						resultSet = preparedStatement.executeQuery();
			            if(resultSet.next()){
			                System.out.println("Inside RS");
			                byte[] bytearray = new byte[1048576];
			                int size=0;
			                sImage = resultSet.getBinaryStream("featured_image_04");
			                response.reset();
			                response.setContentType("image/jpeg");
			                while((size = sImage.read(bytearray)) != -1 ){
			                    response.getOutputStream().
			                    write(bytearray,0,size);
			                }
			            }

			        } catch (Exception e){
			            e.printStackTrace();
			        }
				} else {
					try{
						statement = connect.createStatement();
						preparedStatement = connect.prepareStatement("SELECT featured_image_04 from ch_preferences where status = 1");
						resultSet = preparedStatement.executeQuery();
			            if(resultSet.next()){
			                System.out.println("Inside RS");
			                byte[] bytearray = new byte[1048576];
			                int size=0;
			                sImage = resultSet.getBinaryStream("featured_image_04");
			                response.reset();
			                response.setContentType("image/jpeg");
			                while((size = sImage.read(bytearray)) != -1 ){
			                    response.getOutputStream().
			                    write(bytearray,0,size);
			                }
			            }

			        } catch (Exception e){
			            e.printStackTrace();
			        }
				}
	        break;
	        // featured image end
	        
	        // featured image start
			case "featured_image_5":
				if(prefId != null) {
					try{
						statement = connect.createStatement();
						preparedStatement = connect.prepareStatement("SELECT featured_image_05 from ch_preferences where id = " + prefId);
						resultSet = preparedStatement.executeQuery();
			            if(resultSet.next()){
			                System.out.println("Inside RS");
			                byte[] bytearray = new byte[1048576];
			                int size=0;
			                sImage = resultSet.getBinaryStream("featured_image_05");
			                response.reset();
			                response.setContentType("image/jpeg");
			                while((size = sImage.read(bytearray)) != -1 ){
			                    response.getOutputStream().
			                    write(bytearray,0,size);
			                }
			            }

			        } catch (Exception e){
			            e.printStackTrace();
			        }
				} else {
					try{
						statement = connect.createStatement();
						preparedStatement = connect.prepareStatement("SELECT featured_image_05 from ch_preferences where status = 1");
						resultSet = preparedStatement.executeQuery();
			            if(resultSet.next()){
			                System.out.println("Inside RS");
			                byte[] bytearray = new byte[1048576];
			                int size=0;
			                sImage = resultSet.getBinaryStream("featured_image_05");
			                response.reset();
			                response.setContentType("image/jpeg");
			                while((size = sImage.read(bytearray)) != -1 ){
			                    response.getOutputStream().
			                    write(bytearray,0,size);
			                }
			            }

			        } catch (Exception e){
			            e.printStackTrace();
			        }
				}
	        break;
	        // featured image end
	        
	        // featured image start
			case "featured_image_6":
				if(prefId != null) {
					try{
						statement = connect.createStatement();
						preparedStatement = connect.prepareStatement("SELECT featured_image_06 from ch_preferences where id = " + prefId);
						resultSet = preparedStatement.executeQuery();
			            if(resultSet.next()){
			                System.out.println("Inside RS");
			                byte[] bytearray = new byte[1048576];
			                int size=0;
			                sImage = resultSet.getBinaryStream("featured_image_06");
			                response.reset();
			                response.setContentType("image/jpeg");
			                while((size = sImage.read(bytearray)) != -1 ){
			                    response.getOutputStream().
			                    write(bytearray,0,size);
			                }
			            }

			        } catch (Exception e){
			            e.printStackTrace();
			        }
				} else {
					try{
						statement = connect.createStatement();
						preparedStatement = connect.prepareStatement("SELECT featured_image_06 from ch_preferences where status = 1");
						resultSet = preparedStatement.executeQuery();
			            if(resultSet.next()){
			                System.out.println("Inside RS");
			                byte[] bytearray = new byte[1048576];
			                int size=0;
			                sImage = resultSet.getBinaryStream("featured_image_06");
			                response.reset();
			                response.setContentType("image/jpeg");
			                while((size = sImage.read(bytearray)) != -1 ){
			                    response.getOutputStream().
			                    write(bytearray,0,size);
			                }
			            }

			        } catch (Exception e){
			            e.printStackTrace();
			        }
				}
	        break;
	        // featured image end
	        
	        // featured image start
			case "featured_image_7":
				if(prefId != null) {
					try{
						statement = connect.createStatement();
						preparedStatement = connect.prepareStatement("SELECT featured_image_07 from ch_preferences where id = " + prefId);
						resultSet = preparedStatement.executeQuery();
			            if(resultSet.next()){
			                System.out.println("Inside RS");
			                byte[] bytearray = new byte[1048576];
			                int size=0;
			                sImage = resultSet.getBinaryStream("featured_image_07");
			                response.reset();
			                response.setContentType("image/jpeg");
			                while((size = sImage.read(bytearray)) != -1 ){
			                    response.getOutputStream().
			                    write(bytearray,0,size);
			                }
			            }

			        } catch (Exception e){
			            e.printStackTrace();
			        }
				} else {
					try{
						statement = connect.createStatement();
						preparedStatement = connect.prepareStatement("SELECT featured_image_07 from ch_preferences where status = 1");
						resultSet = preparedStatement.executeQuery();
			            if(resultSet.next()){
			                System.out.println("Inside RS");
			                byte[] bytearray = new byte[1048576];
			                int size=0;
			                sImage = resultSet.getBinaryStream("featured_image_07");
			                response.reset();
			                response.setContentType("image/jpeg");
			                while((size = sImage.read(bytearray)) != -1 ){
			                    response.getOutputStream().
			                    write(bytearray,0,size);
			                }
			            }

			        } catch (Exception e){
			            e.printStackTrace();
			        }
				}
	        break;
	        // featured image end
	        
	        // featured image start
			case "featured_image_8":
				if(prefId != null) {
					try{
						statement = connect.createStatement();
						preparedStatement = connect.prepareStatement("SELECT featured_image_08 from ch_preferences where id = " + prefId);
						resultSet = preparedStatement.executeQuery();
			            if(resultSet.next()){
			                System.out.println("Inside RS");
			                byte[] bytearray = new byte[1048576];
			                int size=0;
			                sImage = resultSet.getBinaryStream("featured_image_08");
			                response.reset();
			                response.setContentType("image/jpeg");
			                while((size = sImage.read(bytearray)) != -1 ){
			                    response.getOutputStream().
			                    write(bytearray,0,size);
			                }
			            }

			        } catch (Exception e){
			            e.printStackTrace();
			        }
				} else {
					try{
						statement = connect.createStatement();
						preparedStatement = connect.prepareStatement("SELECT featured_image_08 from ch_preferences where status = 1");
						resultSet = preparedStatement.executeQuery();
			            if(resultSet.next()){
			                System.out.println("Inside RS");
			                byte[] bytearray = new byte[1048576];
			                int size=0;
			                sImage = resultSet.getBinaryStream("featured_image_08");
			                response.reset();
			                response.setContentType("image/jpeg");
			                while((size = sImage.read(bytearray)) != -1 ){
			                    response.getOutputStream().
			                    write(bytearray,0,size);
			                }
			            }

			        } catch (Exception e){
			            e.printStackTrace();
			        }
				}
	        break;
	        // featured image end
	        
	        // featured image start
			case "featured_image_9":
				if(prefId != null) {
					try{
						statement = connect.createStatement();
						preparedStatement = connect.prepareStatement("SELECT featured_image_09 from ch_preferences where id = " + prefId);
						resultSet = preparedStatement.executeQuery();
			            if(resultSet.next()){
			                System.out.println("Inside RS");
			                byte[] bytearray = new byte[1048576];
			                int size=0;
			                sImage = resultSet.getBinaryStream("featured_image_09");
			                response.reset();
			                response.setContentType("image/jpeg");
			                while((size = sImage.read(bytearray)) != -1 ){
			                    response.getOutputStream().
			                    write(bytearray,0,size);
			                }
			            }

			        } catch (Exception e){
			            e.printStackTrace();
			        }
				} else {
					try{
						statement = connect.createStatement();
						preparedStatement = connect.prepareStatement("SELECT featured_image_09 from ch_preferences where status = 1");
						resultSet = preparedStatement.executeQuery();
			            if(resultSet.next()){
			                System.out.println("Inside RS");
			                byte[] bytearray = new byte[1048576];
			                int size=0;
			                sImage = resultSet.getBinaryStream("featured_image_09");
			                response.reset();
			                response.setContentType("image/jpeg");
			                while((size = sImage.read(bytearray)) != -1 ){
			                    response.getOutputStream().
			                    write(bytearray,0,size);
			                }
			            }

			        } catch (Exception e){
			            e.printStackTrace();
			        }
				}
	        break;
	        // featured image end
	        
	        // featured image start
			case "featured_image_10":
				if(prefId != null) {
					try{
						statement = connect.createStatement();
						preparedStatement = connect.prepareStatement("SELECT featured_image_10 from ch_preferences where id = " + prefId);
						resultSet = preparedStatement.executeQuery();
			            if(resultSet.next()){
			                System.out.println("Inside RS");
			                byte[] bytearray = new byte[1048576];
			                int size=0;
			                sImage = resultSet.getBinaryStream("featured_image_10");
			                response.reset();
			                response.setContentType("image/jpeg");
			                while((size = sImage.read(bytearray)) != -1 ){
			                    response.getOutputStream().
			                    write(bytearray,0,size);
			                }
			            }

			        } catch (Exception e){
			            e.printStackTrace();
			        }
				} else {
					try{
						statement = connect.createStatement();
						preparedStatement = connect.prepareStatement("SELECT featured_image_10 from ch_preferences where status = 1");
						resultSet = preparedStatement.executeQuery();
			            if(resultSet.next()){
			                System.out.println("Inside RS");
			                byte[] bytearray = new byte[1048576];
			                int size=0;
			                sImage = resultSet.getBinaryStream("featured_image_10");
			                response.reset();
			                response.setContentType("image/jpeg");
			                while((size = sImage.read(bytearray)) != -1 ){
			                    response.getOutputStream().
			                    write(bytearray,0,size);
			                }
			            }

			        } catch (Exception e){
			            e.printStackTrace();
			        }
				}
	        break;
	        // featured image end
		}
	}
}
