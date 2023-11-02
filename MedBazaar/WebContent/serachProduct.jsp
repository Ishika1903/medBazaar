<%-- <%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="connect.GetConnection"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<title>view Items</title>
</head>
<body>
	<jsp:include page="userheader.jsp"></jsp:include>

	<div class="w3-container" style="margin-top: 40px"">

		<div class="w3-container w3-card-4  "
			style="width: 1220px; height: 600px; margin-left: 50px; margin-top: 50px;"
			method="get">
			<table class="table table-bordered">
				<thead class="w3-red">
					<tr>
						<th scope="col">Sr.no</th>
						<th scope="col">Product name</th>
						<th scope="col">Manufacturer</th>
						<th scope="col">Mfg.Date</th>
						<th scope="col">Exp.Date</th>
						<th scope="col">Power</th>
						<th scope="col">Qty</th>
						<th scope="col">Price</th>
						<th scope="col">Gmap Link</th>
					</tr>
				</thead>
				<tbody>
					<%
					String srchMedi = request.getParameter("srchmedi");
					String srchpincode = request.getParameter("srchpincode");

					int no = 1;
					Connection con = GetConnection.getConnection();
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(
							"SELECT * FROM tbl_product WHERE pname LIKE '" + srchMedi + "%' and pincode='" + srchpincode + "'");

					while (rs.next()) {
					%>
					<tr>
						<td><%=no++%></td>
						<td><%=rs.getString(2)%></td>
						<td><%=rs.getString(3)%></td>
						<td><%=rs.getString(4)%></td>
						<td><%=rs.getString(5)%></td>
						<td><%=rs.getString(6)%></td>
						<td><%=rs.getString(7)%></td>
						<td><%=rs.getString(8)%></td>


						<!-- COPY -->
						<%
						Connection con1 = GetConnection.getConnection();
						Statement st1 = con1.createStatement();
						ResultSet rs1 = st1.executeQuery("SELECT * FROM vender_reg WHERE email='" + rs.getString(10) + "'");

						while (rs1.next()) {
						%>

						<td><a href="<%=rs1.getString(9)%>" target="_blank">Shop
								Location</a></td>

						<%
						} //Run kr k cheek kro
						rs1.close();
						%>
					</tr>
					<%
					}
					rs.close();
					if (no == 1)
					out.print("<center><h3 style=\"color:blue;\">This Combination of Medicine and PinCode does not exist</h3></center>");
					%>

				</tbody>
			</table>

		</div>
	</div>

</body>
</html> --%>

