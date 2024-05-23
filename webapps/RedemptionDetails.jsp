<%@page import="java.sql.*" %>

<%      
        
        String awid = request.getParameter("awardid");
        String psid = request.getParameter("pid");
        
         
        String query = "SELECT a.a_description AS \"Award Description\", "
             + "a.points_required AS \"Points Required\", "
             + "r.redemption_date AS \"Redemption Date\", "
             + "e.center_name AS \"Exchange Center Name\" "
             + "FROM Awards a "
             + "JOIN Redemption_History r ON a.award_id = r.award_id "
             + "JOIN ExchgCenters e ON r.center_id = e.center_id "
             + "WHERE r.passid ="+psid+" AND r.award_id ="+awid;

		 
        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
        String url="jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
        Connection conn=DriverManager.getConnection(url,"sande","glysacko");
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery(query);
        String output="";
      
        while(rs.next()){
            
            output+=rs.getObject(1)+","+rs.getObject(2)+","+rs.getObject(3)+","+rs.getObject(4)+"#";
            
            }
        conn.close();
        out.print(output);
    
%>




