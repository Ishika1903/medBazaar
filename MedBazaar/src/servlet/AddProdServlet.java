//package servlet;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import operateDao.crud;
//
//
//@WebServlet("/AddProdServlet")
//public class AddProdServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//      public AddProdServlet() { }
//      
//	  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////			String pname=request.getParameter("pname");
////			String pid=request.getParameter("pid");
////			String mfname=request.getParameter("mfname");
////			String mgfdate=request.getParameter("mgfdate");
////			String exp=request.getParameter("expdate");
////			String mg=request.getParameter("power");
////			String qty=request.getParameter("quantity");
////			String price=request.getParameter("price");
////			String shope_owner=null;
////			String pin=null;
////			 HttpSession session=request.getSession(false); 
////			 if(session!=null){  
////				 shope_owner=(String)session.getAttribute("vname");  
////				 pin=(String)session.getAttribute("vpin");     
////			       System.out.println(shope_owner);
////			        }  
////			        else{  
////			            request.getRequestDispatcher("login.jsp").include(request, response);  
////			        }  
////			
////			
////			int status=crud.addProd(pid, pname, mfname, mgfdate, exp, mg, qty, price,pin,shope_owner);
////			
////			if(status>0) {
////				System.out.println("data entered....");
////				response.sendRedirect("viewProduct.jsp");
////			}
////		}
////
////		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////		
////		doGet(request, response);
//			    String pname = request.getParameter("pname");
//			    String pid = request.getParameter("pid");
//			    String mfname = request.getParameter("mfname");
//			    String mgfdate = request.getParameter("mgfdate");
//			    String exp = request.getParameter("expdate");
//			    String mg = request.getParameter("power");
//			    String qty = request.getParameter("quantity");
//			    String price = request.getParameter("price");
//			    String shope_owner = null;
//			    String pin = null;
//			    HttpSession session = request.getSession(false);
//			    if (session != null) {
//			        shope_owner = (String) session.getAttribute("vname");
//			        pin = (String) session.getAttribute("vpin");
//			    } else {
//			        request.getRequestDispatcher("login.jsp").include(request, response);
//			    }
//
//			    int status = 0;
//			    Connection con = null;
//			    PreparedStatement ps = null;
//
//			    try {
//			        // Set your database connection details
//			        String dbURL = "jdbc:mysql://localhost/online_medishopdb";
//			        String dbUser = "root";
//			        String dbPassword = "Ishika1903!";
//
//			        // Establish a database connection
//			        Class.forName("com.mysql.cj.jdbc.Driver");
//			        con = DriverManager.getConnection(dbURL, dbUser, dbPassword);
//
//			        if (con == null) {
//			            throw new SQLException("Failed to connect to the database");
//			        }
//
//			        ps = con.prepareStatement("INSERT INTO tbl_product (pid, pname, mname, mft_date, exp_date, power, quantity, price) values(?,?,?,?,?,?,?,?)");
//			        ps.setString(1, pid);
//			        ps.setString(2, pname);
//			        ps.setString(3, mfname);
//			        ps.setString(4, mgfdate);
//			        ps.setString(5, exp);
//			        ps.setString(6, mg);
//			        ps.setString(7, qty);
//			        ps.setString(8, price);
//
//			        status = ps.executeUpdate();
//			    } catch (SQLException e) {
//			        e.printStackTrace();
//			    } catch (ClassNotFoundException e) {
//			        e.printStackTrace();
//			    } finally {
//			        try {
//			            if (ps != null) {
//			                ps.close();
//			            }
//			            if (con != null) {
//			                con.close();
//			            }
//			        } catch (SQLException e) {
//			            e.printStackTrace();
//			        }
//			    }
//
//			    if (status > 0) {
//			        System.out.println("Data entered successfully.");
//			        response.sendRedirect("viewProduct.jsp");
//			    }
//	  }}

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
import javax.servlet.http.HttpSession;

@WebServlet("/AddProdServlet")
public class AddProdServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddProdServlet() { }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pname = request.getParameter("pname");
        String pid = request.getParameter("pid");
        String mfname = request.getParameter("mfname");
        String mgfdate = request.getParameter("mgfdate");
        String exp = request.getParameter("expdate");
        String mg = request.getParameter("power");
        String qty = request.getParameter("quantity");
        String price = request.getParameter("price");
        String shope_owner = null;
        String pincode = null;
        HttpSession session = request.getSession(false);
        if (session != null) {
            shope_owner = (String) session.getAttribute("vname");
            pincode = (String) session.getAttribute("vpin");
        } else {
            request.getRequestDispatcher("login.jsp").include(request, response);
        }

        int status = 0;
        Connection con = null;
        PreparedStatement ps = null;

        try {
            // Set your database connection details
            String dbURL = "jdbc:mysql://localhost/online_medishopdb";
            String dbUser = "root";
            String dbPassword = "Ishika1903!";

            // Establish a database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(dbURL, dbUser, dbPassword);

            if (con == null) {
                throw new SQLException("Failed to connect to the database");
            }

            ps = con.prepareStatement("INSERT INTO tbl_product (pid, pname, mname, mft_date, exp_date, power, quantity, price, pincode, shope_owner) values(?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, pid);
            ps.setString(2, pname);
            ps.setString(3, mfname);
            ps.setString(4, mgfdate);
            ps.setString(5, exp);
            ps.setString(6, mg);
            ps.setString(7, qty);
            ps.setString(8, price);
            ps.setString(9, pincode);
            ps.setString(10, shope_owner);

            status = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (status > 0) {
            System.out.println("Data entered successfully.");
            response.sendRedirect("viewProduct.jsp");
        }
    }
}
