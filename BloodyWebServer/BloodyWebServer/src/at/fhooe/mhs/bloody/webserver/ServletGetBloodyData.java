package at.fhooe.mhs.bloody.webserver;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import at.fhooe.mhs.bloody.webserver.dao.Dao;
import at.fhooe.mhs.bloody.webserver.model.BloodyData;

import com.google.gson.Gson;

public class ServletGetBloodyData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// same behavior as post
		doGet(req, resp);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
//		String country = req.getParameter("country");
//		List<BloodyData> bloodyData = Dao.INSTANCE.getBloodyData(country);
		Dao.INSTANCE.clearBloodyData();
		List<BloodyData> bloodyData = Dao.INSTANCE.getBloodyDataAll();
		
		Gson gson = new Gson();
		String json = gson.toJson(bloodyData);
		resp.getWriter().println(json);
	}
}
