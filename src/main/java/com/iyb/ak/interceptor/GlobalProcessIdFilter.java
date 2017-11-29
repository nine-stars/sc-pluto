/*
 * Copyright (c) 2016. 上海云才网络科技有限公司 版权所有
 * Shanghai YunCai Network Technology Co., Ltd. All Rights Reserved.
 *
 * This is NOT a freeware,use is subject to license terms.
 */

package com.iyb.ak.interceptor;

import com.google.common.collect.Maps;
import com.iyb.ak.constants.SecurityConstants;
import com.iyb.ak.logger.AccessLogInfo;
import com.iyb.ak.logger.service.LogService;
import com.iyb.ak.utils.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 生成并记录全局流水号.
 *
 */
@SuppressWarnings("all")
@Component
@Order(value = Ordered.LOWEST_PRECEDENCE)
@Slf4j
public class GlobalProcessIdFilter implements Filter {
	@Value("${spring.application.name:}")
	private String systemName;

	private static ThreadLocal<String> GLOBAL_PID = new ThreadLocal<>();
	private static ThreadLocal<String> ACCESS_TOKEN = new ThreadLocal<>();
	private static ThreadLocal<String> USER_UUID = new ThreadLocal<>();
	private static ThreadLocal<String> COMP_UUID = new ThreadLocal<>();
	private static ThreadLocal<String> EMPL_UUID = new ThreadLocal<>();

	public static String getGloblPid(){
		return GLOBAL_PID.get();
	}
	public static String getAccessToken() {
		return ACCESS_TOKEN.get();
	}
	public static String getUserUuid() {
		return USER_UUID.get();
	}
	public static String getCompUuid() {
		return COMP_UUID.get();
	}
	public static String getEmplUuid() {
		return EMPL_UUID.get();
	}
	public static void setAccessToken(String token) {
		ACCESS_TOKEN.set(token);
	}
	public static void setUserUuid(String userUuid) {
		USER_UUID.set(userUuid);
	}
	public static void setCompUuid(String compUuid) {
		COMP_UUID.set(compUuid);
	}
	public static void setEmplUuid(String emplUuid) {
		EMPL_UUID.set(emplUuid);
	}

    /**
     * 初始化
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        // left blank intentionally
    }

    /**
     * 过滤
     *
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain filterChain)
            throws IOException, ServletException {
	    systemName = SpringUtils.getProperty("spring.application.name", "");
	    HttpServletRequest request = (HttpServletRequest)req;
	    long startTime = System.currentTimeMillis();

        if (!RequestMethod.OPTIONS.name().equals(((HttpServletRequest)req).getMethod())) {
        	String curUrl = ((HttpServletRequest)req).getRequestURI();
			if(!(StringUtils.endsWithAny(curUrl, ".js",".css",".html")
					|| StringUtils.endsWithAny(curUrl, ".jpg",".png",".gif", ".jpeg", ".ico")
					|| StringUtils.endsWithAny(curUrl, ".woff", ".woff2"))) {
				setGlobalProcessValue((HttpServletRequest)req, (HttpServletResponse) res);
			}	
        }
	    AccessLogInfo logInfo = AccessLogInfo.build(this, systemName, request.getRequestURI().toString(), request.getMethod(), 0);
        filterChain.doFilter(req, res);

        long endTime = System.currentTimeMillis();
	    //access log
	    logInfo.setTime(endTime - startTime);
	    logInfo.setRemoteIp(request.getRemoteAddr());
	    log.debug(logInfo.toJson());

	    //save access log
	    List<String> profiles = SpringUtils.getActiveProfiles();
//	    if(systemName.equalsIgnoreCase("gin-gateway") && profiles.contains("dev")) {
//	    if(!profiles.contains("dev")) { // 不是dev都保存到mongodb
		if(true) {
	    	Map<String, String> headerMap = Maps.newHashMap();
		    Collections.list(request.getHeaderNames()).stream().forEach(name -> headerMap.put(name, request.getHeader(name)));
		    logInfo.setRequestHeaders(headerMap);

		    /*LogService logService = SpringUtils.getBean(LogService.class);
		    if(logService != null){
		    	logService.save(logInfo);
			}*/
	    }
    }

    private void setGlobalProcessValue(HttpServletRequest req, HttpServletResponse response) {
        String globalUuid = req.getHeader(SecurityConstants.GLOBAL_PID);
        if (StringUtils.isEmpty(globalUuid)) {
	        globalUuid = generateGlobalUuid();
        }
	    GLOBAL_PID.set(globalUuid);

        String token = req.getHeader(SecurityConstants.ACCESS_TOKEN);
	    if(StringUtils.isNotEmpty(token))
	        ACCESS_TOKEN.set(token);

	    String userUuid = req.getHeader(SecurityConstants.USER_UUID);
	    if(StringUtils.isNotEmpty(userUuid))
	        USER_UUID.set(userUuid);

	    String compUuid = req.getHeader(SecurityConstants.COMPUUID);
	    if(StringUtils.isNotEmpty(compUuid))
	        COMP_UUID.set(compUuid);

	    String EmplUuid = req.getHeader(SecurityConstants.EMPL_UUID);
	    if(StringUtils.isNotEmpty(EmplUuid))
	        EMPL_UUID.set(EmplUuid);
    }
    
    private String generateGlobalUuid() {
    	StringBuffer gid = new StringBuffer();
    	//1. 年月日
    	Calendar currentTime = Calendar.getInstance();
    	SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
    	gid.append(formatter.format(currentTime.getTime()));
    	//2. 秒
    	long sec = currentTime.get(Calendar.HOUR_OF_DAY)*3600+currentTime.get(Calendar.MINUTE)*60
    		+ currentTime.get(Calendar.SECOND);
    	NumberFormat nf = NumberFormat.getInstance();
    	//设置是否使用分组
        nf.setGroupingUsed(false);
        //设置最小整数位数    
        nf.setMinimumIntegerDigits(5);
        gid.append(nf.format(sec));
    	
    	//3. 随机数
    	Random r =new Random();
    	
		int i = r.nextInt(99999999);
		//设置最小整数位数    
        nf.setMinimumIntegerDigits(8);
        gid.append(nf.format(i));

        return gid.toString();
    }

    /**
     * do nothing
     */
    @Override
    public void destroy() {
        // left blank intentionally
    }
}