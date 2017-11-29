package com.iyb.ak.exception;

import com.iyb.ak.entity.base.MessageVo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.MOVED_PERMANENTLY)
public class MovedPermanentlyException extends BaseException {

    private static final long serialVersionUID = 8935143312958653140L;

    public MovedPermanentlyException() {
        super();
    }

    public MovedPermanentlyException(String errCode, Object srcClass) {
        super(errCode, srcClass);
    }

    public MovedPermanentlyException(Throwable throwable, Object srcClass) {
        super(throwable, srcClass);
    }

    public MovedPermanentlyException(String errCode, Throwable throwable, Object srcClass) {
        super(errCode, throwable, srcClass);
    }
    
    public MovedPermanentlyException(MessageVo messages, Object srcClass) {
    	super();
    	setSrcClass(srcClass);
    	setMessageVo(messages);
    }

}
