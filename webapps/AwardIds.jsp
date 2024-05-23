<%@page import="java.sql.*" %>

<%      
        
        String pasid = request.getParameter("pid");
         
        int passid=Integer.parseInt(pasid); 
        
	String query = "SELECT DISTINCT RH.award_id FROM Redemption_History RH JOIN Awards A ON RH.award_id = A.award_id WHERE RH.passid ="+passid; 
		 
        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
        String url="jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
        Connection conn=DriverManager.getConnection(url,"sande","glysacko");
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery(query);
        String output="";
      
        while(rs.next()){
            
            output+=rs.getObject(1)+"#";
            
            }
        conn.close();
        out.print(output);
    
%>





