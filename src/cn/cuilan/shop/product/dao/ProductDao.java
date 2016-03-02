package cn.cuilan.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.cuilan.shop.product.entity.Product;
import cn.cuilan.shop.utils.PageHibernateCallback;

/**
 * 商品持久层
 * 
 * @author 翠兰123
 * @date 2015年3月24日
 */
public class ProductDao extends HibernateDaoSupport {

	/**
	 * 首页热门商品的查询
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Product> findHot() {
		// 使用离线条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		// 查询热门商品条件是isHot = 1
		criteria.add(Restrictions.eq("isHot", 1));
		// 倒序排序输出
		criteria.addOrder(Order.desc("pdate"));
		// 执行查询
		List<Product> list = this.getHibernateTemplate().findByCriteria(
				criteria, 0, 10);
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	/**
	 * 首页上最新商品的查询
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Product> finNew() {
		// 使用离线条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		// 按日期倒序
		criteria.addOrder(Order.desc("pdate"));
		// 执行查询
		List<Product> list = this.getHibernateTemplate().findByCriteria(
				criteria, 0, 10);
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	/**
	 * 持久层根据商品的ID完成查询单个商品对象
	 * 
	 * @param pid
	 * @return
	 */
	public Product infByPid(Integer pid) {
		return this.getHibernateTemplate().get(Product.class, pid);
	}

	/**
	 * 根据一级分类的ID查询商品的个数
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int findCountCid(Integer cid) {
		String hql = "select count(*) from Product P where P.categorySecond.category.cid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, cid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		} else {
			return 0;
		}
	}

	/**
	 * 根据一级分类的ID查询商品的集合
	 * 
	 * @param cid
	 * @param begin
	 * @param limit
	 * @return
	 */
	public List<Product> findByPageCid(Integer cid, int begin, int limit) {
		// SQL:select p.* from category c, categorySecond cs, product p where
		// c.cid = cs.csid and cs.csid = p.csid and c.cid = 1;
		// HQL:select p from Category c, CategorySecond cs, Product p where
		// c.cid = cs.category.cid and cs.csid = p.categorySecond.csid and c.cid
		// = ?
		// join自动用外键查询
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		List<Product> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Product>(hql, new Object[] { cid },
						begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	/**
	 * 根据二级分类的ID查询商品的个数
	 * 
	 * @param csid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int findCountCsid(Integer csid) {
		String hql = "select count(*) from Product P where P.categorySecond.csid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, csid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		} else {
			return 0;
		}
	}

	/**
	 * 根据一级分类的ID查询商品的集合
	 * 
	 * @param csid
	 * @param begin
	 * @param limit
	 * @return
	 */
	public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
		List<Product> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Product>(hql, new Object[] { csid },
						begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	/**
	 * 持久层查询商品的个数
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int findCount() {
		String hql = "select count(*) from Product";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		} else {
			return 0;
		}
	}

	/**
	 * 持久层带分页的查询所有商品
	 * 
	 * @param begin
	 * @param limit
	 * @return
	 */
	public List<Product> findByPage(int begin, int limit) {
		String hql = "from Product order by pdate desc";
		List<Product> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Product>(hql, null, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 持久层保存商品
	 * 
	 * @param product
	 */
	public void save(Product product) {
		this.getHibernateTemplate().save(product);
	}

	/**
	 * 持久层删除商品
	 * 
	 * @param product
	 */
	public void delete(Product product) {
		this.getHibernateTemplate().delete(product);
	}

	/**
	 * 持久层修改商品
	 * 
	 * @param product
	 */
	public void update(Product product) {
		this.getHibernateTemplate().update(product);
	}
}
