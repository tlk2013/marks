package com.dwsoft.marks.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {
	public static boolean isEmpty(String inStr) {
		boolean retBoo = false;
		if (inStr == null || "".equals(inStr) || "null".equals(inStr)
				|| "".equals(inStr.trim())) {
			retBoo = true;
		}
		return retBoo;
	}

	/**
	 * 0:"yyyy-MM-dd";1:"yyyy-MM-dd HH:mm:ss";
	 * 
	 * @throws Exception
	 **/
	public static Date stringToDate(String str, int in) throws Exception {
		if (isEmpty(str))
			return null;
		String format = "";
		if (in == 0)
			format = "yyyy-MM-dd";
		if (in == 1) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sformat = new SimpleDateFormat(format);
		Date cDate = null;
		try {
			cDate = sformat.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return cDate;

	}

	/**
	 * 0:"yyyy-MM-dd";1:"yyyy-MM-dd HH:mm:ss";
	 * 
	 * @throws Exception
	 **/
	public static String dateToString(Date str, int in) {
		if (str == null || isEmpty(str + ""))
			return null;
		String format = "";
		if (in == 0) {
			format = "yyyy-MM-dd";
		} else if (in == 1) {
			format = "yyyy-MM-dd HH:mm:ss";
		} else {
			format = "yyyy-MM-dd HH:mm:ss.SSS";
		}
		SimpleDateFormat sformat = new SimpleDateFormat(format);
		String cDate = null;
		try {
			cDate = sformat.format(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cDate;
	}
}
