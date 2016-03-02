package cn.cuilan.shop.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.cuilan.shop.user.entity.User;
import cn.cuilan.shop.user.service.UserService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 用户模块的Action类
 * 
 * @author 翠兰123
 * @date 2015年3月20日
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {

	private static final long serialVersionUID = 1L;

	/** 模型驱动要使用的对象 */
	private User user = new User();

	@Override
	public User getModel() {
		return user;
	}

	/** 接受验证码 */
	private String checkcode;

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	/** 注入UserService */
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 跳转到注册页面的执行方法
	 * 
	 * @return
	 */
	public String registerPage() {
		return "registerPage";
	}

	/**
	 * AJAX进行异步校验用户是否已经存在的方法
	 * 
	 * @return
	 * @throws IOException
	 */
	public String findByName() throws IOException {

		// 调用Service进行查询
		User existUser = userService.findByUsername(user.getUsername());

		// 获取response对象，向页面输出
		HttpServletResponse response = ServletActionContext.getResponse();

		// 设置页面的编码
		response.setCharacterEncoding("UTF-8");
		// 判断
		if (existUser != null) {
			// 查询到结果说明该用户已经存在
			response.getWriter().println(
					"<font color='red'>&nbsp;*该用户已经存在！</font>");
			System.out.println("该用户已经存在！");
		} else {
			// 没有查询到说明用户名可以使用
			response.getWriter().println(
					"<font color='green'>&nbsp;用户名可以使用！</font>");
			System.out.println("用户名可以使用！");
		}
		return NONE;
	}

	/**
	 * 用户注册请求方法
	 * 
	 * @return
	 */
	public String register() {
		// 判断验证码程序，从session中获取验证码的随机值
		String checkcodeFromSession = (String) ServletActionContext
				.getRequest().getSession().getAttribute("checkcode");
		// 判断验证码一致性
		if (!checkcode.equalsIgnoreCase(checkcodeFromSession)) {
			this.addActionError("您的验证码输入不正确！");
			return "checkcodeFail";
		}
		userService.save(user);
		this.addActionMessage("注册成功！请去邮箱激活！！！");
		return "msg";
	}

	/**
	 * 用户激活方法
	 * 
	 * @return
	 */
	public String active() {
		// 根据激活码查询用户
		User existUser = userService.findByCode(user.getCode());
		// 判断
		if (existUser == null) {
			// 激活码错误
			this.addActionMessage("激活失败：激活码错误！");
		} else {
			// 激活成功，修改用户状态
			existUser.setState(1);
			existUser.setCode(null);
			userService.update(existUser);
			this.addActionMessage("恭喜您！激活成功，请去登陆！");
		}
		return "msg";
	}

	/**
	 * 跳转到登陆页面
	 * 
	 * @return
	 */
	public String loginPage() {
		return "loginPage";
	}

	/**
	 * 登陆的方法，模型驱动自动获取用户名和密码
	 * 
	 * @return
	 */
	public String login() {
		User existUser = userService.login(user);
		// 判断
		if (existUser == null) {
			// 登陆失败
			this.addActionError("登陆失败！用户名或密码错误或用户未激活！");
			return LOGIN;
		} else {
			// 登陆成功，将用户信息存入Session，页面跳转
			ServletActionContext.getRequest().getSession()
					.setAttribute("existUser", existUser);
			return "loginSuccess";
		}
	}

	/**
	 * 用户退出方法
	 * 
	 * @return
	 */
	public String quit() {
		// 销毁Session
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}

}
