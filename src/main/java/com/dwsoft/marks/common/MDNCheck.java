package com.dwsoft.marks.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.LogFactory;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MDNCheck {
	private static org.apache.commons.logging.Log logg = LogFactory
			.getLog(MDNCheck.class);
	public static void main(String[] args) {
		String mdn = "12599800123";
		String re = GetCheckBy360(mdn);
		if ("normal".equals(re)) {
			re = GetCheckByBD(mdn);
			if ("normal".equals(re)) {
				re = GetCheckBySG(mdn);
			}
		}
		Map<String, String> map = new HashMap<>();
		map.put(mdn, re);
		System.out.println(map);
	}

	/**
	 * 
	 * 通过百度识别
	 * 
	 */
	public static String GetCheckByBD(String mdn) {
//		System.out.println("》》》》》》》》chaxun baidu  statrt《《《《《《《《《《《《《《《");
		String url = "https://www.baidu.com/s?wd=" + mdn;
		String newText = "";
		try {
			Document doc = getDoc(url);
			Elements contents = doc
					.getElementsByClass("c-btn c-btn-primary op_mobilephone_btn OP_LOG_BTN");
//			System.out.println("baidu normal=>" + contents);
			if (contents.size() > 0)
				return "normal";
			contents = doc.getElementsByClass("op_fraudphone_word");
//			System.out.println("baidu check=>" + contents);
//			if (contents.size() > 0) {
//				if (contents != null && contents.size() > 0) {
//					Element content = contents.get(0);
//					newText = content.text();
//					if (!"1".equals(newText))
//						return newText;
//				}
//			}
			contents = doc.getElementsByTag("strong");
//			System.out.println("contents=>" + contents);
			if (contents != null && contents.size() > 0) {
				Element content = contents.get(0);
				newText = content.text();
				if (!"1".equals(newText))
					
					return newText;
			}
		} catch (Exception e) {
			logg.error("GetCheckByBD=>"+e.getMessage());
			e.printStackTrace();
		}
//		System.out.println("》》》》》》》》chaxun baidu  end《《《《《《《《《《《《《《《");
		return "normal";
	}

	private static Document getDoc(String url) throws IOException {
		Connection conn = Jsoup.connect(url).proxy(UtilFSR.getProxy()).timeout(50000);
//		conn = Jsoup.connect(url).timeout(50000);
				conn.header("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		conn.header("Accept-Encoding", "gzip, deflate, sdch");
		conn.header("Accept-Language", "zh-CN,zh;q=0.8");
		conn.header(
				"User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
		Document doc = conn.get();
		return doc;
	}

	/**
	 * 
	 * 通过360识别
	 */
	public static String GetCheckBy360(String mdn) {
//		System.out.println("》》》》》》》》chaxun 360   start《《《《《《《《《《《《《");
		String url = "https://www.so.com/s?q=" + mdn;
		String newText = "";
		try {
			Document doc = getDoc(url);
			Elements contents = doc.getElementsByClass("ser-btn js-mh-ser-btn");
//			System.out.println("360 normal=>" + contents);
			if (contents.size() > 0)
				return "normal";
			contents = doc.getElementsByClass("mohe-ph-mark");
//			System.out.println("360 check=>" + contents);
			if (contents != null && contents.size() > 0) {
				Element content = contents.get(0);
				newText = content.text();
				return newText;
			}
			contents = doc.getElementsByClass("refresh");
			if (contents != null && contents.size() > 0) {
//				logg.warn("GetCheckBy360=>收到360提示");
//				logg.warn("UtilFSR.setJSONArray(UtilFSR.getJSONArray())=>立即刷新(false)");
//				UtilFSR.setJSONArray(UtilFSR.getJSONArray());
			}
		} catch (Exception e) {
			logg.error("GetCheckBy360=>"+e.getMessage());
//			e.printStackTrace();
		}
//		System.out.println("》》》》》》》》chaxun 360   end《《《《《《《《《《《《《");
		return "normal";
	}

	/**
	 * 通过搜狗识别
	 * 
	 */
	public static String GetCheckBySG(String mdn) {
//		System.out.println("》》》》》》》》chaxun sougou  start《《《《《《《《《《《《《《");
		String url = "https://www.sogou.com/web?query=" + mdn;
		String newText = "";
		try {
			Document doc = getDoc(url);
//			Elements contents = doc.getElementsByTag("script");
			Element content = doc.getElementById("seccodeInput");
			if (content != null) {
//				logg.warn("GetCheckBySG=>收到sougou提示");
//				logg.warn("UtilFSR.setJSONArray(UtilFSR.getJSONArray())=>立即刷新(false)");
//				UtilFSR.setJSONArray(UtilFSR.getJSONArray());
			}
			 content = doc.getElementById("sogou_vr_10001001_sq_ext_0");
			if (content != null) {
				content = content.previousElementSibling();
				String list = content.data();
				System.out.println("contents check=>" + list);
				String atr[] = list.split(";");
				atr = atr[1].split(",");
				atr = atr[8].split("：");
				newText = atr[1];
				if (newText.length() > 1)
					return newText;
			}
		} catch (Exception e) {
			logg.error("GetCheckBySG=>"+e.getMessage());
//			e.printStackTrace();
		}
//		System.out.println("》》》》》》》》chaxun sougou  end《《《《《《《《《《《《《《");
		return "normal";
	}
}
