import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import java.util.*;

public class Temp337 extends HttpServlet
{
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
    {
	res.setContentType("text/html");
	PrintWriter out = res.getWriter();
	try
	    {
		Properties p = new Properties();
		String path = getServletContext().getRealPath("/Login.properties");
		FileInputStream fin = new FileInputStream(path);
		p.load(fin);
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/emp1", p);
		Statement s = c.createStatement();
		ResultSet rs1 = s.executeQuery("select ques from quiz_fest");
		String questions = new String();
		while(rs1.next())
		    {
			questions = questions + rs1.getString("ques") + "@@";
		    }
		out.println(questions);
	    }
	catch(Exception e)
	    {
		out.println(e);
	    }
    }
}
