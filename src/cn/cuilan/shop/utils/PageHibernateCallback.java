package cn.cuilan.shop.utils;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * 分页查询实现HibernateCallback接口
 * 
 * @author 翠兰123
 * @date 2015年3月24日
 */
public class PageHibernateCallback<T> implements HibernateCallback<List<T>> {

	/** 要执行的HQL语句 */
	private String hql;

	/** 查询条件，可变参数 */
	private Object[] params;

	/** 查询起始位置 */
	private int startIndex;

	/** 查询的个数 */
	private int pageSize;

	/**
	 * PageHibernateCallback的构造函数
	 * 
	 * @param hql
	 * @param params
	 * @param startIndex
	 * @param pageSize
	 */
	public PageHibernateCallback(String hql, Object[] params, int startIndex,
			int pageSize) {
		super();
		this.hql = hql;
		this.params = params;
		this.startIndex = startIndex;
		this.pageSize = pageSize;
	}

	/**
	 * 返回一个list集合，其中包含查询条件
	 */
	@SuppressWarnings("unchecked")
	public List<T> doInHibernate(Session session) throws HibernateException,
			SQLException {
		// 1 执行hql语句
		Query query = session.createQuery(hql);
		// 2 实际参数
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		// 3 分页
		query.setFirstResult(startIndex);
		query.setMaxResults(pageSize);

		return query.list();
	}

}
