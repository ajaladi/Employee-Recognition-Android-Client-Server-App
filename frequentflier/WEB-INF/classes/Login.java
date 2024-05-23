/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

/**
 *
 * @author Suresh ANDE
 */
@WebServlet("/login")
public class Login extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
     
    try {
        String user = request.getParameter("user");
        String pass = request.getParameter("pass"); 
        
        String pid = "";
        String un = "";
        String ps = "";
        
        int count = 0;
        
        PrintWriter out = response.getWriter();
        
        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
        String url = "jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
        Connection conn = DriverManager.getConnection(url, "sande", "bytchooz");
        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT PASSID, USERNAME, PASSWD FROM LOGIN");
        
        while (rs.next()) {
            String output = "";
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                output += rs.getObject(i + 1) + " ";       
            }
            
            String arr[] = output.split(" ");
            pid = arr[0];
            un = arr[1].trim();
            ps = arr[2].trim();
            
            if (user.equals(un) && pass.equals(ps)) { 
                count++;
                break;
            }
        }
        
        if (count == 0) {
            out.println("No");
        } else {
            out.println("Yes!" + pid);
        }
            
    } catch (Exception e) {
        e.printStackTrace();
    }
}

}
