package cn.cuilan.shop.categorysecond.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import cn.cuilan.shop.category.entity.Category;
import cn.cuilan.shop.product.entity.Product;

/**
 * 二级分类的实体
 * 
 * @author 翠兰123
 * @date 2015年3月24日
 * @SQL CREATE TABLE 'categorysecond' ( 'csid' int(11) NOT NULL AUTO_INCREMENT,
 *      'csname' varchar(255) DEFAULT NULL, 'cid' int(11) DEFAULT NULL, PRIMARY
 *      KEY ('csid'), KEY 'FK936FCAF21DB1FD15' ('cid'), CONSTRAINT
 *      'FK936FCAF21DB1FD15' FOREIGN KEY ('cid') REFERENCES 'category' ('cid') )
 *      ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
 */
public class CategorySecond implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer csid;

	private String csname;

	/** 所属一级分类的，存入的是一级分类的对象 */
	private Category category;

	/** 配置商品集合 */
	private Set<Product> products = new HashSet<Product>();

	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	public String getCsname() {
		return csname;
	}

	public void setCsname(String csname) {
		this.csname = csname;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
