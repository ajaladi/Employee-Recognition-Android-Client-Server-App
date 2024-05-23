<%@page import="java.sql.*" %>

<%      
        
        String fid = request.getParameter("flightid");
         
	String query = "SELECT f.dept_datetime, f.arrival_datetime, f.flight_miles, t.trip_id, t.trip_miles FROM Flights f JOIN Flights_Trips fp ON f.flight_id = fp.flight_id JOIN Trips T ON fp.trip_id = t.trip_id WHERE f.flight_id ="+"'"+fid+"'"; 
		 
        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
        String url="jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
        Connection conn=DriverManager.getConnection(url,"sande","glysacko");
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery(query);
        String output="";
      
        while(rs.next()){
            
            output+=rs.getObject(1)+","+rs.getObject(2)+","+rs.getObject(3)+","+rs.getObject(4)+","+rs.getObject(5)+"#";
            
            }
        conn.close();
        out.print(output);
    
%>



