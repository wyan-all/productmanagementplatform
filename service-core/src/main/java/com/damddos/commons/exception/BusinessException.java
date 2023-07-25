package com.damddos.commons.exception;

import com.damddos.commons.ResponseCode;

public class BusinessException extends RuntimeException{

    private Integer code;
    private String message;


    public BusinessException(String message) {
        this.message = message;
    }

    public BusinessException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    /**
     * @param message 错误消息
     * @param code    错误码
     * @param cause   原始异常对象
     */
    public BusinessException(String message, Integer code, Throwable cause) {
        super(cause);
        this.message = message;
        this.code = code;
    }

    /**
     * @param resultCodeEnum 接收枚举类型
     */
    public BusinessException(ResponseCode resultCodeEnum) {
        this.message = resultCodeEnum.getMessage();
        this.code = resultCodeEnum.getCode();
    }

    /**
     * @param resultCodeEnum 接收枚举类型
     * @param cause          原始异常对象
     */
    public BusinessException(ResponseCode resultCodeEnum, Throwable cause) {
        super(cause);
        this.message = resultCodeEnum.getMessage();
        this.code = resultCodeEnum.getCode();
    }

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
        
}
