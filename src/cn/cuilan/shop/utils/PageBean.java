package cn.cuilan.shop.utils;

import java.util.List;

/**
 * 分页类的对象
 * 
 * @author 翠兰123
 * @date 2015年3月24日
 */
public class PageBean<T> {

	/** 当前页数 */
	private Integer page;

	/** 总的记录数 */
	private Integer totalCount;

	/** 总页数 */
	private Integer totalPage;

	/** 每页显示的记录数 */
	private Integer limit;

	/** 每页显示的数据集合 */
	private List<T> list;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}
