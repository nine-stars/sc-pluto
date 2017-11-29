package com.iyb.ak.exception;

import com.iyb.ak.entity.base.MessageVo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends BaseException {

	private static final long serialVersionUID = -1490728564509176810L;
	
	private String loginId;

	public UnauthorizedException() {
        super();
    }

    public UnauthorizedException(String errCode, Object srcClass) {
        super(errCode, srcClass);
    }

    public UnauthorizedException(Throwable throwable, Object srcClass) {
        super(throwable, srcClass);
    }

    public UnauthorizedException(String errCode, Throwable throwable, Object srcClass) {
        super(errCode, throwable, srcClass);
    }
    
    public UnauthorizedException(String errCode, String loginId, Object srcClass) {
        super(errCode, srcClass);
        this.loginId = loginId;
    }
    
    public UnauthorizedException(MessageVo messages, Object srcClass) {
    	super();
    	setSrcClass(srcClass);
    	setMessageVo(messages);
    }

    public String getLoginId() {
    	return loginId;
    }
}