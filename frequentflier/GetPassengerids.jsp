<%@page import="java.sql.*" %>

<%      
        
        String passId = request.getParameter("pid");
         
	String query = "SELECT passid FROM Passengers WHERE passid NOT IN (SELECT passid FROM Passengers WHERE passid = "+passId+")"; 
		 
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



