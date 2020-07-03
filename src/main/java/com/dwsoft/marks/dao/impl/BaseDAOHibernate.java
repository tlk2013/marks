package com.dwsoft.marks.dao.impl;

import com.dwsoft.marks.dao.BaseDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class BaseDAOHibernate implements BaseDao {
	@Autowired
	protected SessionFactory sessionFactory;

	public Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession() ;
	}
	
	public Session getNewSession() {
		return this.sessionFactory.openSession();
	}
}
