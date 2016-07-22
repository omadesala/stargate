package com.omade.monitor.utils;

import java.io.Serializable;

import org.apache.commons.httpclient.HttpStatus;

public class JsonMessage implements Serializable {

	private static final long serialVersionUID = -1882833679184634855L;

	private Integer status = HttpStatus.SC_BAD_REQUEST;
	private String message = "";
	private Object data = "";

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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "JsonMessage [status=" + status + ", message=" + message
				+ ", data=" + data + "]";
	}
}