<%-- <%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="connect.GetConnection"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<title>View Items</title>
</head>
<body>
    <jsp:include page="userheader.jsp"></jsp:include>

    <div class="w3-container" style="margin-top: 40px">

        <form method="get" action="<%= request.getContextPath() %>/serachProduct.jsp">
            <!-- Add a form element for searching -->
            <input type="text" name="srchmedi" placeholder="Search Medicine">
            <input type="text" name="srchpincode" placeholder="Enter Pincode">
            <input type="submit" value="Search">
        </form>

        <div class="w3-container w3-card-4"
            style="width: 1220px; height: 600px; margin-left: 50px; margin-top: 50px;">
            <table class="table table-bordered">
                <thead class="w3-red">
                    <tr>
                        <th scope="col">Sr.no</th>
                        <th scope="col">Product name</th>
                        <th scope="col">Manufacturer</th>
                        <th scope="col">Mfg.Date</th>
                        <th scope="col">Exp.Date</th>
                        <th scope="col">Power</th>
                        <th scope="col">Qty</th>
                        <th scope="col">Price</th>
                        <th scope="col">Seller's Information</th>
                        <th scope="col">Additional Details</th>
                        <th scope="col">Gmap Link</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                    String srchMedi = request.getParameter("srchmedi");
                    String srchpincode = request.getParameter("srchpincode");

                    int no = 1;
                    Connection con = null;
                    Statement st = null;
                    ResultSet rs = null;

                    try {
                        con = GetConnection.getConnection();
                        st = con.createStatement();
                        rs = st.executeQuery(
                            "SELECT p.*, v.seller_info, v.additional_details, v.gmap_link " +
                            "FROM tbl_product p " +
                            "INNER JOIN vender_reg v ON p.email = v.email " +
                            "WHERE p.pname LIKE '%" + srchMedi + "%' and p.pincode='" + srchpincode + "'");

                        while (rs.next()) {
                    %>
                    <tr>
                        <td><%=no++%></td>
                        <td><%=rs.getString("pname")%></td>
                        <td><%=rs.getString("manufacturer")%></td>
                        <td><%=rs.getString("mfg_date")%></td>
                        <td><%=rs.getString("exp_date")%></td>
                        <td><%=rs.getString("power")%></td>
                        <td><%=rs.getString("qty")%></td>
                        <td><%=rs.getString("price")%></td>
                        <td><%=rs.getString("seller_info")%></td>
                        <td><%=rs.getString("additional_details")%></td>
                        <td><a href="<%=rs.getString("gmap_link")%>" target="_blank">Shop Location</a></td>
                    </tr>
                    <%
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        // Close all resources in a finally block
                        if (rs != null) {
                            rs.close();
                        }
                        if (st != null) {
                            st.close();
                        }
                        if (con != null) {
                            con.close();
                        }
                    }

                    if (no == 1) {
                        out.print("<center><h3 style=\"color:blue;\">This Combination of Medicine and PinCode does not exist</h3></center>");
                    }
                    %>

                </tbody>
            </table>

        </div>
    </div>
</body>
</html> --%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="connect.GetConnection"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<title>view Items</title>
</head>
<body>
	<jsp:include page="userheader.jsp"></jsp:include>

	<div class="w3-container" style="margin-top: 40px"">

		<div class="w3-container w3-card-4  "
			style="width: 1220px; height: 600px; margin-left: 50px; margin-top: 50px;"
			method="get">
			<table class="table table-bordered">
  <thead class="w3-red">
    <tr>
      <th scope="col">Sr.no</th>
      <th scope="col">Product name</th>
      <th scope="col">Manufacturer</th>
      <th scope="col">Mfg.Date</th>
      <th scope="col">Exp.Date</th>
      <th scope="col">Power</th>
      <th scope="col">Qty</th>
      <th scope="col">Price</th>
      <th scope="col">Gmap Link</th>
    </tr>
  </thead>
  <tbody>
       <%
             String srchMedi=request.getParameter("srchmedi");
             String srchpincode=request.getParameter("srchpincode");
             
					int no = 1;
 	        		Connection con=GetConnection.getConnection();
					Statement st=con.createStatement();
					ResultSet rs=st.executeQuery("SELECT * FROM tbl_product WHERE pname LIKE '"+srchMedi+"%' and pincode='"+srchpincode+"'");
					
					while (rs.next()) {
				%>
				<tr>
					<td><%=no++%></td>
					<td><%=rs.getString(2) %></td>
					<td><%=rs.getString(3)%></td>
					<td><%=rs.getString(4)%></td>
					<td><%=rs.getString(5)%></td>
					<td><%=rs.getString(6)%></td>
					<td><%=rs.getString(7)%></td>
					<td><%=rs.getString(8) %></td>
					
					
					<!-- COPY -->
					<%
					
					Connection con1=GetConnection.getConnection();
					Statement st1=con1.createStatement();
					ResultSet rs1=st1.executeQuery("SELECT * FROM vender_reg WHERE email='"+rs.getString(10)+"'");
					
					while (rs1.next()) { %>
					
					<td><a href="<%=rs1.getString(9)%>" target="_blank">Shop Location</a></td>
					
					 <%
			   		 } //Run kr k cheek kro
					rs1.close();
					%>
				</tr>
			    <%
			    } 
					rs.close();
					if(no==1)
						out.print("<center><h3 style=\"color:blue;\">This Combination of Medicine and PinCode does not exist</h3></center>");
			    %>
   
  </tbody>
</table>
			
			</div>
	</div>

</body>
</html>