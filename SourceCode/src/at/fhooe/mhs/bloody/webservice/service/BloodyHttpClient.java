package at.fhooe.mhs.bloody.webservice.service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class BloodyHttpClient {

//	private static String BASE_URL_GET = "http://10.0.2.2:8888/getbloodydata";
//	private static String BASE_URL_POST = "http://10.0.2.2:8888/addbloodydata";
	private static String BASE_URL_GET = "http://bloodywebserver.appspot.com/getbloodydata";
	private static String BASE_URL_POST = "http://bloodywebserver.appspot.com/addbloodydata";

	public String getBloodyData() {
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(BASE_URL_GET);
		HttpResponse responseGet;

		try {
			responseGet = client.execute(get);
			HttpEntity resEntityGet = responseGet.getEntity();

			if (resEntityGet != null) {
				return EntityUtils.toString(resEntityGet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean postBloodyData(String bloodyData) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(BASE_URL_POST);

		try {
			httpPost.setEntity(new StringEntity(bloodyData));
			httpclient.execute(httpPost);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}
}
