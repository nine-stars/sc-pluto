package com.iyb.ak.exception;

import com.iyb.ak.entity.base.MessageVo;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends BaseException {

    private static final long serialVersionUID = -8998072342743536663L;

    public BadRequestException() {
        super();
    }

    public BadRequestException(String errCode, Object srcClass) {
        super(errCode, srcClass);
    }

    public BadRequestException(Throwable throwable, Object srcClass) {
        super(throwable, srcClass);
    }

    public BadRequestException(String errCode, Throwable throwable, Object srcClass) {
        super(errCode, throwable, srcClass);
    }
    
    public BadRequestException(MessageVo messages, Object srcClass) {
    	super();
    	setSrcClass(srcClass);
    	setMessageVo(messages);
    }
    
    public BadRequestException(BindingResult result, Object srcClass) {
    	super();
    	setSrcClass(srcClass);
    	if(result.hasErrors()){
            List<ObjectError>  list = result.getAllErrors();
            for(ObjectError error:list){
            	if (error instanceof FieldError) {
            		FieldError ferr = (FieldError) error;
            		setMessage(ferr.getDefaultMessage(), ferr.getField());
            	} else {
            		setMessage(error.getDefaultMessage(), "");
            	}
            }
    	}
    }
    
    /**
     * 
     * @param errCode
     * @param srcClass
     * @param values 占位符信息
     */
    public BadRequestException(String errCode, Object srcClass, Object... values) {
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
    public BadRequestException(String errCode, Object srcClass, String field) {
    	super();
    	setSrcClass(srcClass);
   		setMessage(errCode, field);
    }
}
