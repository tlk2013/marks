package com.dwsoft.marks.model;


/**
 *
 * @author tlk
 * @date 2019-7-25 下午3:51:24
 * @version V1.0
 */

public class MarkInfo {
	private Integer mid;//
	private String mdn;//
	private String mark;//
	private String date;//
	private String source="1";//

	public MarkInfo() {
	}

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public String getMdn() {
		return mdn;
	}

	public void setMdn(String mdn) {
		this.mdn = mdn;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
