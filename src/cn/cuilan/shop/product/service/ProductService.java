package cn.cuilan.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.cuilan.shop.product.dao.ProductDao;
import cn.cuilan.shop.product.entity.Product;
import cn.cuilan.shop.utils.PageBean;

/**
 * 商品的业务层
 * 
 * @author 翠兰123
 * @date 2015年3月24日
 */
@Transactional
public class ProductService {

	/** 注入ProductDao */
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	/**
	 * 首页热门商品的查询
	 * 
	 * @return
	 */
	public List<Product> findHot() {
		return productDao.findHot();
	}

	/**
	 * 首页上最新商品的查询
	 * 
	 * @return
	 */
	public List<Product> finNew() {
		return productDao.finNew();
	}

	/**
	 * 根据商品的ID来完成查询
	 * 
	 * @param pid
	 * @return
	 */
	public Product findByPid(Integer pid) {
		return productDao.infByPid(pid);
	}

	/**
	 * 根据一级分类的Cid带有分页的查询商品
	 * 
	 * @param cid
	 * @return
	 */
	public PageBean<Product> findByPageCid(Integer cid, Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		// 设置当前的页数
		pageBean.setPage(page);
		// 设置每页显示的记录数
		int limit = 12;
		pageBean.setLimit(limit);
		// 设置总的记录数
		int totalCount = 0;
		totalCount = productDao.findCountCid(cid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage = 0;
		// 向上取整
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合
		// 从哪开始
		int begin = (page - 1) * limit;
		List<Product> list = productDao.findByPageCid(cid, begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * 根据二级分类的ID查询商品
	 * 
	 * @param csid
	 * @param page
	 * @return
	 */
	public PageBean<Product> findByPageCsid(Integer csid, Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		// 设置当前的页数
		pageBean.setPage(page);
		// 设置每页显示的记录数
		int limit = 12;
		pageBean.setLimit(limit);
		// 设置总的记录数
		int totalCount = 0;
		totalCount = productDao.findCountCsid(csid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage = 0;
		// 向上取整
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合
		// 从哪开始
		int begin = (page - 1) * limit;
		List<Product> list = productDao.findByPageCsid(csid, begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * 业务层带分页的查询所有商品
	 * 
	 * @param page
	 * @return
	 */
	public PageBean<Product> findByPage(Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		// 设置当前页数
		pageBean.setPage(page);
		// 设置每页显示的记录数
		int limit = 10;
		pageBean.setLimit(limit);
		// 设置总的记录数
		int totalCount = productDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 设置总的页数
		int totalPage = 0;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置开始页面
		int begin = (page - 1) * limit;
		// 设置显示到页面的集合
		List<Product> list = productDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * 业务层保存商品的方法
	 * 
	 * @param product
	 */
	public void save(Product product) {
		productDao.save(product);
	}

	/**
	 * 业务层删除商品的方法
	 * 
	 * @param product
	 */
	public void delete(Product product) {
		productDao.delete(product);
	}

	/**
	 * 业务层修改商品的方法
	 * 
	 * @param product
	 */
	public void update(Product product) {
		productDao.update(product);
	}
}
