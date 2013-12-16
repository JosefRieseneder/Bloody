package at.fhooe.mhs.bloody.webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import at.fhooe.mhs.bloody.webserver.dao.Dao;
import at.fhooe.mhs.bloody.webserver.model.BloodyData;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

@SuppressWarnings("serial")
public class ServletAddBloodyData extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// same behavior as post
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		System.out.println("Add new bloody data ");
		resp.setContentType("text/plain");

		BufferedReader reader = req.getReader();
		StringBuilder sb = new StringBuilder();
		String line = reader.readLine();
		while (line != null) {
			sb.append(line + "\n");
			line = reader.readLine();
		}
		reader.close();

		String data = sb.toString();

		try {
			Gson gson = new Gson();
			List<BloodyData> bloodyData = gson.fromJson(data,
					new TypeToken<List<BloodyData>>() {
					}.getType());
			for (BloodyData element : bloodyData) {
				Dao.INSTANCE.add(element);
			}
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		}
	}
}
