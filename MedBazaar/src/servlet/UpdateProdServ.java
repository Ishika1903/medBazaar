package servlet;
//
//import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import operateDao.crud;
//
//@WebServlet("/UpdateProdServ")
//public class UpdateProdServ extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    
//    public UpdateProdServ() {
//       
//    }
//
//	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String pname=request.getParameter("pname");
//		String pid=request.getParameter("pid");
//		String mfname=request.getParameter("mfname");
//		String mgfdate=request.getParameter("mgfdate");
//		String exp=request.getParameter("expdate");
//		String mg=request.getParameter("power");
//		String qty=request.getParameter("quantity");
//		String price=request.getParameter("price");
//		
//		//run kr...oye  run karo
//		System.out.println(pname+" "+pid+" "+mfname+" "+mgfdate+" "+exp+" "+mg+" "+qty+" "+price);
//		
//		int status=crud.updateProd(pid, pname, mfname, mgfdate, exp, mg, qty, price);
//		
//		if(status>0) {
//			System.out.println("data entered....");
//			response.sendRedirect("viewProduct.jsp");
//		}
//		
//	}
//
//}

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

@WebServlet("/UpdateProdServ")
public class UpdateProdServ extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve product details from the form
        String pid = request.getParameter("pid");
        String pname = request.getParameter("pname");
        String mname = request.getParameter("mname");
        String mft_date = request.getParameter("mft_date");
        String exp_date = request.getParameter("exp_date");
        String power = request.getParameter("power");
        String quantity = request.getParameter("quantity");
        String price = request.getParameter("price");

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

            // Prepare the SQL update statement
            ps = con.prepareStatement(
                    "UPDATE tbl_product SET pname=?, mname=?, mft_date=?, exp_date=?, power=?, quantity=?, price=? WHERE pid=?");
            ps.setString(1, pname);
            ps.setString(2, mname);
            ps.setString(3, mft_date);
            ps.setString(4, exp_date);
            ps.setString(5, power);
            ps.setString(6, quantity);
            ps.setString(7, price);
            ps.setString(8, pid);

            // Execute the update
            ps.executeUpdate();
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

        // Redirect back to the product list page after the update
        response.sendRedirect("viewProduct.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}

