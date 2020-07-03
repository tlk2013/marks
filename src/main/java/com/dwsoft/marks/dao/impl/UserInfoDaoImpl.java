package com.dwsoft.marks.dao.impl;

import com.dwsoft.marks.dao.UserInfoDao;
import com.dwsoft.marks.model.MarkInfo;
import com.dwsoft.marks.model.UserInfo;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class UserInfoDaoImpl extends BaseDAOHibernate implements UserInfoDao {
	public UserInfo getUserInfoByMdn(String mdn) {
			Session session=getCurrentSession();
			
			String hql=" from UserInfo cu where 1=1 and cu.mdn='"+mdn+"'";
			Query q=session.createQuery(hql);
			return  (UserInfo) q.uniqueResult();
	}

	@Override
	public void saveOrupdateUserInfo(UserInfo userInfo) {
			Session session=getCurrentSession();
			session.saveOrUpdate(userInfo);
	}

	@Override
	public void savePhoneInfo(MarkInfo userInfo) {
		Session session=getCurrentSession();
		session.save(userInfo);
	}
}
