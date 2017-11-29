/*
 * Copyright (c) 2016. 上海云才网络科技有限公司 版权所有
 * Shanghai YunCai Network Technology Co., Ltd. All Rights Reserved.
 *
 * This is NOT a freeware,use is subject to license terms.
 */

package com.iyb.ak.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 允许跨域.
 *
 * @author penny
 * @version 1.0, 2016-11-4
 * @since 1.0
 */
@SuppressWarnings("all")
@Component
public class SimpleCORSFilter implements Filter {

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
        setHeader((HttpServletRequest)req, (HttpServletResponse) res);
        if (RequestMethod.OPTIONS.name().equals(((HttpServletRequest)req).getMethod()))
        	return;
        filterChain.doFilter(req, res);
    }

    private void setHeader(HttpServletRequest req, HttpServletResponse response) {
    	String origin = req.getHeader("Origin");
    	if (origin == null)
    		origin = "*";
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, PATCH");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,accept,Content-Type,authorization,Api-Version,xa_token");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Expose-Headers", "xa_token");
    }

    /**
     * do nothing
     */
    @Override
    public void destroy() {
        // left blank intentionally
    }
}