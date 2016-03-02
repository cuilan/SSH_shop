package cn.cuilan.shop.adminuser.action;

import org.apache.struts2.ServletActionContext;

import cn.cuilan.shop.adminuser.entity.AdminUser;
import cn.cuilan.shop.adminuser.service.AdminUserService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台用户登陆的Action
 * 
 * @author 翠兰123
 * @date 2015年3月26日
 */
public class AdminUserAction extends ActionSupport implements
		ModelDriven<AdminUser> {

	private static final long serialVersionUID = 1L;

	/** 模型驱动需要使用的对象 */
	private AdminUser adminUser = new AdminUser();

	@Override
	public AdminUser getModel() {
		return adminUser;
	}

	/** 注入Service */
	private AdminUserService adminUserService;

	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}

	/**
	 * 后台管理的Action
	 * 
	 * @return
	 */
	public String login() {
		// 调用Service完成登陆
		AdminUser existAdminUser = adminUserService.login(adminUser);
		if (existAdminUser == null) {
			// 登陆失败
			this.addActionError("您的用户名或密码错误！");
			return "loginFail";
		} else {
			// 登陆成功
			ServletActionContext.getRequest().getSession()
					.setAttribute("existAdminUser", existAdminUser);
			return "loginSuccess";
		}
	}

}
