package cn.cuilan.shop.cart.service;

import cn.cuilan.shop.cart.entity.CartItem;

/**
 * 购物车业务层
 * 
 * @author 翠兰123
 * @date 2015年3月24日
 */
public interface CartService {

	/**
	 * 添加到购物车
	 * 
	 * @param cartItem
	 */
	public void addCart(CartItem cartItem);

	/**
	 * 通过商品ID移除购物项
	 * 
	 * @param pid
	 */
	public void removeCart(Integer pid);

	/**
	 * 清空购物车<br>
	 * 1.将所有的购物项清空<br>
	 * 2.将总计设置为0
	 */
	public void clearCart();

}
