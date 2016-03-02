package cn.cuilan.shop.category.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import cn.cuilan.shop.categorysecond.entity.CategorySecond;

/**
 * 一级分类的实体对象
 * 
 * @author 翠兰123
 * @date 2015年3月24日
 * @SQL CREATE TABLE 'category' ( 'cid' int(11) NOT NULL AUTO_INCREMENT, 'cname'
 *      varchar(255) DEFAULT NULL, PRIMARY KEY ('cid') ) ENGINE=InnoDB
 *      AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
 */
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer cid;

	private String cname;

	/** 一级分类中存放二级分类的集合 */
	private Set<CategorySecond> categorySeconds = new HashSet<CategorySecond>();

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Set<CategorySecond> getCategorySeconds() {
		return categorySeconds;
	}

	public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
		this.categorySeconds = categorySeconds;
	}

}
