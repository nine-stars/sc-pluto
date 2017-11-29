package com.iyb.ak.logger;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AuditLogInfo extends BaseLogInfo {
	private String functionName;
	
	/**
	 * 访问数据的主键信息，不要包含敏感内容
	 */
	private String accessAction; //CRUD
	private String accessCompUuid;
	private String accessUserUuid;
	private String accessEmplUuid;
	private Object accessInfo;
	
	private AuditLogInfo(Object obj, String systemName, String functionName, String accessAction,
			String accessCompUuid, String accessUserUuid, String accessEmplUuid, Object accessInfo) {
		super(obj, systemName, "AuditLog");
		this.functionName = functionName;
		this.accessAction = accessAction;
		this.accessCompUuid = accessCompUuid;
		this.accessUserUuid = accessUserUuid;
		this.accessEmplUuid = accessEmplUuid;
		this.accessInfo = accessInfo;
	}
	
	public static AuditLogInfo build(Object obj, String systemName, String functionName, String accessAction,
			String accessCompUuid, String accessUserUuid, String accessEmplUuid, Object accessInfo) {
		return new AuditLogInfo(obj, systemName, functionName, accessAction, accessCompUuid,
				accessUserUuid, accessEmplUuid, accessInfo);
	}
}
