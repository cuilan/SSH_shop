package cn.cuilan.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import cn.cuilan.shop.cart.entity.Cart;
import cn.cuilan.shop.cart.entity.CartItem;
import cn.cuilan.shop.product.entity.Product;
import cn.cuilan.shop.product.service.ProductService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 购物车的Action
 * 
 * @author 翠兰123
 * @date 2015年3月25日
 */
public class CartAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	/** 接受pid */
	private Integer pid;

	/** 接受数量count */
	private Integer count;

	/** 注入ProductService */
	private ProductService productService;

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	/**
	 * 将购物项添加到购物车
	 * 
	 * @return
	 */
	public String addCart() {
		// 封装CartItem对象
		CartItem cartItem = new CartItem();
		// 设置数量
		cartItem.setCount(count);
		// 设置商品，根据商品的pid完成查询
		Product product = productService.findByPid(pid);
		cartItem.setProduct(product);
		// 购物车存在于Session中
		Cart cart = getCart();
		cart.addCart(cartItem);
		return "addCart";
	}

	/**
	 * 清空购物车的执行方法
	 * 
	 * @return
	 */
	public String clearCart() {
		// 获取购物车对象
		Cart cart = getCart();
		// 调用购物车的清空方法
		cart.clearCart();
		return "clearCart";
	}

	/**
	 * 从购物车中移除购物项的方法
	 * 
	 * @return
	 */
	public String removeCart() {
		// 获得购物车对象
		Cart cart = getCart();
		// 调用购物车中移除的方法
		cart.removeCart(pid);
		// 返回页面
		return "removeCart";
	}

	/**
	 * menu中我的购物车方法
	 * 
	 * @return
	 */
	public String myCart() {
		return "myCart";
	}

	/**
	 * 从Session中获得购物车的方法
	 * 
	 * @return
	 */
	private Cart getCart() {
		// 从Session中获得购物车对象
		Cart cart = (Cart) ServletActionContext.getRequest().getSession()
				.getAttribute("cart");
		// 判断session中是否存在
		if (cart == null) {
			cart = new Cart();
			ServletActionContext.getRequest().getSession()
					.setAttribute("cart", cart);
		}
		return cart;
	}

}
