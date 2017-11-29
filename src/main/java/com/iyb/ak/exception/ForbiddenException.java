package com.iyb.ak.exception;

import com.iyb.ak.entity.base.MessageVo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ForbiddenException extends BaseException {

	private static final long serialVersionUID = -1490728564509176810L;

	public ForbiddenException() {
        super();
    }

    public ForbiddenException(String errCode, Object srcClass) {
        super(errCode, srcClass);
    }

    public ForbiddenException(Throwable throwable, Object srcClass) {
        super(throwable, srcClass);
    }

    public ForbiddenException(String errCode, Throwable throwable, Object srcClass) {
        super(errCode, throwable, srcClass);
    }
    
    public ForbiddenException(MessageVo messages, Object srcClass) {
    	super();
    	setSrcClass(srcClass);
    	setMessageVo(messages);
    }

}