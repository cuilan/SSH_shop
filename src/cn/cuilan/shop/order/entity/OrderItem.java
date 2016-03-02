package cn.cuilan.shop.order.entity;

import java.io.Serializable;

import cn.cuilan.shop.product.entity.Product;

/**
 * 订单模块的实体对象
 * 
 * @author 翠兰123
 * @date 2015年3月25日
 * @SQL CREATE TABLE 'orderitem' ( 'itemid' int(11) NOT NULL AUTO_INCREMENT,
 *      'count' int(11) DEFAULT NULL, 'subtotal' double DEFAULT NULL, 'pid'
 *      int(11) DEFAULT NULL, 'oid' int(11) DEFAULT NULL, PRIMARY KEY
 *      ('itemid'), KEY 'FKE8B2AB6166C01961' ('oid'), KEY 'FKE8B2AB6171DB7AE4'
 *      ('pid'), KEY 'FKE8B2AB6140ACF87A' ('oid'), CONSTRAINT
 *      'FKE8B2AB6140ACF87A' FOREIGN KEY ('oid') REFERENCES 'orders' ('oid'),
 *      CONSTRAINT 'FKE8B2AB6171DB7AE4' FOREIGN KEY ('pid') REFERENCES 'product'
 *      ('pid') ) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
 */
public class OrderItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer itemid;

	private Integer count;

	private Double subtotal;

	/** 商品外键:对象 */
	private Product product;

	/** 订单外键:对象 */
	private Order order;

	public Integer getItemid() {
		return itemid;
	}

	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
