package cn.cuilan.shop.cart.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物项对象
 * 
 * @author 翠兰123
 * @date 2015年3月24日
 */
public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 购物项集合，key为商品ID，value为购物项 */
	private Map<Integer, CartItem> map = new LinkedHashMap<Integer, CartItem>();

	/** 购物总计 */
	private double total;

	/**
	 * 添加到购物车
	 * 
	 * @param cartItem
	 */
	public void addCart(CartItem cartItem) {
		// 获取商品ID
		Integer pid = cartItem.getProduct().getPid();
		// 判断购物车中是否已经存在该购物项
		if (map.containsKey(pid)) {
			// 如果存在，数量增加，总计 = 总计 + 购物小计
			CartItem cartItem2 = map.get(pid);
			cartItem2.setCount(cartItem.getCount() + cartItem2.getCount());
		} else {
			// 如果不存在，添加到map中，总计 = 总计 + 购物小计
			map.put(pid, cartItem);
		}
		// 设置总计的值
		total += cartItem.getSubtotal();
	}

	/**
	 * 通过商品ID移除购物项
	 * 
	 * @param pid
	 */
	public void removeCart(Integer pid) {
		// 将购物项从购物车中移除
		CartItem cartItem = map.remove(pid);
		// 总计 = 总计 - 移除的购物项
		total -= cartItem.getSubtotal();
	}

	/**
	 * 清空购物车
	 */
	public void clearCart() {
		// 将所有的购物项清空
		map.clear();
		// 将总计设置为0
		total = 0;
	}

	/**
	 * 调用Map的values()方法，将Map转换成单列集合
	 * 
	 * @return 返回一个Collection类型的单列集合
	 */
	public Collection<CartItem> getCartItems() {
		return map.values();
	}

	/**
	 * 提供getter供页面设置值
	 * 
	 * @return
	 */
	public double getTotal() {
		return total;
	}

}