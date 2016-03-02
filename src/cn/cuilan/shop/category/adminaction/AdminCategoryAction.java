package cn.cuilan.shop.category.adminaction;

import java.util.List;

import cn.cuilan.shop.category.entity.Category;
import cn.cuilan.shop.category.service.CategoryService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台一级分类管理的Action
 * 
 * @author 翠兰123
 * @date 2015年3月26日
 */
public class AdminCategoryAction extends ActionSupport implements
		ModelDriven<Category> {

	private static final long serialVersionUID = 1L;

	/** 模型驱动需要使用的一级分类对象 */
	private Category category = new Category();

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public Category getModel() {
		return category;
	}

	/** 注入一级分类的Service */
	private CategoryService categoryService;

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/**
	 * 后台执行查询所有一级分类的方法
	 * 
	 * @return
	 */
	public String findAll() {
		// 查询所有一级分类
		List<Category> cList = categoryService.findAll();
		// 将数据集合显示到页面上
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAll";
	}

	/**
	 * 后台保存一级分类的方法
	 * 
	 * @return
	 */
	public String save() {
		// 调用Service保存
		categoryService.save(category);
		// 保存之后页面跳转
		return "saveSuccess";
	}

	/**
	 * 后台删除一级分类的方法
	 * 
	 * @return
	 */
	public String delete() {
		// 先进行查询，再删除
		category = categoryService.findByCid(category.getCid());
		// 判断category是否为空
		if (category != null) {
			categoryService.delete(category);
		}
		// 页面跳转
		return "deleteSuccess";
	}

	/**
	 * 后台编辑一级分类跳转的方法
	 * 
	 * @return
	 */
	public String edit() {
		// 根据一级分类查询
		categoryService.findByCid(category.getCid());
		// 页面跳转
		return "editSuccess";
	}

	/**
	 * 后台修改一级分类的方法
	 * 
	 * @return
	 */
	public String update() {
		categoryService.update(category);
		return "updateSuccess";
	}

}
