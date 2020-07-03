package com.dwsoft.marks.dao;


import com.dwsoft.marks.model.MarkInfo;
import com.dwsoft.marks.model.UserInfo;

public interface UserInfoDao {
	/**
	 * ����ʶ��
	 * @param mdn
	 * @return
	 */
	public UserInfo getUserInfoByMdn(String mdn);
/**
 * 
 * @param userInfo
 */
	public void saveOrupdateUserInfo(UserInfo userInfo);

    void savePhoneInfo(MarkInfo userInfo);
}
