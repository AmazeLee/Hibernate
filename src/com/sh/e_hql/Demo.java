package com.sh.e_hql;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Test;

import com.sh.domain.Customer;
import com.sh.utils.HibernateUtils;

/**
 * hql查询
 * 
 * @author Administrator
 *
 */
public class Demo {

	@Test
	// 基本查询
	public void fun1() {
		// 1.获得Session
		Session session = HibernateUtils.openSession();
		// 2.控制事务
		Transaction tx = session.beginTransaction();
		// 3.执行操作
		
		//hql操作-------------------------------
		
		//1>书写hql语句
		//String hql = "from com.sh.domain.Customer";
		String hql = "from Customer";
		//2>根据hql语句创建查询对象
		Query query = session.createQuery(hql);
		//3>根据查询对象获得查询结果
		List<Customer> list = query.list();
		//Object uniqueResult = query.uniqueResult();
		//Object result = query.getSingleResult();
		System.out.println(list);
		//-------------------------------------
		
		// 4.提交事务,关闭资源
		tx.commit();
		session.close();// 游离\托管状态 有id 没有关联
	}
	
	@Test
	// 条件查询
	//hql语句，不可能出现任何数据库相关的信息的
	public void fun2() {
		// 1.获得Session
		Session session = HibernateUtils.openSession();
		// 2.控制事务
		Transaction tx = session.beginTransaction();
		// 3.执行操作
		
		//hql操作-------------------------------
		
		//1>书写hql语句
		//String hql = "from com.sh.domain.Customer";
		String hql = "from Customer where cust_id=1";
		//2>根据hql语句创建查询对象
		Query query = session.createQuery(hql);
		//3>根据查询对象获得查询结果
		Customer result = (Customer) query.getSingleResult();
		System.out.println(result);
		//-------------------------------------
		
		// 4.提交事务,关闭资源
		tx.commit();
		session.close();// 游离\托管状态 有id 没有关联
	}

	@Test
	// 条件查询
	// 问号占位符的使用
	public void fun3() {
		// 1.获得Session
		Session session = HibernateUtils.openSession();
		// 2.控制事务
		Transaction tx = session.beginTransaction();
		// 3.执行操作
		//hql操作-------------------------------
		
		//1>书写hql语句
		//String hql = "from com.sh.domain.Customer";
		String hql = "from Customer where cust_id=?";
		//2>根据hql语句创建查询对象
		Query query = session.createQuery(hql);
		//设置参数
		query.setParameter(0,1l);
		//3>根据查询对象获得查询结果
		Customer result = (Customer) query.getSingleResult();
		System.out.println(result);
		//-------------------------------------
		
		// 4.提交事务,关闭资源
		tx.commit();
		session.close();// 游离\托管状态 有id 没有关联
	}
	
	@Test
	// 条件查询
	// 命名占位符的使用
	public void fun4() {
		// 1.获得Session
		Session session = HibernateUtils.openSession();
		// 2.控制事务
		Transaction tx = session.beginTransaction();
		// 3.执行操作
		//hql操作-------------------------------
		
		//1>书写hql语句
		//String hql = "from com.sh.domain.Customer";
		String hql = "from Customer where cust_id=:cust_id";
		//2>根据hql语句创建查询对象
		Query query = session.createQuery(hql);
		//设置参数
		query.setParameter("cust_id",1l);
		//3>根据查询对象获得查询结果
		Customer result = (Customer) query.getSingleResult();
		System.out.println(result);
		//-------------------------------------
		
		// 4.提交事务,关闭资源
		tx.commit();
		session.close();// 游离\托管状态 有id 没有关联
	}

	@Test
	// 分页查询
	// 命名占位符的使用
	public void fun5() {
		// 1.获得Session
		Session session = HibernateUtils.openSession();
		// 2.控制事务
		Transaction tx = session.beginTransaction();
		// 3.执行操作
		//hql操作-------------------------------
		
		//1>书写hql语句
		//String hql = "from com.sh.domain.Customer";
		String hql = "from Customer";
		//2>根据hql语句创建查询对象
		Query query = session.createQuery(hql);
		//设置分页信息
		query.setFirstResult(0);
		query.setMaxResults(1);
		//3>根据查询对象获得查询结果
		List<Customer> list = query.list();
		System.out.println(list);
		//-------------------------------------
		
		// 4.提交事务,关闭资源
		tx.commit();
		session.close();// 游离\托管状态 有id 没有关联
	}
}
