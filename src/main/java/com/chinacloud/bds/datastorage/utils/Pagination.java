package com.chinacloud.bds.datastorage.utils;

import java.util.Collections;
import java.util.List;

/**
 * Pagination
 * page class
 * */


public class Pagination<T> {
	
	   private int pageSize;  //the size of page
	   
	   private List<T> data;  //the data 
	   
	   private Pagination(List<T> data, int pageSize) {  
	        if (data == null || data.isEmpty()) {  
	            throw new IllegalArgumentException("data is empty!");  
	        }  
	        this.data = data;  
	        this.pageSize = pageSize;  
	   } 
	   
	   /** 
	     * To Create Page
	     * 
	     * @param data 
	     * @param pageSize 
	     * @param <T>  
	     * @return Pagination
	     */  
	    public static <T> Pagination<T> init(List<T> data, int pageSize) {  
	        return new Pagination<T>(data, pageSize);  
	    }  
	    
	    /** 
	     * To Get the specify page of Data
	     * 
	     * @param pageNum 
	     * @return the result of data
	     */  
	    public List<T> getPagedList(int pageNum) {  
	        int fromIndex = (pageNum - 1) * pageSize;  
	        if (fromIndex >= data.size()) {  
	            return Collections.emptyList();  
	        }  
	  
	        int toIndex = pageNum * pageSize;  
	        if (toIndex >= data.size()) {  
	            toIndex = data.size();  
	        }  
	        return data.subList(fromIndex, toIndex);  
	    }  
	  
	    public int getPageSize() {  
	        return pageSize;  
	    }  
	  
	    public List<T> getData() {  
	        return data;  
	    }  
	    
	   
}

