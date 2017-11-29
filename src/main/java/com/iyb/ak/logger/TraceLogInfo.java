package com.iyb.ak.logger;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class TraceLogInfo extends BaseLogInfo {

	private String traceMessage;
	
	private TraceLogInfo(Object obj, String systemName, String traceMessage) {
		super(obj, systemName, "TraceLog");
		this.traceMessage = traceMessage;
	}
	
	public static TraceLogInfo build(Object obj, String systemName, String traceMessage) {
		return new TraceLogInfo(obj, systemName, traceMessage);
	}
}
