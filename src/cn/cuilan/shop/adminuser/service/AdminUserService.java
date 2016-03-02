package cn.cuilan.shop.adminuser.service;

import cn.cuilan.shop.adminuser.dao.AdminUserDao;
import cn.cuilan.shop.adminuser.entity.AdminUser;

/**
 * 后台管理的业务层
 * 
 * @author 翠兰123
 * @date 2015年3月26日
 */
public class AdminUserService {

	/** 注入AdminUserDao */
	private AdminUserDao adminUserDao;

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	/**
	 * 业务层登陆的方法
	 * 
	 * @param adminUser
	 * @return
	 */
	public AdminUser login(AdminUser adminUser) {
		return adminUserDao.login(adminUser);
	}

}
