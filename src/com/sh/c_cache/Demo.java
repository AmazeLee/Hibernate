package com.sh.c_cache;
/**
 * 测试一级缓存
 * @author Administrator
 *
 */

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.sh.domain.Customer;
import com.sh.utils.HibernateUtils;

public class Demo {
	
	@Test
	//证明一级缓存存在
	public void fun1() {
		//1.获得Session
		Session session = HibernateUtils.openSession();
		//2.控制事务
		Transaction tx = session.beginTransaction();
		//3.执行操作
		Customer c1 = session.get(Customer.class, 1L);
		Customer c2 = session.get(Customer.class, 1L);
		Customer c3 = session.get(Customer.class, 1L);
		Customer c4 = session.get(Customer.class, 1L);
		Customer c5 = session.get(Customer.class, 1L);
		
		//4.提交事务,关闭资源
		tx.commit();
		session.close();//游离\托管状态 有id 没有关联
	}
	
	@Test
	//
	public void fun2() {
		//1.获得Session
		Session session = HibernateUtils.openSession();
		//2.控制事务
		Transaction tx = session.beginTransaction();
		//3.执行操作
		Customer c1 = session.get(Customer.class, 1L);
		c1.setCust_name("网易");
		
		//4.提交事务,关闭资源
		tx.commit();
		session.close();//游离\托管状态 有id 没有关联
	}
	
	
	@Test
	//持久化状态对象就是放入session中的对象
	public void fun3() {
		//1.获得Session
		Session session = HibernateUtils.openSession();
		//2.控制事务
		Transaction tx = session.beginTransaction();
		//3.执行操作
		Customer c1 = new Customer();
		c1.setCust_id(1l);
		
		session.update(c1);//将c1放入session
		
		Customer C2 = session.get(Customer.class, 1l);
		
		//4.提交事务,关闭资源
		tx.commit();
		session.close();//游离\托管状态 有id 没有关联
	}
}
