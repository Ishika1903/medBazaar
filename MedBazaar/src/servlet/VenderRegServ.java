package servlet; 
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

@WebServlet("/VenderRegServ")
public class VenderRegServ extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("fname");
        String lastName = request.getParameter("lname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phno");
        String vendorName = request.getParameter("vender");
        String address = request.getParameter("address");
        String pincode = request.getParameter("pin");
        String password = request.getParameter("pass1");
        String reEnterPassword = request.getParameter("pass2");
        String glink = request.getParameter("link"); // Added "glink" parameter

        
        if (password.equals(reEnterPassword)) {
            Connection con = null;
            PreparedStatement preparedStatement = null;

            try {
                // Connect to the database
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost/online_medishopdb", "root", "Ishika1903!");


            // Insert data into the database
            String insertQuery = "INSERT INTO vender_reg (fname, lname, email, phone, venderName, address, pincode, password, glink) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = con.prepareStatement(insertQuery);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, vendorName);
            preparedStatement.setString(6, address);
            preparedStatement.setString(7, pincode);
            preparedStatement.setString(8, password);
            preparedStatement.setString(9, glink);

            preparedStatement.executeUpdate();

            // Redirect to the success page
            response.sendRedirect("success.jsp");
        } catch (Exception e) {
            e.printStackTrace();}}}}
            // Handle exceptions and redirect to the error page

//            response.sendRedirect("error.jsp");
//        } finally {
//            // Close resources in a finally block
//            try {
//                if (preparedStatement != null) {
//                	preparedStatement.close();
//                }
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    } else {
//        // Redirect to the error page
//        response.sendRedirect("error.jsp");
//    }

