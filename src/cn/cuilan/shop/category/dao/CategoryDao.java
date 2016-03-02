package cn.cuilan.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.cuilan.shop.category.entity.Category;

/**
 * 一级分类的持久层
 * 
 * @author 翠兰123
 * @date 2015年3月24日
 */
public class CategoryDao extends HibernateDaoSupport {

	/**
	 * 持久层查询所有一级分类的方法
	 * 
	 * @return Category对象的list集合
	 */
	@SuppressWarnings("unchecked")
	public List<Category> findAll() {
		String hql = "from Category";
		List<Category> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	/**
	 * 持久层保存一级分类的方法
	 */
	public void save(Category category) {
		this.getHibernateTemplate().save(category);
	}

	/**
	 * 持久层根据一级分类的ID查询
	 * 
	 * @param cid
	 * @return
	 */
	public Category findByCid(Integer cid) {
		return this.getHibernateTemplate().get(Category.class, cid);
	}

	/**
	 * 持久层删除一级分类
	 * 
	 * @param category
	 */
	public void delete(Category category) {
		this.getHibernateTemplate().delete(category);
	}

	/**
	 * 持久层编辑一级分类
	 * 
	 * @param category
	 */
	public void update(Category category) {
		this.getHibernateTemplate().update(category);
	}

}
