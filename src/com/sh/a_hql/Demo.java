package com.sh.a_hql;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Test;

import com.sh.domain.Customer;
import com.sh.utils.HibernateUtils;

/**
 * @author 
 * @date 2017年10月18日 下午3:44:26 
 * @version V1.0  
 * @Description: 学习hql语法
 */
public class Demo {
	
	@Test
	//基本语法
	public void fun1() {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		
		/*String hql ="from com.sh.domain.Customer";*///完整写法
		String hql = "from Customer";//简单写法
		Query query = session.createQuery(hql);
		List<Customer> list = query.list();
		System.out.println(list);
		
		tx.commit();
		session.close();
	}
	
	@Test
	//排序
	public void fun2() {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		
		String hql = "from Customer order by cust_id desc";//简单写法 asc desc
		Query query = session.createQuery(hql);
		List<Customer> list = query.list();
		System.out.println(list);
		
		tx.commit();
		session.close();
	}
	
	@Test
	//条件查询
	public void fun3() {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		
		String hql = "from Customer where cust_id=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, 2l);
		Customer c1 = (Customer) query.getSingleResult();
		Customer c2 = (Customer) query.uniqueResult();
		
		System.out.println(c1+"--"+c2);
		
		tx.commit();
		session.close();
	}
	
	@Test
	//分页查询
	public void fun4() {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		
		String hql = "from Customer";
		Query query = session.createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(2);
		List<Customer> list = query.list();
		System.out.println(list);
		tx.commit();
		session.close();
	}
	
	@Test
	/* 统计查询
	 * count 计数
	 * sum 求和
	 * avg 平均数
	 * max 最大值
	 * min 最小值
	 * */
	public void fun5() {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		
		String hql1 = "select count(*) from Customer";
		String hql2 = "select sum(cust_id) from Customer";
		String hql3 = "select avg(cust_id) from Customer";
		String hql4 = "select min(cust_id) from Customer";
		String hql5 = "select max(cust_id) from Customer";
		
		Query query = session.createQuery(hql5);
		Number result = (Number) query.uniqueResult();
		System.out.println(result);
		tx.commit();
		session.close();
	}
	
	@Test
	//投影查询
	public void fun6() {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		
		String hql1 = "select cust_name from Customer";
		String hql2 = "select cust_name,cust_id from Customer";
		String hql3 = "select new Customer(cust_id,cust_name) from Customer";
		Query query = session.createQuery(hql3);
		List list = query.list();
		System.out.println(list);
		tx.commit();
		session.close();
	}
}
