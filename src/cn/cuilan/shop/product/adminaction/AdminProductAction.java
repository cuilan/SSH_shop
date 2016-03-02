package cn.cuilan.shop.product.adminaction;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import cn.cuilan.shop.categorysecond.entity.CategorySecond;
import cn.cuilan.shop.categorysecond.service.CategorySecondService;
import cn.cuilan.shop.product.entity.Product;
import cn.cuilan.shop.product.service.ProductService;
import cn.cuilan.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台商品管理的Action
 * 
 * @author 翠兰123
 * @date 2015年3月27日
 */
/**
 * @author 翠兰123
 * @date 2015年3月27日
 * @描述：
 */
public class AdminProductAction extends ActionSupport implements
		ModelDriven<Product> {

	private static final long serialVersionUID = 1L;

	/** 模型驱动需要使用的product对象 */
	private Product product = new Product();

	@Override
	public Product getModel() {
		return product;
	}

	/** 注入商品的Service */
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	/** 接受page */
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}

	/** 注入二级分类的Service */
	private CategorySecondService categorySecondService;

	public void setCategorySecondService(
			CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	/** Struts2文件上传的文件 */
	private File upload;

	/** 接受文件上传的文件名 */
	private String uploadFileName;

	/** 接受文件上传的MIME的类型 */
	// private String uploadContextType;

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	/**
	 * 带分页的查询所有商品
	 * 
	 * @return
	 */
	public String findAll() {
		// 调用Service完成查询
		PageBean<Product> pageBean = productService.findByPage(page);
		// 将数据传入页面
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		// 页面跳转
		return "findAll";
	}

	/**
	 * 跳转到添加商品页面
	 * 
	 * @return
	 */
	public String addPage() {
		// 查询所有的二级分类的集合
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		// 页面跳转
		return "addPageSuccess";
	}

	/**
	 * 保存商品的方法
	 * 
	 * @return
	 * @throws IOException
	 */
	public String save() throws IOException {
		// 调用Service完成保存商品操作
		product.setPdate(new Date());
		if (upload != null) {
			// 获得文件上传的磁盘绝对路径
			String realPath = ServletActionContext.getServletContext()
					.getRealPath("/products");
			// 创建一个文件
			File diskFile = new File(realPath + "/" + uploadFileName);
			// 文件上传
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/" + uploadFileName);
		}
		productService.save(product);
		return "saveSuccess";
	}

	/**
	 * 商品的删除方法
	 * 
	 * @return
	 */
	public String delete() {
		// 先查询
		product = productService.findByPid(product.getPid());
		// 再删除
		if (product != null) {
			// 删除商品图片
			String path = product.getImage();
			if (path != null) {
				String realPath = ServletActionContext.getServletContext()
						.getRealPath("/" + path);
				File file = new File(realPath);
				file.delete();
			}
			productService.delete(product);
		}
		return "deleteSuccess";
	}

	/**
	 * 编辑商品信息
	 * 
	 * @return
	 */
	public String edit() {
		// 根据商品的ID查询商品
		product = productService.findByPid(product.getPid());
		// 查询所有二级分类的集合
		List<CategorySecond> csList = categorySecondService.findAll();
		// 将数据保存到页面
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "editSuccess";
	}

	/**
	 * 修改商品的方法
	 * 
	 * @return
	 * @throws IOException
	 */
	public String update() throws IOException {
		// 修改商品数据
		product.setPdate(new Date());
		// 文件修改
		if (upload != null) {
			// 获得数据库中图片的路径
			String path = product.getImage();
			File file = new File(ServletActionContext.getServletContext()
					.getRealPath("/" + path));
			// 将原来上传的图片删除
			file.delete();
			// 获得页面上传的图片的绝对路径
			String realPath = ServletActionContext.getServletContext()
					.getRealPath("/products");
			File diskFile = new File(realPath + "/" + uploadFileName);
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/" + uploadFileName);
		}
		productService.update(product);
		return "updateSuccess";
	}

}
