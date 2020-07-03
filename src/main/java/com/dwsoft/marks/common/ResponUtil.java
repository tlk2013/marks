package com.dwsoft.marks.common;


/**
 * 响应返回类
 *
 * @author tlk
 *
 */
public class ResponUtil {
	// 状态码
	// 1000-查询成功
	// 5000 查询异常
	private int resCode;
	// 提示信息
	private String resMsg;

	// 用户要返回给浏览器的数据
	private  Object data="";

	/**
	 * 自定义
	 *
	 * @param code
	 * @param msg
	 * @return
	 */
	public static ResponUtil respon(int code, String msg) {
		ResponUtil result = new ResponUtil();
		result.setResCode(code);
		result.setResMsg(msg);
		return result;
	}

	/**
	 * 成功且有数据
	 *
	 * @return
	 */
	public static ResponUtil success_yes(Object data) {
		ResponUtil result = new ResponUtil();
		result.setResCode(1000);
		result.setResMsg("请求成功,查得");
		result.setData(data);
		return result;
	}

	/**
	 * 成功且无数据
	 *
	 * @return
	 */
	public static ResponUtil success_no() {
		ResponUtil result = new ResponUtil();
		result.setResCode(1004);
		result.setResMsg("请求成功,查无");
		return result;
	}

	/**
	 * 失败
	 *
	 * @return
	 */
	public static ResponUtil error() {
		ResponUtil result = new ResponUtil();
		result.setResCode(5000);
		result.setResMsg("请求失败，服务异常");
		return result;
	}

	public int getResCode() {
		return resCode;
	}

	public void setResCode(int resCode) {
		this.resCode = resCode;
	}

	public String getResMsg() {
		return resMsg;
	}

	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
