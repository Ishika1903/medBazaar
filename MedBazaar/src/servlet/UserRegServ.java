package servlet;

/*
 * package servlet;
 * 
 * import java.io.IOException; import java.sql.Connection; import
 * java.sql.DriverManager; import java.sql.Statement;
 * 
 * import javax.servlet.ServletException; import
 * javax.servlet.annotation.WebServlet; import javax.servlet.http.HttpServlet;
 * import javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse;
 * 
 *//**
	 * Servlet implementation class UserRegServ
	 *//*
		 * @WebServlet("/UserRegServ") public class UserRegServ extends HttpServlet {
		 * private static final long serialVersionUID = 1L;
		 * 
		 * protected void doPost(HttpServletRequest request, HttpServletResponse
		 * response) throws ServletException, IOException { String fname =
		 * request.getParameter("fname"); String lname = request.getParameter("lname");
		 * String email = request.getParameter("email"); String phone =
		 * request.getParameter("phno"); String password =
		 * request.getParameter("pass1"); String reEnterPass =
		 * request.getParameter("pass2");
		 * 
		 * if (password.equals(reEnterPass)) { try { // Connect to the database
		 * Class.forName("com.mysql.cj.jdbc.Driver"); Connection con =
		 * DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root",
		 * "password");
		 * 
		 * // Create a statement Statement stmt = con.createStatement();
		 * 
		 * // Insert the user data into the database String sql =
		 * "INSERT INTO usr_reg (fname, lname, email, phone, password) VALUES ('" +
		 * fname + "', '" + lname + "', '" + email + "', '" + phone + "', '" + password
		 * + "')"; stmt.executeUpdate(sql);
		 * 
		 * // Close the connection con.close();
		 * 
		 * // Redirect to the success page response.sendRedirect("success.jsp"); } catch
		 * (Exception e) { e.printStackTrace(); response.sendRedirect("error.jsp"); } }
		 * else { // Redirect to the error page response.sendRedirect("error.jsp"); } }
		 * 
		 * }
		 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserRegServ")
public class UserRegServ extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phno");
        String password = request.getParameter("pass1");
        String reEnterPass = request.getParameter("pass2");

        if (password.equals(reEnterPass)) {
            Connection con = null;
            PreparedStatement pstmt = null;

            try {
                // Connect to the database
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost/online_medishopdb", "root", "Ishika1903!");

                // Create a prepared statement
                String sql = "INSERT INTO usr_reg (fname, lname, email, phone, password) VALUES (?, ?, ?, ?, ?)";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, fname);
                pstmt.setString(2, lname);
                pstmt.setString(3, email);
                pstmt.setString(4, phone);
                pstmt.setString(5, password);

                // Execute the prepared statement
                pstmt.executeUpdate();

                // Redirect to the success page
                response.sendRedirect("success.jsp");
            } catch (Exception e) {
                e.printStackTrace();
                // Handle exceptions and redirect to the error page
                response.sendRedirect("error.jsp");
            } finally {
                // Close resources in a finally block
                try {
                    if (pstmt != null) {
                        pstmt.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            // Redirect to the error page
            response.sendRedirect("error.jsp");
        }
    }
}
