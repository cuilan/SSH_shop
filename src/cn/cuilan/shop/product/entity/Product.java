package cn.cuilan.shop.product.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import cn.cuilan.shop.categorysecond.entity.CategorySecond;

/**
 * 商品实体对象
 * 
 * @author 翠兰123
 * @date 2015年3月24日
 * @SQL CREATE TABLE 'product' ( 'pid' int(11) NOT NULL AUTO_INCREMENT, 'pname'
 *      varchar(255) DEFAULT NULL, 'market_price' double DEFAULT NULL,
 *      'shopPrice' double DEFAULT NULL, 'image' varchar(255) DEFAULT NULL,
 *      'pdesc' varchar(255) DEFAULT NULL, 'isHot' int(11) DEFAULT NULL, 'pdate'
 *      datetime DEFAULT NULL, 'csid' int(11) DEFAULT NULL, PRIMARY KEY ('pid'),
 *      KEY 'FKED8DCCEFB9B74E02' ('csid'), CONSTRAINT 'FKED8DCCEFB9B74E02'
 *      FOREIGN KEY ('csid') REFERENCES 'categorysecond' ('csid') )
 *      ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;
 */
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer pid;

	private String pname;

	private Double marketPrice;

	private Double shopPrice;

	private String image;

	private String pdesc;

	private Integer isHot;

	private Date pdate;

	/** 二级分类的外键：使用二级分类的对象 */
	private CategorySecond categorySecond;

	/** 配置商品的集合 */
	private Set<Product> products = new HashSet<Product>();

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public Double getShopPrice() {
		return shopPrice;
	}

	public void setShopPrice(Double shopPrice) {
		this.shopPrice = shopPrice;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPdesc() {
		return pdesc;
	}

	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}

	public Integer getIsHot() {
		return isHot;
	}

	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}

	public Date getPdate() {
		return pdate;
	}

	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}

	public CategorySecond getCategorySecond() {
		return categorySecond;
	}

	public void setCategorySecond(CategorySecond categorySecond) {
		this.categorySecond = categorySecond;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
