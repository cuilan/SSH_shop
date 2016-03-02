package cn.cuilan.shop.categorysecond.adminaction;

import java.util.List;

import cn.cuilan.shop.category.entity.Category;
import cn.cuilan.shop.category.service.CategoryService;
import cn.cuilan.shop.categorysecond.entity.CategorySecond;
import cn.cuilan.shop.categorysecond.service.CategorySecondService;
import cn.cuilan.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台二级分类管理的Action
 * 
 * @author 翠兰123
 * @date 2015年3月27日
 */
public class AdminCategorySecondAction extends ActionSupport implements
		ModelDriven<CategorySecond> {

	private static final long serialVersionUID = 1L;

	/** 创建模型驱动需要使用的对象 */
	private CategorySecond categorySecond = new CategorySecond();

	@Override
	public CategorySecond getModel() {
		return categorySecond;
	}

	/** 注入CategorySecondService */
	private CategorySecondService categorySecondService;

	public void setCategorySecondService(
			CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	/** 接受分页page */
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}

	/** 注入CategoryService */
	private CategoryService categoryService;

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/**
	 * 查询二级分类的方法
	 * 
	 * @return
	 */
	public String findAll() {
		PageBean<CategorySecond> pageBean = categorySecondService
				.findByPage(page);
		// 将PageBean数据保存到值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	public String addPage() {
		// 查询所有的一级分类
		List<Category> cList = categoryService.findAll();
		// 把数据显示到页面上
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "addPageSuccess";
	}

	/**
	 * 保存二级分类的方法
	 * 
	 * @return
	 */
	public String save() {
		categorySecondService.save(categorySecond);
		return "saveSuccess";
	}

	/**
	 * 删除二级分类的方法
	 * 
	 * @return
	 */
	public String delete() {
		// 如果级联删除，先查询再删除
		categorySecond = categorySecondService.findByCsid(categorySecond
				.getCsid());
		if (categorySecond != null) {
			categorySecondService.delete(categorySecond);
		}
		return "deleteSuccess";
	}

	/**
	 * 编辑二级分类的方法
	 * 
	 * @return
	 */
	public String edit() {
		// 根据二级分类的ID查询二级分类的对象
		categorySecond = categorySecondService.findByCsid(categorySecond
				.getCsid());
		// 查询所有的一级分类
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "editSuccess";
	}

	/**
	 * 修改二级分类的方法
	 * 
	 * @return
	 */
	public String update() {
		categorySecondService.update(categorySecond);
		return "updateSuccess";
	}

}
