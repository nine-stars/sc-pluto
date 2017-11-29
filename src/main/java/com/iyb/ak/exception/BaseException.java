package com.iyb.ak.exception;


import com.iyb.ak.entity.base.MessageObj;
import com.iyb.ak.entity.base.MessageVo;
import com.iyb.ak.utils.JacksonUtil;
import com.iyb.ak.utils.MessageUtil;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public abstract class BaseException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1800374899031616535L;

	public static final String ERR_9999 = "error.9999"; //system error
	public static final String ERR_9998 = "error.9998"; //doesn't login
	public static final String ERR_9997 = "error.9997"; //no permission
	public static final String ERR_9996 = "error.9996"; //no found

	//当status为3xx, 4xx, 5xx的时候返回错误消息对象
	private MessageVo messages = null;
	
    private String errorCode = null;

	
	private Throwable originalException = null;
	
	/**
	 * 出现异常的对象
	 */
	private Object srcClass;
	
	/**
	 * 构造函数
	 */
    public BaseException() {
    }
    
	/**
	 * 构造函数
	 * 
	 * @param errCode
	 * @param message
	 * @param srcClass
	 */
	public BaseException(String errCode, String message, Object srcClass) {
		super(message);
		setMessage(errCode, "", message);
		this.srcClass = srcClass;
	}
	
	/**
	 * 构造函数
	 * 
	 * @param errCode
	 */
	public BaseException(String errCode, Object srcClass) {
		String message = MessageUtil.getMessage(errCode);
		setMessage(errCode, "", message);
		this.srcClass = srcClass;
	}

	/**
	 * 构造函数
	 * 
	 * @param errCode
	 * @param e
	 */
	public BaseException(String errCode, Throwable e, Object srcClass) {
		this(errCode, MessageUtil.getMessage(errCode), srcClass);
		originalException = e;
	}

	/**
	 * 构造函数
	 */
	public BaseException(Throwable e, Object srcClass) {
		this(ERR_9999, srcClass);
		originalException = e;
	}

    
    public MessageVo getMessageList() {
    	return messages;
    }
    
    public Throwable getOriginalException() {
    	return originalException;
    }

    public void setMessage(String messageCode, String field) {
        this.errorCode = messageCode;
    	MessageVo messageas = getMessageVo();
    	String objName = MessageUtil.getMessage(field);
    	if (objName != null) {
    		messageas.addMessageObj(messageCode, MessageUtil.getMessage(messageCode, objName), field);
    	} else {
    		messageas.addMessageObj(messageCode, MessageUtil.getMessage(messageCode), field);
    	}
    }
    
    public void setMessage(String messageCode, Object... values) {
        this.errorCode = messageCode;
    	MessageVo messageas = getMessageVo();
   		messageas.addMessageObj(messageCode, MessageUtil.getMessage(messageCode, values), null);
    }

    
    public void setMessage(String messageCode, String field, String message) {
    	MessageVo messageas = getMessageVo();
    	messageas.addMessageObj(messageCode, message, field);
    	this.errorCode = messageCode;
    }
    
    private MessageVo getMessageVo() {
    	if (messages == null)
    		messages = new MessageVo();
		return messages;

    }
    
    protected void setMessageVo(MessageVo messages) {
    	this.messages = messages;
    }
    
    protected void setSrcClass(Object srcClass) {
    	this.srcClass = srcClass;
    }
    
    public Object getSrcClass() {
    	return this.srcClass;
    }
    
	/**
	 * 获取消息字符串
	 * 
	 * @return 消息字符串
	 * @see Throwable#getMessage()
	 */
	public String getMessage() {
		StringBuffer buf = new StringBuffer();
		buf.append(getClass().getName()).append(":\r\n");
		if (messages != null) {
			for(MessageObj msg:messages.getReasons()) {
				if (msg.getMsg_id() != null) {
					buf.append("ErrorCode:[").append(msg.getMsg_id()).append("]")
							.append("\r\n");
				}
				buf.append("ErrorMesg:[").append(msg.getMessage()).append("]")
						.append("\r\n");

			}
		}

		return buf.toString();
	}

	/**
	 * 异常实例转String，返回消息字符串
	 *
	 * @return 消息字符串
	 * @see Object#toString()
	 */
	public String toString() {
		return getMessage();
	}

    
	public static String get(Throwable t) {
		//
		if (t == null) {
			return "NULL";
		}
		// Create
		StringBuffer b = new StringBuffer();
		//
		b.append(t.getMessage());
		b.append("\n");
		//
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		//
		t.printStackTrace(ps);
		//
		b.append(baos.toString());
		//
		return b.toString();
	}
	
    public String getErrorCode() {
        return errorCode;
    }
    
	public String getJsonMessage() {
		if (getMessageList() == null)
			return null;
		return JacksonUtil.toJSon(getMessageList());
	}
}
