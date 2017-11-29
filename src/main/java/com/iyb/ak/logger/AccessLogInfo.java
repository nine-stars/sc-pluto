package com.iyb.ak.logger;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper=false)
//@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AccessLogInfo extends BaseLogInfo {

//	@Indexed
	private String url;
	private String method;
	private long time;

	private String remoteIp;
	private Map<String, String> requestHeaders;
	
	private AccessLogInfo(Object obj, String systemName, String url, String method, long time) {
		super(obj, systemName, "AccessLog");
		this.url = url;
		this.method = method;
		this.time = time;
	}
	
	public static AccessLogInfo build(Object obj, String systemName, String url, String method, long time) {
		return new AccessLogInfo(obj, systemName, url, method, time);
	}
}
