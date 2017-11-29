package com.iyb.ak.interceptor;

import com.iyb.ak.constants.SecurityConstants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnBean(GlobalProcessIdFilter.class)
@Slf4j
public class FeignRequestInterceptor implements RequestInterceptor {
	@Override
	public void apply(RequestTemplate template) {
		//在header中插入追踪数据
		try {
			if(StringUtils.isNotEmpty(GlobalProcessIdFilter.getGloblPid()))
				template.header(SecurityConstants.GLOBAL_PID, GlobalProcessIdFilter.getGloblPid());
			if(StringUtils.isNotEmpty(GlobalProcessIdFilter.getAccessToken()))
				template.header(SecurityConstants.ACCESS_TOKEN, GlobalProcessIdFilter.getAccessToken());
			if(StringUtils.isNotEmpty(GlobalProcessIdFilter.getUserUuid()))
				template.header(SecurityConstants.USER_UUID, GlobalProcessIdFilter.getUserUuid());
			if(StringUtils.isNotEmpty(GlobalProcessIdFilter.getCompUuid()))
				template.header(SecurityConstants.COMPUUID, GlobalProcessIdFilter.getCompUuid());
			if(StringUtils.isNotEmpty(GlobalProcessIdFilter.getEmplUuid()))
				template.header(SecurityConstants.EMPL_UUID, GlobalProcessIdFilter.getEmplUuid());
		} catch (Exception e) {
			log.error("header set tracking data error.", e);
		}
	}
}