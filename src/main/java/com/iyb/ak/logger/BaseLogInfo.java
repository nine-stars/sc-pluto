package com.iyb.ak.logger;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iyb.ak.interceptor.GlobalProcessIdFilter;
import com.iyb.ak.utils.JacksonUtil;
import lombok.Data;

import java.util.Date;

@Data
public class BaseLogInfo {
	private String type;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS",timezone = "GMT+8")
//	@Indexed
	private Date logTime;
	private String systemId;
	private String className;
	
	/**
	 * 	全局流水号
	 */
//	@Indexed
	private String globalProcessUuid;
//	@Indexed
	private String token;
	private String userUuid;
	private String compUuid;
	private String emplUuid;
	
	public BaseLogInfo() {
		setLogTime(new Date());
		setGlobalProcessUuid(GlobalProcessIdFilter.getGloblPid());
		setToken(GlobalProcessIdFilter.getAccessToken());
		setUserUuid(GlobalProcessIdFilter.getUserUuid());
		setCompUuid(GlobalProcessIdFilter.getCompUuid());
		setEmplUuid(GlobalProcessIdFilter.getEmplUuid());
	}
	
	public BaseLogInfo(Object obj, String systemName, String type) {
		this();
		setType(type);
		setSystemId(systemName);
		if (obj instanceof String)
			setClassName((String)obj);
		else
			setClassName(obj.getClass().getName());
	}
	
	public String toJson() {
		return JacksonUtil.toJSon(this);
	}
}
