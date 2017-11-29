package com.iyb.ak.utils;

import org.apache.commons.lang.StringUtils;

import java.util.Locale;

public final class MessageUtil {

    private MessageUtil() {}

    public static String getMessage(Locale locale, String msgId, Object... params) {

        // 妥当性验证
        if (StringUtils.isEmpty(msgId)) {
            return null;
        }
        if (locale == null) {
            locale = Locale.getDefault();
        }
        try {
        	return ApplicationContextUtils.getApplicationContext().getMessage(msgId, params, locale);
        } catch (Exception e) {
        	return msgId;
        }
    }

    public static String getMessage(String msgId, Object... params) {
        return MessageUtil.getMessage(null, msgId, params);
    }
}

