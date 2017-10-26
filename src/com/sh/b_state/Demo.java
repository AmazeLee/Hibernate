package com.sh.b_state;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.sh.domain.Customer;
import com.sh.utils.HibernateUtils;
/**
 * 测试对象的三种状态
 * @author Administrator
 *
 */
public class Demo {
	
	@Test
	//查看三种状态
	public void fun1() {
		//1.获得Session
		Session session = HibernateUtils.openSession();
		//2.控制事务
		Transaction tx = session.beginTransaction();
		//3.执行操作
		Customer c = new Customer();//没有id，没有与session关联=>瞬时状态
		
		c.setCust_phone("联想");//瞬时状态
		
		session.save(c);//持久化状态，有id，有关联
		
		//4.提交事务,关闭资源
		tx.commit();
		session.close();//游离\托管状态 有id 没有关联
	}
	
	@Test
	/**
	 * 三种状态特点
	 * save方法：其实不能理解为保存，理解成将瞬时转态转换为持久状态
	 * 主键自增：执行save方法时，为了将对象转化为持久化状态，必须生成id值，所有需执行insert语句执行
	 * increment:执行save方法，为了生成id，会执行查询id最大的sql语句
	 */
	public void fun2() {
		//1.获得Session
		Session session = HibernateUtils.openSession();
		//2.控制事务
		Transaction tx = session.beginTransaction();
		//3.执行操作
		Customer c = new Customer();//没有id，没有与session关联=>瞬时状态
		
		c.setCust_name("联想");//瞬时状态
		
		session.save(c);//持久化状态，有id，有关联
		
		//4.提交事务,关闭资源
		tx.commit();
		session.close();//游离\托管状态 有id 没有关联
	}
	
	@Test
	/**
	 * 三种状态特点
	 * 持久化状态特点：持久化状态对象的任何变化都会同步到数据库中
	 */
	public void fun3() {
		//1.获得Session
		Session session = HibernateUtils.openSession();
		//2.控制事务
		Transaction tx = session.beginTransaction();
		//3.执行操作
		Customer c = session.get(Customer.class, 1l);
		c.setCust_name("阿里云");
		//4.提交事务,关闭资源
		tx.commit();
		session.close();//游离\托管状态 有id 没有关联
	}
}
