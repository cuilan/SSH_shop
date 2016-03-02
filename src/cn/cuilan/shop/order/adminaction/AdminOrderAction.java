package cn.cuilan.shop.order.adminaction;

import java.util.List;

import cn.cuilan.shop.order.entity.Order;
import cn.cuilan.shop.order.entity.OrderItem;
import cn.cuilan.shop.order.service.OrderService;
import cn.cuilan.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台订单管理的Action
 * 
 * @author 翠兰123
 * @date 2015年3月27日
 */
public class AdminOrderAction extends ActionSupport implements
		ModelDriven<Order> {

	private static final long serialVersionUID = 1L;

	/** 模型驱动需要使用的对象 */
	private Order order = new Order();

	@Override
	public Order getModel() {
		return order;
	}

	/** 注入orderService */
	private OrderService orderService;

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	/** 接受前台的page */
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}

	/**
	 * 带分页查询的执行方法
	 * 
	 * @return
	 */
	public String findAll() {
		PageBean<Order> pageBean = orderService.findByPageUid(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}

	/**
	 * 根据订单ID查询订单项
	 * 
	 * @return
	 */
	public String findOrderItem() {
		// 根据订单ID查询订单项
		List<OrderItem> list = orderService.findOrderItem(order.getOid());
		ActionContext.getContext().getValueStack().set("list", list);
		return "findOrderItem";
	}

	/**
	 * 后台修改订单状态的方法
	 * 
	 * @return
	 */
	public String updateState() {
		// 1.根据订单ID查询订单
		Order currOrder = orderService.findByOid(order.getOid());
		// 2.修改订单状态
		currOrder.setState(3);
		orderService.update(currOrder);
		// 3.页面跳转
		return "updateStateSuccess";
	}

}
