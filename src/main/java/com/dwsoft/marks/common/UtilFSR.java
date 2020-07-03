package com.dwsoft.marks.common;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.logging.LogFactory;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class UtilFSR {
	private static org.apache.commons.logging.Log logg = LogFactory
			.getLog(UtilFSR.class);
	private static String app_key = "060c478b1114f1e2094c9ee6312a0498";
	//"b5428e4d368440f505a2a3d213bed9ac";// 建全
	// "060c478b1114f1e2094c9ee6312a0498";//官伟
	private static String proxyUser = "spk12@qq.com";
	//"846321894@qq.com"; // 建全
	// "spk12@qq.com";//官伟
	private static String proxyPwd = "Dwsoft123";
	//"Quan19920428qin";// 建全
	// "Dwsoft123";//官伟
	private static String num = "21";
	//"40";// 10000
	// "4"  1000
	public static JSONArray jSONArray = null;
//	static {
//		jSONArray = getJSONArray();
//	}

	public static void setJSONArray(JSONArray jSONArray2) {
		jSONArray = jSONArray2;
	}

	/**
	 * 提取ip
	 *
	 * @return
	 */
	public static JSONArray getJSONArray() {
		HttpURLConnection connection = null;
		InputStream is = null;
		JSONObject jsonResult = null;
		JSONArray arr = null;
		ByteArrayOutputStream baos = null;
		String path = "http://api.wandoudl.com/api/ip/list";
//		System.out.println(path);
		try {
			URL url = new URL(path);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(2000);
			connection.setReadTimeout(3000);
			connection.setDoOutput(true);
			connection.connect();
			OutputStream os = connection.getOutputStream();
			String params = "app_key=" + app_key
					+ "&pack=0&num="+num+"&xy=2&type=2&port=4&mr=1";
			os.write(params.getBytes("utf-8"));
			int responseCode = connection.getResponseCode();
			if (responseCode == 200) {
				is = connection.getInputStream();
				baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len = -1;
				while ((len = is.read(buffer)) != -1) {
					baos.write(buffer, 0, len);
				}
				String result = baos.toString();
				// ��ӡ���
				logg.warn("getIP>>>" + result);
				jsonResult = JSONObject.fromObject(result);
				if ("200".equals(jsonResult.get("code") + ""))
					arr = JSONArray.fromObject(jsonResult.get("data"));
//				logg.warn(arr);
			}
		} catch (Exception e) {
			logg.error("提取IP>>>>>>" + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (baos != null)
					baos.close();
				if (is != null)
					is.close();
				if (connection != null)
					connection.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return arr;
	}

	public static Proxy getProxy() {
		try {
			JSONObject jsonObject = null;
			if (jSONArray != null)
				jsonObject = jSONArray.getJSONObject(new Random()
						.nextInt(jSONArray.size()));
			String proxyHost = jsonObject.getString("ip");
			int proxyPort = Integer.parseInt(jsonObject.getString("port"));
//			logg.warn("proxy######{" + proxyHost + ":" + proxyPort + "}");
			Proxy.Type proxyType = Proxy.Type.HTTP;
//			String proxyUser = "spk12@qq.com";
//			String proxyPwd = "Dwsoft123";
//			Authenticator
//					.setDefault(new ProxyAuthenticator(proxyUser, proxyPwd));
			InetSocketAddress addr = new InetSocketAddress(proxyHost, proxyPort);
			Proxy proxy = new Proxy(proxyType, addr);
			return proxy;
		} catch (Exception e) {
			logg.error("getProxy()=========" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ��������ת�����ַ�
	 *
	 * @param inStream
	 * @return
	 * @throws IOException
	 */
	public static String IO2String(InputStream inStream) throws IOException {
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len;
		while ((len = inStream.read(buffer)) != -1) {
			result.write(buffer, 0, len);
		}
		String str = result.toString(StandardCharsets.UTF_8.name());
		return str;
	}

	static class ProxyAuthenticator extends Authenticator {
		private String authUser, authPwd;

		public ProxyAuthenticator(String authUser, String authPwd) {
			this.authUser = authUser;
			this.authPwd = authPwd;
		}

		public PasswordAuthentication getPasswordAuthentication() {
			return (new PasswordAuthentication(authUser, authPwd.toCharArray()));
		}
	}

	public static void main(String[] args) {
		System.out.println(getAreas());
	}

	/**
	 * 提取ip
	 *
	 * @return
	 */
	public static JSONArray getAreas() {
		HttpURLConnection connection = null;
		InputStream is = null;
		JSONObject jsonResult = null;
		JSONArray arr = null;
		ByteArrayOutputStream baos = null;
		String path = "http://api.wandoudl.com/api/areas/list";
		System.out.println(path);
		try {
			URL url = new URL(path);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(2000);
			connection.setReadTimeout(3000);
			connection.setDoOutput(true);
			connection.connect();
			OutputStream os = connection.getOutputStream();
			String params = "app_key=060c478b1114f1e2094c9ee6312a0498&pack=205465&num=4&xy=2&type=2&port=4&mr=1&area_id=411500";
			os.write(params.getBytes("utf-8"));
			int responseCode = connection.getResponseCode();
			if (responseCode == 200) {
				is = connection.getInputStream();
				baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len = -1;
				while ((len = is.read(buffer)) != -1) {
					baos.write(buffer, 0, len);
				}
				String result = baos.toString();
				// ��ӡ���
				System.out.println("getIP>>>" + result);
				jsonResult = JSONObject.fromObject(result);
				if ("200".equals(jsonResult.get("code") + ""))
					arr = JSONArray.fromObject(jsonResult.get("data"));
				logg.warn(arr);
			}
		} catch (Exception e) {
			logg.error("提取IP>>>>>>" + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (baos != null)
					baos.close();
				if (is != null)
					is.close();
				if (connection != null)
					connection.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return arr;
	}
}
