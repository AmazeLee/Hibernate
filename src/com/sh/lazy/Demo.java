package com.sh.lazy;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.sh.domain.Customer;
import com.sh.utils.HibernateUtils;

/**
 * @author Amaze_lee
 * @date 2017年10月18日 下午7:15:49 
 * @version V1.0  
 * @Description: 懒加载/延迟加载
 */
public class Demo {
	@Test
	//get方法
	public void fun1() {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		
		Customer c = session.get(Customer.class,2l);
		System.out.println(c);
		
		tx.commit();
		session.close();
	}
	
	@Test
	//load方法 在执行时，不发送任何sql语句，返回一个对象，使用该对象时，才执行查询
	//延迟加载：仅仅获得没有使用，不会查询，在使用时才进行查询
	//是否对类进行延迟加载：可以通过在class元素上设置lazy属性来控制
		//lazy:true 加载时，不查询，使用时彩查询
		//lazy:false 加载时立即查询
	public void fun2() {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		
		Customer c = session.load(Customer.class,2l);
		System.out.println(c);
		
		tx.commit();
		session.close();
	}
}
