package com.sh.b_criteria;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.sh.domain.Customer;
import com.sh.utils.HibernateUtils;

/**
 * @author
 * @date 2017年10月18日 下午3:44:26
 * @version V1.0
 * @Description: Criteria离线查询
 */
public class Demo2 {

	@Test
	// Criteria离线查询
	public void fun1() {
		//Service层/web层
		DetachedCriteria dc = DetachedCriteria.forClass(Customer.class);
		dc.add(Restrictions.idEq(6l));//拼装条件（全部与普通Criteria一致）
		
		
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();

		Criteria c = dc.getExecutableCriteria(session);
		List<Customer> list = c.list();
		System.out.println(list);

		tx.commit();
		session.close();
	}
}
