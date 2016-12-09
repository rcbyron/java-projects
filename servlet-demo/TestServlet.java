import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   response.setContentType("text/html");
	   PrintWriter out = response.getWriter();
	   String ref = request.getHeader("referer");
	   int first = Integer.parseInt(request.getParameter("int1"));
	   int second = Integer.parseInt(request.getParameter("int2"));
	   String docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\n";
	   out.println(docType +
			       "<html>\n" +
			   	   "<head><title>My Awesome Servlet</title></head>\n" + "<body><h1>The sum of "+first+" and "+second+" is "+(first+second)+"</h1>\n" +
			       "Referred by: "+ ref + 
			       "</body></html>");
	   }
}