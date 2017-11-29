package com.iyb.ak.exception;

import com.iyb.ak.entity.base.MessageVo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends BaseException {

    private static final long serialVersionUID = -8687066286979480116L;

    public NotFoundException() {
        super();
    }

    public NotFoundException(String errCode, Object srcClass) {
        super(errCode, srcClass);
    }

    public NotFoundException(Throwable throwable, Object srcClass) {
        super(throwable, srcClass);
    }

    public NotFoundException(String errCode, Throwable throwable, Object srcClass) {
        super(errCode, throwable);
    }
    
    public NotFoundException(MessageVo messages, Object srcClass) {
    	super();
    	setSrcClass(srcClass);
    	setMessageVo(messages);
    }

}
