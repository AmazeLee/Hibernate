package com.sh.a_hello;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.sh.domain.Customer;

/**
 * 测试Hibernate框架
 * @author Administrator
 *这是一个测试
 */
public class Demo {
	//保存客户
	@Test
	public void fun1() {
		Configuration conf = new Configuration().configure();
		SessionFactory sessionFactory = conf.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Customer c = new Customer();
		c.setCust_name("百度公司");
		session.save(c);
		
		tx.commit();
		session.close();
		sessionFactory.close();
	}
}
