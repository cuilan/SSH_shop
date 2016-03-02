package cn.cuilan.shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.cuilan.shop.order.entity.Order;
import cn.cuilan.shop.order.entity.OrderItem;
import cn.cuilan.shop.utils.PageHibernateCallback;

/**
 * 订单模块持久层
 * 
 * @author 翠兰123
 * @date 2015年3月25日
 */
public class OrderDao extends HibernateDaoSupport {

	/**
	 * 持久层保存订单的方法
	 * 
	 * @param order
	 */
	public void save(Order order) {
		this.getHibernateTemplate().save(order);
	}

	/**
	 * 持久层我的订单的个数统计
	 * 
	 * @param uid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Integer findByCountUid(Integer uid) {
		String hql = "select count(*) from Order o where o.user.uid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, uid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return null;
	}

	/**
	 * 持久层我的订单的查询
	 * 
	 * @param uid
	 * @param begin
	 * @param limit
	 * @return
	 */
	public List<Order> findByPageUid(Integer uid, Integer begin, Integer limit) {
		// 查询后排序显示
		String hql = "from Order o where o.user.uid = ? order by ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Order>(hql, new Object[] { uid },
						begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 持久层根据订单ID查询订单
	 * 
	 * @param oid
	 * @return
	 */
	public Order findByOid(Integer oid) {
		return this.getHibernateTemplate().get(Order.class, oid);
	}

	/**
	 * 持久层修改订单
	 * 
	 * @param currOrder
	 */
	public void update(Order currOrder) {
		this.getHibernateTemplate().update(currOrder);
	}

	/**
	 * 持久层统计订单个数
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Integer findByCount() {
		String hql = "select count(*) from Order";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		} else {
			return 0;
		}
	}

	/**
	 * 持久层带分页的查询
	 * 
	 * @param begin
	 * @param limit
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Order> findByPage(Integer begin, Integer limit) {
		String hql = "from Order order by ordertime desc";
		List<Order> list = this.getHibernateTemplate().executeFind(
				new PageHibernateCallback<Order>(hql, null, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	/**
	 * 持久层根据订单ID查询订单项
	 * 
	 * @param oid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OrderItem> findOrderItem(Integer oid) {
		String hql = "from OrderItem oi where oi.order.oid = ?";
		List<OrderItem> list = this.getHibernateTemplate().find(hql, oid);
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

}
