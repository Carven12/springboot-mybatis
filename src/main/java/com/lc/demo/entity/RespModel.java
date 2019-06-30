package com.lc.demo.entity;

public class RespModel {

	private int code;
	private String msg;
	private Object data;
	
	public RespModel(int code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	public RespModel(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}
