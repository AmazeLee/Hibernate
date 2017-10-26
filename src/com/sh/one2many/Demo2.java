package com.sh.one2many;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.sh.domain.Customer;
import com.sh.domain.LinkMan;
import com.sh.utils.HibernateUtils;

/**
 * @author 李书华
 * @date 2017年10月17日 下午4:52:58 
 * @version V1.0  
 * @Description: 一对多，多对一操作
 */
public class Demo2 {
	
	@Test
	//保存客户以及客户下的联系人
	//cascade:save-update
	public void fun1() {
		//1.获得sesison
		Session session = HibernateUtils.openSession();
		//2.开启事务
		Transaction tx = session.beginTransaction();
		
		//3.操作
		Customer c = new Customer();
		c.setCust_name("小米科技");
		
		LinkMan lm1 = new LinkMan();
		lm1.setLkm_name("雷军");
		
		LinkMan lm2 = new LinkMan();
		lm2.setLkm_name("林斌");
		
		//表达一对多，客户下有多个联系人
		c.getLinkMens().add(lm1);
		c.getLinkMens().add(lm2);
		
		//表达多对一，联系人属于哪个客户
		lm1.setCustomer(c);
		lm2.setCustomer(c);
		session.save(c);
		/*session.save(lm1);
		session.save(lm2);*/
		
		//4.提交事务
		tx.commit();
		//5.关闭资源
		session.close();
	}
	
	@Test
	//测试删除客户时，级联删除客户下的联系人
	public void fun2() {
		//1.获得sesison
		Session session = HibernateUtils.openSession();
		//2.开启事务
		Transaction tx = session.beginTransaction();
		
		//3.操作
		//1>获得要操作的客户对象
		Customer c = session.get(Customer.class,1l);
		//2>调用delete删除客户
		session.delete(c);
		
		//4.提交事务
		tx.commit();
		//5.关闭资源
		session.close();
	}
}
