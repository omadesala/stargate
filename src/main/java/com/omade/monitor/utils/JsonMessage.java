package com.omade.monitor.utils;

import java.io.Serializable;

import org.apache.commons.httpclient.HttpStatus;

/**
 * @create bo.xu
 * @date Apr 20, 2016
 * @version 1.0
 * @description :y
 */
public class JsonMessage implements Serializable {

	private static final long serialVersionUID = -1882833679184634855L;

	public static final Integer ERROR = 409;

	public static final String NO_SUCH_CONTAINOR = "Container Not Found";
	public static final String NO_SUCH_OBJECT = "Object Not Found";

	/**
	 * return status
	 */
	private Integer status = HttpStatus.SC_BAD_REQUEST;
	/**
	 * 文本信息
	 */
	private String message = "error";

	public JsonMessage() {

	}

	public JsonMessage(Integer status, String message) {
		this.status = status;
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
