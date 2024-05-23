<%@page import="java.sql.*" %>

<%      
        
        String accountId = request.getParameter("pid");
         
		String query = "SELECT p.pname, SUM(f.flight_miles + t.trip_miles) AS total_points FROM Passengers p LEFT JOIN Flights f ON p.passid = f.passid LEFT JOIN Flights_Trips ft ON f.flight_id = ft.flight_id LEFT JOIN Trips t ON ft.trip_id = t.trip_id WHERE p.passid ="+accountId +"GROUP BY p.pname";


        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
        String url="jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
        Connection conn=DriverManager.getConnection(url,"sande","glysacko");
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery(query);
        String output="";
      
        while(rs.next()){
            
            output+=rs.getObject(1)+","+rs.getObject(2)+"#";
            
            }
        conn.close();
        out.print(output);
    
%>

