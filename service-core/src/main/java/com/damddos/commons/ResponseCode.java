package com.damddos.commons;

public enum ResponseCode {
    SUCCESS(0, "成功"),
    ERROR(-1, "服务器内部错误"),
	
    //-1xx 服务器错误  安全问题 不能暴露内部逻辑
    BAD_SQL_GRAMMAR_ERROR(-101, "数据库错误"),
    
//    -2xx 参数校验     
//    
    USERNAME_NULL_ERROR(-202, "用户名称不能为空"),
    PASSWORD_NULL_ERROR(204, "密码不能为空"),
	
    CODE_NULL_ERROR(205, "验证码不能为空"),
    CODE_ERROR(206, "验证码错误"),
    
    LOGIN_MOBILE_ERROR(208, "用户不存在"),
    LOGIN_PASSWORD_ERROR(209, "密码错误");
	
    private Integer code;
    private String message;
    
	private ResponseCode(Integer code, String message) {
		this.code = code;
		this.message = message;
	}	

	public void setCode(Integer code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}
   
}
