package com.ruanjf.springMVC.dao.support;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
/**
 * 分页对象.包含当前页数据及分页信息如总记录数.
 *
 * @author Scott.wanglei
 * @since  2008-6-29
 */
public class Page<T> implements Serializable {

	private static final long serialVersionUID = -5624189033006412710L;

	private static long DEFAULT_PAGE_SIZE = 20;
	private long pageSize = DEFAULT_PAGE_SIZE; // 每页的记录数
	private long start; // 当前页第一条数据在List中的位置,从0开始
	private List<T> data = Collections.emptyList(); // 当前页中存放的记录
	private long totalCount = 0; // 总记录数
	private long pageCount = 0; // 总页数
	
	/**
	 * 构造方法，只构造空页.
	 */
	public Page() {
		this(0l, 0l, DEFAULT_PAGE_SIZE, Collections.<T>emptyList());
	}
	
	/**
	 * 构造方法，只构造空页.
	 * @param pageNo 当前页
	 * @param pageSize 页面容量
	 */
	public Page(long pageNo, long pageSize){
		if(pageSize<=0)
			pageSize=DEFAULT_PAGE_SIZE;
		this.pageSize = pageSize;
		this.start = getStartOfPage(pageNo, pageSize);
	}
	
	/**
	 * 默认构造方法.
	 *
	 * @param start	 本页数据在数据库中的起始位置
	 * @param totalSize 数据库中总记录条数
	 * @param pageSize  本页容量
	 * @param data	  本页包含的数据
	 */
	public Page(long start, long totalSize, long pageSize, List<T> data) {
		if(pageSize<=0)
			pageSize=DEFAULT_PAGE_SIZE;
		this.pageSize = pageSize;
		this.start = start;
		this.totalCount = totalSize;
		this.data = data;
		this.pageCount = this.getPageCount();
	}

	/**
	 * 取总记录数.
	 */
	public long getTotalCount() {
		return this.totalCount;
	}

	/**
	 * 取总页数.
	 */
	public long getPageCount() {
		if (totalCount % pageSize == 0)
			return totalCount / pageSize;
		else
			return totalCount / pageSize + 1;
	}

	/**
	 * 取每页数据容量.
	 */
	public Long getPageSize() {
		return pageSize;
	}

	/**
	 * 取当前页中的记录.
	 */
	public List<T> getResult() {
		return data;
	}
	
	/**
	 * 填充当前页中的记录.
	 */
	public void setResult(List<T> data) {
		if(data!=null && !data.isEmpty())
			this.data = data;
	}

	/**
	 * 取该页当前页码,页码从1开始.
	 */
	public long getCurrentPage() {
		return start / pageSize + 1;
	}

	/**
	 * 该页是否有下一页.
	 */
	public boolean hasNextPage() {
		return this.getCurrentPage() < this.getPageCount() - 1;
	}

	/**
	 * 该页是否有上一页.
	 */
	public boolean hasPreviousPage() {
		return this.getCurrentPage() > 1;
	}

	/**
	 * 获取任一页第一条数据在数据集的位置，每页条数使用默认值.
	 *
	 */
	protected static long getStartOfPage(long pageNo) {
		return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
	}

	/**
	 * 获取任一页第一条数据在数据集的位置.
	 *
	 * @param pageNo   从1开始的页号
	 * @param pageSize 每页记录条数
	 * @return 该页第一条数据
	 */
	public static long getStartOfPage(long pageNo, long pageSize) {
		if(pageNo<1)
			pageNo=1;
		if(pageSize<=0)
			pageSize=DEFAULT_PAGE_SIZE;
		return (pageNo - 1) * pageSize;
	}

	/**
	 * 获得起始条数
	 * @return
	 */
	public Long getStart() {
		return start;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
		
		pageCount = totalCount/pageSize;
		if(totalCount%pageSize>0)
			pageCount++;
		
	}

}