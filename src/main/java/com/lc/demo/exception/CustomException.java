package com.lc.demo.exception;

import com.lc.demo.entity.ResultJson;

public class CustomException  extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8441873636680994003L;
	
	private ResultJson<?> resultJson;

    public ResultJson<?> getResultJson() {
		return resultJson;
	}

	public void setResultJson(ResultJson<?> resultJson) {
		this.resultJson = resultJson;
	}

	public CustomException(ResultJson<?> resultJson) {
        this.resultJson = resultJson;
    }
}
