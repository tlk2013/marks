package com.dwsoft.marks.service.impl;

import com.dwsoft.marks.dao.UserInfoDao;
import com.dwsoft.marks.model.MarkInfo;
import com.dwsoft.marks.model.UserInfo;
import com.dwsoft.marks.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {
	@Autowired
	UserInfoDao userInfoDao;
	public UserInfo getUserInfoByMdn(String mdn) {
		return userInfoDao.getUserInfoByMdn(mdn);
	}

	public void saveOrupdateUserInfo(UserInfo userInfo) {
		userInfoDao.saveOrupdateUserInfo(userInfo);
	}

	@Override
	public void savePhoneInfo(MarkInfo userInfo) {
		userInfoDao.savePhoneInfo(userInfo);
	}

}
