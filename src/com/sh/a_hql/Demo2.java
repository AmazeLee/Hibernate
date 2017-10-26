package com.sh.a_hql;

import java.util.Arrays;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Test;

import com.sh.domain.Customer;
import com.sh.utils.HibernateUtils;

/**
 * @author Amaze_lee
 * @date 2017年10月18日 下午3:44:26 
 * @version V1.0  
 * @Description: 学习hql语法-多表查询语法（不常用）
 */
public class Demo2 {
	/**
	 * 原生SQL
	 * 交叉连接-笛卡尔积（避免）
	 * 	select * from A,B;
	 * 内连接
	 * 	- 隐式内连接
	 * 		select * from where b.aid = a,bid;
	 *  - 显式内连接
	 *  	select * from A inner join B on b.aid = a.bid;
	 * 外连接
	 *  - 左外
	 *  	select * from A left[outer] join B on b.aid = a.bid;
	 *  - 右外
	 *  	select * from A right[outer] join B on b.aid = a.bid;
	 *  
	 *  HQL的多表查询
	 *  内连接（迫切）
	 *  外连接
	 *   - 左外（迫切）
	 *   - 右外（迫切）
	 */
	@Test
	//HQL内连接 -- 将连接的对象分别返回，放到数组中
	public void fun1() {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		
		String hql = "from Customer c inner join c.linkMens";
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		for (Object[] arr : list) {
			System.out.println(Arrays.toString(arr));
		}
		
		tx.commit();
		session.close();
	}
	
	@Test
	//HQL内连接 迫切 -- 帮我们进行封装，返回的是一个对象
	public void fun2() {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		
		String hql = "from Customer c inner join fetch c.linkMens";
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		for (Object[] arr : list) {
			System.out.println(Arrays.toString(arr));
		}
		
		tx.commit();
		session.close();
	}
	
	@Test
	//HQL左外连接
	public void fun3() {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		
		String hql = "from Customer c left join c.linkMens";
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		for (Object[] arr : list) {
			System.out.println(Arrays.toString(arr));
		}
		
		tx.commit();
		session.close();
	}
	
	@Test
	//HQL右外连接
	public void fun4() {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		
		String hql = "from Customer c right join c.linkMens";
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		for (Object[] arr : list) {
			System.out.println(Arrays.toString(arr));
		}
		
		tx.commit();
		session.close();
	}
}
