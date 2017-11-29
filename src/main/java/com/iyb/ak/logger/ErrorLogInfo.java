package com.iyb.ak.logger;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ErrorLogInfo extends BaseLogInfo {

	private String errorCode;
	
	private String errorMessage;
	
	private ErrorLogInfo(Object obj, String systemName, String errorCode, String errorMessage) {
		super(obj, systemName, "ErrorLog");
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public static ErrorLogInfo build(Object obj, String systemName, String errorCode, String errorMessage) {
		return new ErrorLogInfo(obj, systemName, errorCode, errorMessage);
	}
	
	public static ErrorLogInfo build(Object obj, String systemName, String errorCode, String errorMessage, String userUuid) {
		ErrorLogInfo err = new ErrorLogInfo(obj, systemName, errorCode, errorMessage);
		if (userUuid != null)
			err.setUserUuid(userUuid);
		return err;
	}
}
