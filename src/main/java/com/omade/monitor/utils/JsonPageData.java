package com.omade.monitor.utils;

import java.io.Serializable;
import java.util.List;

/**
 * @create bo.xu
 * @date  Apr 22, 2016
 * @version 1.0
 * @description : 分页response数据
 * **/
public class JsonPageData<T extends Serializable> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer pageNo;
	
	private Integer pageSize;
	
	//private Integer totalCount;
	
	private List<T> datas;
	
	public JsonPageData(){
		
	}
	public JsonPageData(Integer pageNo, Integer pageSize, 
			//Integer totalCount, 
			List<T> datas){
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		//this.setTotalCount(totalCount);
		this.datas = datas;
	}
	
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getDatas() {
		return datas;
	}
	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
