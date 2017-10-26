package com.sh.many2many;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.sh.domain.Role;
import com.sh.domain.User;
import com.sh.utils.HibernateUtils;

/**
 * @author 
 * @date 2017年10月17日 下午4:52:58
 * @version V1.0
 * @Description: 多对多操作
 */
public class Demo {

	@Test
	// 保存客户以及客户下的联系人
	public void fun1() {
		// 1 获得session
		Session session = HibernateUtils.openSession();
		// 2 开启事务
		Transaction tx = session.beginTransaction();
		// -------------------------------------------------
		// 3操作
		// 1> 创建两个 User
		User u1 = new User();
		u1.setUser_name("豆腐西施");

		User u2 = new User();
		u2.setUser_name("玛丽莲.梦露");

		// 2> 创建两个 Role
		Role r1 = new Role();
		r1.setRole_name("演员");

		Role r2 = new Role();
		r2.setRole_name("名媛");
		// 3> 用户表达关系
		u1.getRoles().add(r1);
		u1.getRoles().add(r2);

		u2.getRoles().add(r1);
		u2.getRoles().add(r2);

		// 4> 角色表达关系
		r1.getUsers().add(u1);
		r1.getUsers().add(u2);

		r2.getUsers().add(u1);
		r2.getUsers().add(u2);

		// 5> 调用Save方法一次保存
		session.save(u1);
		session.save(u2);
		session.save(r1);
		session.save(r2);
		// -------------------------------------------------
		// 4提交事务
		tx.commit();
		// 5关闭资源
		session.close();
	}

	@Test
	//为豆腐西施新增一个角色
	public void fun2() {
		// 1 获得session
		Session session = HibernateUtils.openSession();
		// 2 开启事务
		Transaction tx = session.beginTransaction();
		// -------------------------------------------------
		// 3操作
		//1>获得角色
		User u = session.get(User.class,3l);
		//2>创建一个豆腐店老板
		Role r = new Role();
		r.setRole_name("明星");
		//3>将角色添加到用户中
		u.getRoles().add(r);
		//4>将角色转化为持久化
		/*session.save(r);*/
		// -------------------------------------------------
		// 4提交事务
		tx.commit();
		// 5关闭资源
		session.close();
	}

	@Test
	//为豆腐西施解除一个角色
	public void fun3() {
		// 1 获得session
		Session session = HibernateUtils.openSession();
		// 2 开启事务
		Transaction tx = session.beginTransaction();
		// -------------------------------------------------
		// 3操作
		//1>获得角色
		User u = session.get(User.class,3l);
		Role r = session.get(Role.class, 3l);
		//2>将角色从用户的角色中删除
		u.getRoles().remove(r);
		// -------------------------------------------------
		// 4提交事务
		tx.commit();
		// 5关闭资源
		session.close();
	}
}
