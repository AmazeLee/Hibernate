package com.sh.e_fetchsize;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Test;

import com.sh.domain.Customer;
import com.sh.utils.HibernateUtils;


//抓取数量
public class Demo {
	
	@Test
	public void fun1(){
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		//----------------------------------------------------
		
		String hql = "from Customer ";
		Query query = session.createQuery(hql);
		List<Customer> list = query.list();
		
		for(Customer c:list){
			System.out.println(c.getLinkMens());
		}
		
		//----------------------------------------------------
		tx.commit();
		session.close();
		
	}
	
	
}
