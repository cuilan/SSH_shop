package cn.cuilan.shop.order.action;

import java.io.IOException;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import cn.cuilan.shop.cart.entity.Cart;
import cn.cuilan.shop.cart.entity.CartItem;
import cn.cuilan.shop.order.entity.Order;
import cn.cuilan.shop.order.entity.OrderItem;
import cn.cuilan.shop.order.service.OrderService;
import cn.cuilan.shop.user.entity.User;
import cn.cuilan.shop.utils.PageBean;
import cn.cuilan.shop.utils.PaymentUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 订单管理的Action
 * 
 * @author 翠兰123
 * @date 2015年3月25日
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order> {

	private static final long serialVersionUID = 1L;

	/** 模型驱动需要使用的对象 */
	private Order order = new Order();

	@Override
	public Order getModel() {
		return order;
	}

	/** 注入OrderService */
	private OrderService orderService;

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	/** 接受页面的page */
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}

	/** 接受支付通道的编码 */
	private String pd_FrpId;

	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}

	/** 接受付款成功后的相应数据 */
	private String r6_Order;

	/** 接受付款成功后的相应数据 */
	private String r3_Amt;

	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}

	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	/**
	 * 生成订单的方法
	 * 
	 * @return
	 */
	public String save() {
		// 1.保存数据到数据库
		order.setOrdertime(new Date());
		// 订单状态：1未付款 2已付款，但是没有发货 3已经发货，没有确认收货 4交易完成
		order.setState(1);
		// 从Session中获取购物车
		Cart cart = (Cart) ServletActionContext.getRequest().getSession()
				.getAttribute("cart");
		if (cart == null) {
			this.addActionError("亲，没有您的商品！请先去购物！！！");
			return "msg";
		}
		// 总计的数据是购物车中的信息
		order.setTotal(cart.getTotal());
		// 设置订单中的订单项
		for (CartItem cartItem : cart.getCartItems()) {
			// 创建订单项对象
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			// 将订单项添加到订单Set的集合中
			order.getOrderItems().add(orderItem);
		}
		// 订单所属用户
		User existUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		if (existUser == null) {
			this.addActionError("亲，您还没有登录！请先登录！！！");
			return "login";
		}
		order.setUser(existUser);
		orderService.save(order);
		// 2.将订单对象显示到页面上（值栈中）
		// 清空购物车
		cart.clearCart();
		return "saveSuccess";
	}

	/**
	 * 我的订单的查询
	 * 
	 * @return
	 */
	public String findByUid() {
		// 根据用户ID查询
		User user = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		// 调用Service，返回的list封装到PageBean对象中
		PageBean<Order> pageBean = orderService.findByPageUid(user.getUid(),
				page);
		// 将分页的数据显示到页面上
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUidSuccess";
	}

	/**
	 * 根据顶大ID查询订单的方法
	 * 
	 * @return
	 */
	public String findByOid() {
		order = orderService.findByOid(order.getOid());
		return "findByOidSuccess";
	}

	/**
	 * 为订单付款的方法
	 * 
	 * @return
	 * @throws IOException
	 */
	public String payOrder() throws IOException {
		// 修改订单
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setAddr(order.getAddr());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());
		orderService.update(currOrder);

		// 为订单付款
		String p0_Cmd = "Buy"; // 业务类型
		String p1_MerId = "10001126856"; // 商户编号
		String p2_Order = order.getOid().toString(); // 订单号
		String p3_Amt = "0.01"; // 付款金额
		String p4_Cur = "CNY"; // 交易币种
		String p5_Pid = ""; // 商品名称
		String p6_Pcat = ""; // 商品种类
		String p7_Pdesc = ""; // 商品描述
		String p8_Url = "http://localhost:8080/SSH_shop/order_callBack.action"; // 商户接收支付成功数据的地址
		String p9_SAF = ""; // 送货地址
		String pa_MP = ""; // 商户扩展信息
		String pd_FrpId = this.pd_FrpId; // 支付通道编码
		String pr_NeedResponse = "1"; // 应答机制
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl"; // 算法秘钥
		// 签名数据
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue);

		// 向易宝发出
		StringBuffer stringBuffer = new StringBuffer(
				"https://www.yeepay.com/app-merchant-proxy/node?");
		stringBuffer.append("p0_Cmd=").append(p0_Cmd).append("&");
		stringBuffer.append("p1_MerId=").append(p1_MerId).append("&");
		stringBuffer.append("p2_Order=").append(p2_Order).append("&");
		stringBuffer.append("p3_Amt=").append(p3_Amt).append("&");
		stringBuffer.append("p4_Cur=").append(p4_Cur).append("&");
		stringBuffer.append("p5_Pid=").append(p5_Pid).append("&");
		stringBuffer.append("p6_Pcat=").append(p6_Pcat).append("&");
		stringBuffer.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		stringBuffer.append("p8_Url=").append(p8_Url).append("&");
		stringBuffer.append("p9_SAF=").append(p9_SAF).append("&");
		stringBuffer.append("pa_MP=").append(pa_MP).append("&");
		stringBuffer.append("pd_FrpId=").append(pd_FrpId).append("&");
		stringBuffer.append("pr_NeedResponse=").append(pr_NeedResponse)
				.append("&");
		stringBuffer.append("hmac=").append(hmac);

		// 重定向到易宝
		ServletActionContext.getResponse()
				.sendRedirect(stringBuffer.toString());
		return NONE;
	}

	/**
	 * 支付成功后
	 * 
	 * @return
	 */
	public String callBack() {
		// 修改订单状态为已经付款
		Order currOrder = orderService.findByOid(Integer.parseInt(r6_Order));
		currOrder.setState(2);
		orderService.update(currOrder);
		// 在页面上显示付款成功后的信息
		this.addActionMessage("订单付款成功！订单编号：" + r6_Order + " 付款金额：" + r3_Amt);
		return "msg";
	}

	/**
	 * 确认收货，修改订单状态
	 * 
	 * @return
	 */
	public String updateState() {
		// 根据订单ID查询订单
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setState(4);
		orderService.update(currOrder);
		return "updateStateSuccess";
	}

}
