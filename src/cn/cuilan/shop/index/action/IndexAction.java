package cn.cuilan.shop.index.action;

import java.util.List;

import cn.cuilan.shop.category.entity.Category;
import cn.cuilan.shop.category.service.CategoryService;
import cn.cuilan.shop.product.entity.Product;
import cn.cuilan.shop.product.service.ProductService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 访问首页的Action
 * 
 * @author 翠兰123
 * @date 2015年3月20日
 */
public class IndexAction extends ActionSupport {

	/** 序列化版本ID */
	private static final long serialVersionUID = 2999055005035729731L;

	/** 注入CategoryService */
	private CategoryService categoryService;

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/** 注入ProductService */
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	/**
	 * 1.执行访问首页的方法<br>
	 * 2.查询一级分类
	 * 
	 * @return
	 */
	@Override
	public String execute() {

		// **************************************************
		// 2015/8/25 测试OneAPM功能，临时添加，让线程等待10秒，来抓取数据
		// **************************************************
		// try {
		// Thread.sleep(10000L);
		// } catch (InterruptedException e) {
		// throw new RuntimeException("线程等待10秒异常");
		// }
		// **************************************************

		// 查询所有一级分类
		List<Category> cList = categoryService.findAll();
		// 将一级分类存入到Session范围内
		ActionContext.getContext().getSession().put("cList", cList);
		// 查询热门商品
		List<Product> hList = productService.findHot();
		// 保存到值栈
		ActionContext.getContext().getValueStack().set("hList", hList);
		// 查询最新商品
		List<Product> nList = productService.finNew();
		// 保存到值栈中
		ActionContext.getContext().getValueStack().set("nList", nList);
		// 跳转首页
		return "index";
	}

}
