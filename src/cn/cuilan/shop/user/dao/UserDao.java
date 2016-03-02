package cn.cuilan.shop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.cuilan.shop.user.entity.User;

/**
 * 用户模块持久层代码
 * 
 * @author 翠兰123
 * @date 2015年3月20日
 */
public class UserDao extends HibernateDaoSupport {

	/**
	 * 按名次查询是否有该用户
	 * 
	 * @param username
	 * @return User对象
	 */
	@SuppressWarnings("unchecked")
	public User findByUsername(String username) {

		String hql = "from User where username = ?";
		List<User> list = this.getHibernateTemplate().find(hql, username);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 注册用户存入数据库
	 * 
	 * @param user
	 */
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

	/**
	 * 根据激活码查询用户
	 * 
	 * @param code
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public User findByCode(String code) {
		String hql = "from User where code = ?";
		List<User> list = this.getHibernateTemplate().find(hql, code);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 修改用户状态的方法
	 * 
	 * @param existUser
	 */
	public void update(User existUser) {
		this.getHibernateTemplate().update(existUser);
	}

	/**
	 * 用户登陆的方法
	 * 
	 * @param user
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public User login(User user) {
		String hql = "from User where username = ? and password = ? and state = ?";
		List<User> list = this.getHibernateTemplate().find(hql,
				user.getUsername(), user.getPassword(), 1);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}
