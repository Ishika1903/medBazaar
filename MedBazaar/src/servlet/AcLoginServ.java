//package servlet;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import operateDao.crud;
//
//@WebServlet("/AcLoginServ")
//public class AcLoginServ extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    public AcLoginServ() {
//
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//        String accType = request.getParameter("accountType");
//        String userName = request.getParameter("username");
//        String pswd = request.getParameter("pswd");
//        String q = null;
//
//        if (accType.equals("Select Account Type")) {
//            RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
//            rd.include(request, response);
//            out.println("<script type=\"text/javascript\">");
//            out.println("alert('Please Select Account Type');");
//            out.println("</script>");
//        } else {
//            if (accType.equals("User")) {
//                q = "SELECT * FROM usr_reg WHERE email = ?";
//            } else {
//                q = "SELECT * FROM vender_reg WHERE email = ?";
//            }
//
//            Connection con = null;
//            PreparedStatement pstmt = null;
//            ResultSet rs = null;
//
//            try {
//                // Connect to the database
//                Class.forName("com.mysql.cj.jdbc.Driver");
//                con = DriverManager.getConnection("jdbc:mysql://localhost/online_medishopdb", "root", "Ishika1903!");
//
//                // Create a prepared statement
//                pstmt = con.prepareStatement(q);
//                pstmt.setString(1, userName);
//
//                // Execute the query
//                rs = pstmt.executeQuery();
//
//                if (rs.next()) {
//                    String dbPassword = rs.getString("password");
//
//                    if (pswd.equals(dbPassword)) {
//                        if (accType.equals("User")) {
//                            HttpSession session = request.getSession();
//                            session.setAttribute("uname", userName);
//                            RequestDispatcher rd = request.getRequestDispatcher("/medisearch.jsp");
//                            rd.forward(request, response);
//                        } else {
//                            String pin = crud.getPin(userName);
//                            HttpSession session = request.getSession();
//                            session.setAttribute("vname", userName);
//                            session.setAttribute("vpin", pin);
//
//                            RequestDispatcher rd = request.getRequestDispatcher("/viewProduct.jsp");
//                            rd.forward(request, response);
//                        }
//                    } else {
//                        RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
//                        rd.include(request, response);
//                        out.print("<center><h3 style=\"color:red;\">Incorrect password</h3></center>");
//                    }
//                } else {
//                    RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
//                    rd.include(request, response);
//                    out.print("<center><h3 style=\"color:red;\">User not found</h3></center>");
//                }
//            } catch (ClassNotFoundException | SQLException e) {
//                e.printStackTrace();
//                response.sendRedirect("error.jsp");
//            } finally {
//                // Close resources in a finally block
//                try {
//                    if (rs != null) {
//                        rs.close();
//                    }
//                    if (pstmt != null) {
//                        pstmt.close();
//                    }
//                    if (con != null) {
//                        con.close();
//                    }
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//}

package servlet; 
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import operateDao.crud;

@WebServlet("/AcLoginServ")
public class AcLoginServ extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AcLoginServ() {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String accType = request.getParameter("accountType");
        String userName = request.getParameter("username");
        String pswd = request.getParameter("pswd");
        String q = null;

        if (accType.equals("Select Account Type")) {
            RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
            rd.include(request, response);
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Please Select Account Type');");
            out.println("</script>");
        } else {
            if (accType.equals("User")) {
                q = "SELECT * FROM usr_reg WHERE email = ?";
            } else {
                q = "SELECT * FROM vender_reg WHERE email = ?";
            }

            Connection con = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {
                // Connect to the database
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost/online_medishopdb", "root", "Ishika1903!");

                // Create a prepared statement
                pstmt = con.prepareStatement(q);
                pstmt.setString(1, userName);

                // Execute the query
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    String dbPassword = rs.getString("password");

                    if (pswd.equals(dbPassword)) {
                        if (accType.equals("User")) {
                            HttpSession session = request.getSession();
                            session.setAttribute("uname", userName);
                            RequestDispatcher rd = request.getRequestDispatcher("/medisearch.jsp");
                            rd.forward(request, response);
                        } else {
                            String pin = crud.getPin(userName);
                            HttpSession session = request.getSession();
                            session.setAttribute("vname", userName);
                            session.setAttribute("vpin", pin);

                            RequestDispatcher rd = request.getRequestDispatcher("/viewProduct.jsp");
//                            RequestDispatcher rd = request.getRequestDispatcher("success.jsp");
                            rd.forward(request, response);
                        }
                    } else {
                        RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
                        rd.include(request, response);
                        out.print("<center><h3 style=\"color:red;\">Incorrect password</h3></center>");
                    }
                } else {
                    RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
                    rd.include(request, response);
                    out.print("<center><h3 style=\"color:red;\">User not found</h3></center>");
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp");
            } finally {
                // Close resources in a finally block
                try {
                    if (rs != null) {
                        rs.close();
                    }
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
        }
    }
}