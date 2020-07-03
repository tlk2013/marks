package com.dwsoft.marks.service;


import com.dwsoft.marks.model.MarkInfo;
import com.dwsoft.marks.model.UserInfo;

public interface UserInfoService {
	/**
	 * ����ʶ��
	 * 
	 * @param mdn
	 * @return
	 */
	public UserInfo getUserInfoByMdn(String mdn);

	/**
	 * ͬ���ӽڵ�����
	 * 
	 * @param userInfo
	 */
	public void saveOrupdateUserInfo(UserInfo userInfo);

	public void savePhoneInfo(MarkInfo userInfo);

}
