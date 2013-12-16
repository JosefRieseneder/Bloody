package at.fhooe.mhs.bloody.webserver;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class BloodyWebServerServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}
}
