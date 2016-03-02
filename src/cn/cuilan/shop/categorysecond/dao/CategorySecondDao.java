package cn.cuilan.shop.categorysecond.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.cuilan.shop.categorysecond.entity.CategorySecond;
import cn.cuilan.shop.utils.PageHibernateCallback;

/**
 * 二级分类持久层
 * 
 * @author 翠兰123
 * @date 2015年3月26日
 */
public class CategorySecondDao extends HibernateDaoSupport {

	/**
	 * 持久层统计二级分类的方法
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int findCount() {
		String hql = "select count(*) from CategorySecond";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * 持久层分页查询二级分类的方法
	 * 
	 * @param begin
	 * @param limit
	 * @return
	 */
	public List<CategorySecond> findByPage(int begin, int limit) {
		String hql = "from CategorySecond order by csid desc";
		List<CategorySecond> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<CategorySecond>(hql, null, begin,
						limit));
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	/**
	 * 持久层保存二级分类的方法
	 * 
	 * @param categorySecond
	 */
	public void save(CategorySecond categorySecond) {
		this.getHibernateTemplate().save(categorySecond);
	}

	/**
	 * 持久层根据二级分类的ID查询二级分类
	 * 
	 * @param csid
	 * @return
	 */
	public CategorySecond findByCsid(Integer csid) {
		return this.getHibernateTemplate().get(CategorySecond.class, csid);
	}

	/**
	 * 持久层删除二级分类的方法
	 * 
	 * @param categorySecond
	 */
	public void delete(CategorySecond categorySecond) {
		this.getHibernateTemplate().delete(categorySecond);
	}

	/**
	 * 持久层修改二级分类的方法
	 * 
	 * @param categorySecond
	 */
	public void update(CategorySecond categorySecond) {
		this.getHibernateTemplate().update(categorySecond);
	}

	/**
	 * 持久层查询所有二级分类的方法
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CategorySecond> findAll() {
		String hql = "from CategorySecond";
		List<CategorySecond> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
}
