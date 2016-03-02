package cn.cuilan.shop.cart.entity;

import java.io.Serializable;

import cn.cuilan.shop.product.entity.Product;

/**
 * 购物车对象
 * 
 * @author 翠兰123
 * @date 2015年3月24日
 */
public class CartItem implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 购物项中的商品信息 */
	private Product product;

	/** 购物车中购物项总计 */
	private Integer count;

	/** 购物车小计 */
	@SuppressWarnings("unused")
	private double subtotal;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * 购物项小计直接返回数值
	 * 
	 * @return
	 */
	public double getSubtotal() {
		// 数量 * 价格
		return count * product.getShopPrice();
	}

	/**
	 * 购物项小计不需要setter方法，直接从页面中计算得出
	 * 
	 * @param subtotal
	 */
	/*
	 * public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }
	 */

}
