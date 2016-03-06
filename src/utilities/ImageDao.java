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
		 String imageId = request.getParameter("id");
	        System.out.println(imageId);
	        InputStream sImage;

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
	                System.out.println("Inside RS");
	                byte[] bytearray = new byte[1048576];
	                int size=0;
	                sImage = resultSet.getBinaryStream("photo");
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

}