package com.sh.d_tx;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.sh.domain.Customer;
import com.sh.utils.HibernateUtils;
/**
 * 测试getCurrentSession
 * @author Administrator
 *
 */
public class Demo {
	
	@Test
	//返回同一个session
	public void fun1() {
		Session session1 = HibernateUtils.getCurrentSession();
		Session session2 = HibernateUtils.getCurrentSession();
		System.out.println(session1==session2);//true
	}
	
	@Test
	//返回不同的session
	public void fun2() {
		Session session1 = HibernateUtils.openSession();
		Session session2 = HibernateUtils.openSession();
		System.out.println(session1==session2);//false
	}
	
}
