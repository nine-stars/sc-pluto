package com.iyb.ak.exception;

import com.iyb.ak.entity.base.MessageVo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BusinessException extends BaseException {

    private static final long serialVersionUID = 3086627551412398745L;

    public BusinessException() {
        super();
    }

    public BusinessException(String errCode, Object srcClass) {
        super(errCode, srcClass);
    }

    public BusinessException(Throwable throwable, Object srcClass) {
        super(throwable, srcClass);
    }

    public BusinessException(String errCode, Throwable throwable, Object srcClass) {
        super(errCode, throwable, srcClass);
    }

    public BusinessException(MessageVo messages, Object srcClass) {
    	super();
    	setSrcClass(srcClass);
    	setMessageVo(messages);
    }

    /**
     * 
     * @param errCode
     * @param srcClass
     * @param values 占位符信息
     */
    public BusinessException(String errCode, Object srcClass, Object... values) {
    	super();
    	setSrcClass(srcClass);
   		setMessage(errCode, values);
    }
    
    /**
     * 
     * @param errCode
     * @param srcClass
     * @param field 占位字段名
     */
    public BusinessException(String errCode, Object srcClass, String field) {
    	super();
    	setSrcClass(srcClass);
   		setMessage(errCode, field);
    }
}
