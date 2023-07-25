package com.damddos.commons;

public class ResponseMessage<T> {

    private int code;

    private String message;

    private T result;
    
    private ResponseMessage() {
	}

	public ResponseMessage(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }

    public ResponseMessage(ResponseCode responseCode,T result) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        this.result = result;
    }

    public ResponseMessage(ResponseCode responseCode,String message) {
        this.code = responseCode.getCode();
        this.message = message;
    }

    public ResponseMessage(ResponseCode responseCode,String message,T result) {
        this.code = responseCode.getCode();
        this.message = message;
        this.result = result;
    }

    public static ResponseMessage success() {
        return new ResponseMessage(ResponseCode.SUCCESS);
    }
    
    public static ResponseMessage error() {
        return new ResponseMessage(ResponseCode.ERROR);
    }
    

    public static ResponseMessage build(ResponseCode responseCode) {
        return new ResponseMessage(responseCode);
    }
    public static<T> ResponseMessage<T> build(ResponseCode responseCode,T result) {
        return new ResponseMessage(responseCode,result);
    }
    public static ResponseMessage build(ResponseCode responseCode,String message) {
        return new ResponseMessage(responseCode,message);
    }
    public static<T> ResponseMessage<T> build(ResponseCode responseCode,String message,T result) {
        return new ResponseMessage(responseCode,message,result);
    }

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

    

}

