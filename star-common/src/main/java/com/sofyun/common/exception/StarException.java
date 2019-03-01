package com.sofyun.common.exception;

/**
 * @ClassName StarException
 * @Description TODO
 * @Author gm
 * @Date 2019/2/28 17:35
 **/
public class StarException extends RuntimeException {

    private static final long serialVersionUID = -7264548751118669394L;

    private String message;

    public StarException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
