package cn.cuilan.shop.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.cuilan.shop.order.dao.OrderDao;
import cn.cuilan.shop.order.entity.Order;
import cn.cuilan.shop.order.entity.OrderItem;
import cn.cuilan.shop.utils.PageBean;

/**
 * 订单模块业务层
 * 
 * @author 翠兰123
 * @date 2015年3月25日
 */
@Transactional
public class OrderService {

	/** 注入OrderDao */
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	/**
	 * 保存订单的业务层代码
	 * 
	 * @param order
	 */
	public void save(Order order) {
		orderDao.save(order);
	}

	/**
	 * 我的订单业务层代码
	 * 
	 * @param uid
	 * @param page
	 * @return
	 */
	public PageBean<Order> findByPageUid(Integer uid, Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		// 设置当前页数
		pageBean.setPage(page);
		// 设置每页显示的记录数
		Integer limit = 5;
		pageBean.setLimit(limit);
		// 设置总的记录数
		Integer totalCount = null;
		totalCount = orderDao.findByCountUid(uid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		Integer totalPage = null;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置每页显示的数据集合
		Integer begin = (page - 1) * limit;
		List<Order> list = orderDao.findByPageUid(uid, begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * 业务层根据订单ID查询订单
	 * 
	 * @param oid
	 * @return
	 */
	public Order findByOid(Integer oid) {
		return orderDao.findByOid(oid);
	}

	/**
	 * 业务层修改订单信息
	 * 
	 * @param currOrder
	 */
	public void update(Order currOrder) {
		orderDao.update(currOrder);
	}

	/**
	 * 业务层带分页查询订单的方法
	 * 
	 * @param page
	 * @return
	 */
	public PageBean<Order> findByPageUid(Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		// 设置当前页数
		pageBean.setPage(page);
		// 设置每页显示的记录数
		Integer limit = 10;
		pageBean.setLimit(limit);
		// 设置总的记录数
		Integer totalCount = 0;
		totalCount = orderDao.findByCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		Integer totalPage = null;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置每页显示的数据集合
		Integer begin = (page - 1) * limit;
		List<Order> list = orderDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * 业务层根据订单ID查询订单项的方法
	 * 
	 * @param oid
	 * @return
	 */
	public List<OrderItem> findOrderItem(Integer oid) {
		return orderDao.findOrderItem(oid);
	}

}
