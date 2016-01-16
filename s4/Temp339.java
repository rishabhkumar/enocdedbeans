import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import java.util.*;

public class Temp339 extends HttpServlet
{
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
    {
	String answers = req.getParameter("ans");
	String email = req.getParameter("email");
	res.setContentType("text/html");
	PrintWriter out = res.getWriter();
	String path = getServletContext().getRealPath("/Login.properties");
	try
	    {
		Properties  p = new Properties();
		FileInputStream fin = new FileInputStream(path);
		p.load(fin);
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/emp1", p);
		Statement s = c.createStatement();
		ResultSet rs1 = s.executeQuery("select ans from quiz_fest");
	        PreparedStatement ps = c.prepareStatement("update reg_fest set marks = ? where email = ?");
		int i = 0;
		int marks = 0;
		while(rs1.next())
		    {
			if(rs1.getString("ans").charAt(0) == answers.charAt(i))
			    {
				System.out.println("Correct");
				marks++;
			    }
			else
			    {
				System.out.println("Wrong");
			    }
			i++;
		    }
		ps.setString(2, email);
		ps.setInt(1, marks);
		ps.execute();
	        out.print("You Got " + marks + " marks!");
	    }
	catch(Exception e)
	    {
		System.out.println(e);
	    }
    }
}
