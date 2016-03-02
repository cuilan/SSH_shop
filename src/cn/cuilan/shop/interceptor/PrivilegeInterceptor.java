package cn.cuilan.shop.interceptor;

import org.apache.struts2.ServletActionContext;

import cn.cuilan.shop.adminuser.entity.AdminUser;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 后台权限校验拦截器，对没有登录的用户拒绝访问
 * 
 * @author 翠兰123
 * @date 2015年4月13日
 */
public class PrivilegeInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = 1L;

	/**
	 * 执行拦截的方法
	 */
	@Override
	protected String doIntercept(ActionInvocation actionInvocation)
			throws Exception {
		// 判断session中是否保存了用户的信息
		AdminUser adminUser = (AdminUser) ServletActionContext.getRequest()
				.getSession().getAttribute("existAdminUser");
		if (adminUser == null) {
			// 没有登录进行访问
			ActionSupport actionSupport = (ActionSupport) actionInvocation
					.getAction();
			actionSupport.addActionError("您还没有登录，没有权限访问！");
			return "loginFail";
		} else {
			// 已经登录
			return actionInvocation.invoke();
		}
	}

}
