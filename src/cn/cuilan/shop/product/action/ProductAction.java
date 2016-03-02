package cn.cuilan.shop.product.action;

import cn.cuilan.shop.product.entity.Product;
import cn.cuilan.shop.product.service.ProductService;
import cn.cuilan.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 商品对应的Action
 * 
 * @author 翠兰123
 * @date 2015年3月24日
 */
public class ProductAction extends ActionSupport implements
		ModelDriven<Product> {

	private static final long serialVersionUID = 1L;

	/** 用于接受数据的模型驱动 */
	private Product product = new Product();

	@Override
	public Product getModel() {
		return product;
	}

	/** 接受一级分类的Cid */
	private Integer cid;

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	/** 接受二级分类的Csid */
	private Integer csid;

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	/**
	 * 为了在页面中查询，需要提供getter
	 * 
	 * @return
	 */
	public Integer getCsid() {
		return csid;
	}

	/**
	 * 为了在productList.jsp页面中能获取到Cid，需要提供一个getter
	 * 
	 * @return
	 */
	public Integer getCid() {
		return cid;
	}

	/** 接受当前的页数 */
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}

	/** 注入商品的Service */
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	/**
	 * 根据商品的ID进行查询商品的方法
	 * 
	 * @return
	 */
	public String findByPid() {
		// 调用Service的方法完成查询
		product = productService.findByPid(product.getPid());
		return "findByPid";
	}

	/**
	 * 根据分类的ID查询商品，直接从Session中获取
	 * 
	 * @return
	 */
	public String findByCid() {
		// 根据一级分类查询商品（带分压）
		PageBean<Product> pageBean = productService.findByPageCid(cid, page);
		// 将pageBean存入到值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}

	/**
	 * 根据二级分类的ID查询商品
	 * 
	 * @return
	 */
	public String findByCsid() {
		PageBean<Product> pageBean = productService.findByPageCsid(csid, page);
		// 将pageBean存入到值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}
}
