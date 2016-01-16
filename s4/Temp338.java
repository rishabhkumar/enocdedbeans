import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.*;
import java.io.*;

public class Temp338 extends HttpServlet
{
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
    {
	res.setContentType("text/html");
	PrintWriter out = res.getWriter();
	String path = getServletContext().getRealPath("/Login.properties");
	try
	    {
		Properties p = new Properties();
		FileInputStream fin = new FileInputStream(path);
		p.load(fin);
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/emp1", p);
		Statement s = c.createStatement();
		ResultSet rs1 = s.executeQuery("select ops from quiz_fest");
		String options = new String();
		int count = 1;
		while(rs1.next())
		    {
			options = options + rs1.getString("ops") + "@@";
			
		    }
		out.println(options);
	    }
	catch(Exception e)
	    {
		out.println(e);
	    }
    }
}
