package com.sh.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	
	private static SessionFactory sf;
	static {
		// 1 创建,调用空参构造
		Configuration conf = new Configuration().configure();
		// 2 根据配置信息,创建 SessionFactory对象
		sf = conf.buildSessionFactory();
	}

	// 获得全新的Session
	public static Session openSession() {

		// 3 获得session
		Session session = sf.openSession();
		return session;
	}

	// 获得与线程绑定的Session
	public static Session getCurrentSession() {
		Session session = sf.getCurrentSession();
		return session;
	}
}
