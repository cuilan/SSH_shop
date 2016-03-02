package cn.cuilan.shop.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.cuilan.shop.category.dao.CategoryDao;
import cn.cuilan.shop.category.entity.Category;

/**
 * 一级分类的业务层
 * 
 * @author 翠兰123
 * @date 2015年3月24日
 */
@Transactional
public class CategoryService {

	/** 注入CategoryDao */
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	/**
	 * 业务层查询所有一级分类
	 * 
	 * @return
	 */
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	/**
	 * 业务层保存一级分类的方法
	 * 
	 * @param category
	 */
	public void save(Category category) {
		categoryDao.save(category);
	}

	/**
	 * 业务层根据一级分类的ID查询
	 * 
	 * @param cid
	 * @return
	 */
	public Category findByCid(Integer cid) {
		return categoryDao.findByCid(cid);
	}

	/**
	 * 业务层删除一级分类的方法
	 * 
	 * @param category
	 */
	public void delete(Category category) {
		categoryDao.delete(category);
	}

	/**
	 * 业务层修改一级分类的方法
	 * @param category
	 */
	public void update(Category category) {
		categoryDao.update(category);
	}

}
