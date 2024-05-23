<%@page import="java.sql.*" %>

<%      
        
        String sopid = request.getParameter("spid");
        String depid = request.getParameter("dpid");
        String numpo = request.getParameter("npoints");
         
	
        String query1 = "UPDATE Point_Accounts SET total_points = total_points-"+numpo+" WHERE passid ="+sopid;
        String query2 = "UPDATE Point_Accounts SET total_points = total_points+"+numpo+" WHERE passid ="+depid;
        
        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
        String url="jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
        Connection conn=DriverManager.getConnection(url,"sande","glysacko");
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery(query1);
        ResultSet rs1=stmt.executeQuery(query2);
        String output="Transfer is successful";
      
        out.print(output);
    
%>




