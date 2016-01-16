import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;;
import java.sql.*;
import java.util.*;

public class Temp336 extends HttpServlet
{
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
    {
	res.setContentType("text/html");
	PrintWriter out = res.getWriter();
	String path = getServletContext().getRealPath("/Login.properties");
	String name = req.getParameter("name");
	String phone = req.getParameter("phone");
	String email = req.getParameter("email");
	String team = req.getParameter("team");
	String name2 = req.getParameter("name2");
	try
	    {
		Properties p = new Properties();
		FileInputStream fin = new FileInputStream(path);
		p.load(fin);
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/emp1", p);
		Statement ss = c.createStatement();
		ResultSet rs1 = ss.executeQuery("select * from reg_fest where email = '" + email + "'");
		if(!rs1.next())
		    {
			out.print("1");
			PreparedStatement ps = c.prepareStatement("insert into reg_fest(name, phone, email, team, name2) values(?, ?, ?, ?, ?)");
			ps.setString(1, name);
			ps.setString(2, phone);
			ps.setString(3, email);
			ps.setString(4, team);
			ps.setString(5, name2);
			ps.execute();
		    }
		else
		    {
			out.print("2");
		    }
	    }
	catch(Exception e)
	    {
		out.print(e);
	    }
    }
}
