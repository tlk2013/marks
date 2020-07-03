package com.dwsoft.marks.model;


/**
 *
 * @author tlk
 * @date 2019-7-25 下午3:51:24
 * @version V1.0
 */

public class UserInfo {
	private String mdn;//手机号
	private String url;//识别url
	private String ctime;//录入时间
	private String utime;//更新时间
	private String mark;//备注
	private String status;//业务状态码 1：开通  2：已取消
	public UserInfo() {
		super();
	}

	public UserInfo(String mdn, String url, String ctime, String utime,
					String mark, String status) {
		super();
		this.mdn = mdn;
		this.url = url;
		this.ctime = ctime;
		this.utime = utime;
		this.mark = mark;
		this.status = status;
	}

	public String getMdn() {
		return mdn;
	}
	public void setMdn(String mdn) {
		this.mdn = mdn;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	public String getUtime() {
		return utime;
	}
	public void setUtime(String utime) {
		this.utime = utime;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String toString() {
		return "UserInfo [mdn=" + mdn + ", url=" + url + ", ctime=" + ctime
				+ ", utime=" + utime + ", mark=" + mark + ", status=" + status
				+ "]";
	}

}
