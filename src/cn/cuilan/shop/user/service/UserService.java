package cn.cuilan.shop.user.service;

import org.springframework.transaction.annotation.Transactional;

import cn.cuilan.shop.user.dao.UserDao;
import cn.cuilan.shop.user.entity.User;
import cn.cuilan.shop.utils.MailUtils;
import cn.cuilan.shop.utils.UUIDUtils;

/**
 * 用户模块业务层代码
 * 
 * @author 翠兰123
 * @date 2015年3月20日
 */
// 配置事物的注解
@Transactional
public class UserService {

	/** 注入UserDao */
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * 按用户名查询用户的方法
	 * 
	 * @param username用户名
	 * @return User对象
	 */
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	/**
	 * 业务层完成用户注册
	 * 
	 * @param user
	 */
	public void save(User user) {
		// 0代表用户未激活，1代表用户已经激活
		user.setState(0);
		// 生成一个64位的UUID
		String code = UUIDUtils.getUUID() + UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		// 发送激活邮件
		MailUtils.sendMail(user.getEmail(), code);
	}

	/**
	 * 业务层根据激活码查询用户
	 * 
	 * @param code
	 * @return
	 */
	public User findByCode(String code) {
		return userDao.findByCode(code);
	}

	/**
	 * 业务层修改用户状态的方法
	 * 
	 * @param existUser
	 */
	public void update(User existUser) {
		userDao.update(existUser);
	}

	/**
	 * 业务层用户登陆的方法
	 * 
	 * @param user
	 * @return
	 */
	public User login(User user) {
		return userDao.login(user);
	}

}
