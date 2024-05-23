<%@page import="java.sql.*" %>

<%      
        
        String accountId = request.getParameter("pid");
         
	String query = "SELECT Flights.flight_id, Flights.flight_miles, Flights.destination FROM Flights INNER JOIN Passengers ON Flights.passid = Passengers.passid WHERE Passengers.passid ="+accountId; 
		 
        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
        String url="jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
        Connection conn=DriverManager.getConnection(url,"sande","glysacko");
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery(query);
        String output="";
      
        while(rs.next()){
            
            output+=rs.getObject(1)+","+rs.getObject(2)+","+rs.getObject(3)+"#";
            
            }
        conn.close();
        out.print(output);
    
%>


