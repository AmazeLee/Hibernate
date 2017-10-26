package com.sh.f_sql;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.junit.Test;

import com.sh.domain.Customer;
import com.sh.utils.HibernateUtils;

/**
 * 原生sql查询
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

		// sql操作-------------------------------

		// 1>书写sql语句
		String sql = "select * from cst_customer";
		// 2>根据sql语句创建查询对象
		NativeQuery query = session.createNativeQuery(sql, Customer.class);
		// 3>根据查询对象获得查询结果
		List<Customer> list = query.list();
		// Object uniqueResult = query.uniqueResult();
		// Object result = query.getSingleResult();
		System.out.println(list);
		// -------------------------------------

		// 4.提交事务,关闭资源
		tx.commit();
		session.close();// 游离\托管状态 有id 没有关联
	}

	@Test
	// 条件查询
	public void fun2() {
		// 1.获得Session
		Session session = HibernateUtils.openSession();
		// 2.控制事务
		Transaction tx = session.beginTransaction();
		// 3.执行操作

		// sql操作-------------------------------

		// 1>书写sql语句
		String sql = "select * from cst_customer where cust_id=?";
		// 2>根据sql语句创建查询对象
		NativeQuery query = session.createNativeQuery(sql, Customer.class);
		//设置参数
		query.setParameter(1,1);
		// 3>根据查询对象获得查询结果
		List<Customer> list = query.list();
		// Object uniqueResult = query.uniqueResult();
		// Object result = query.getSingleResult();
		System.out.println(list);
		// -------------------------------------

		// 4.提交事务,关闭资源
		tx.commit();
		session.close();// 游离\托管状态 有id 没有关联
	}

	@Test
	// 分页查询
	public void fun3() {
		// 1.获得Session
		Session session = HibernateUtils.openSession();
		// 2.控制事务
		Transaction tx = session.beginTransaction();
		// 3.执行操作
		// hql操作-------------------------------

		// 1>书写hql语句
		// String hql = "from com.sh.domain.Customer";
		String sql = "select * from cst_customer limit ?,?";
		// 2>根据sql语句创建查询对象
		NativeQuery query = session.createNativeQuery(sql, Customer.class);
		//设置分页信息
		query.setParameter(1, 1);
		query.setParameter(2, 1);
		// 3>根据查询对象获得查询结果
		List<Customer> list = query.list();
		System.out.println(list);
		// -------------------------------------

		// 4.提交事务,关闭资源
		tx.commit();
		session.close();// 游离\托管状态 有id 没有关联
	}
}
