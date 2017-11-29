package com.iyb.ak.utils;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
	private static final SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
	public static final String patternOfDay = "yyyy-MM-dd";
	public static final String cover_minute = ":00";//补位分
	public static final String cover_second = ":00";//补位秒

	/**
	 * 将“2013-06-19”转换成“20130619”
	 * 
	 * @description
	 * @author mowenbin
	 * @create 2013-6-20
	 * @param date
	 * @return
	 */
	public static String getFormart(String date) {
		String str = null;
		if (null != date && !"".equals(date)) {
			int len = date.length();
			if (8 == len) {
				String y = date.substring(0, 4);
				String m = date.substring(4, 6);
				String d = date.substring(6, 8);
				str = y + m + d;
			} else {
				String y = date.substring(0, 4);
				String m = date.substring(5, 7);
				String d = date.substring(8, 10);
				str = y + m + d;
			}
		}
		return str;
	}

	/**
	 * 将“20130619”转换成“2013-06-19”
	 * 
	 * @description
	 * @author mowenbin
	 * @create 2013-7-4
	 * @param date
	 * @return
	 */
	public static String getFormartStr(String date) {
		String str = null;
		if (null != date && !"".equals(date)) {
			String y = date.substring(0, 4);
			String m = date.substring(4, 6);
			String d = date.substring(6, 8);
			str = y + "-" + m + "-" + d;
		}
		return str;
	}

	public static String getYear(String date) {
		String year = null;
		if (StringUtils.isNotBlank(date)) {
			year = date.substring(0, 4);
		}
		return year;
	}

	public static String getDate(Timestamp date) {
		if (date == null) {
			return "";
		}
		return sf.format(date);
	}
	
	public static String getDate(Timestamp date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		if (date == null) {
			return "";
		}
		return sdf.format(date);
	}

	public static String getDate(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		if (date == null) {
			return "";
		}
		return sdf.format(date);
	}

	public static Timestamp getTime(Date date) {
		if (date != null) {
			return new Timestamp(date.getTime());
		}
		return null;
	}

	public static Date getDate(String dateStr) {
		try {
			if (dateStr != null && !"".equals(dateStr)) {
				dateStr = dateStr.replace(".", "-");
				return sf.parse(dateStr);
			}
		} catch (ParseException e) {
			e.printStackTrace();
			/*try {
				return sd.parse(dateStr);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}*/
			
		}
		return null;
	}

	public static Date getDate(String dateStr, String pattern) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			if (dateStr != null && !"".equals(dateStr)) {
				dateStr = dateStr.replace(".", "-");
				return sdf.parse(dateStr);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getDate(Date date) {
		if (date == null) {
			return null;
		}
		return sf.format(date);
	}

	//把日期转为字符串
	public static String format(Date _date, String _format) {

		if (_date == null) {
			return null;
		}

		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.setLenient(false);
		sdf.applyPattern(_format);

		try {
			return sdf.format(_date);
		} catch (Exception ex) {
			return null;
		}
	}

	public static int fetchAmOrPm(Date date) {
		// 创建 Calendar 对象
		Calendar calendar = Calendar.getInstance();
		// 初始化 Calendar 对象，但并不必要，除非需要重置时间
		calendar.setTime(date);
		// 显示年份
		int year = calendar.get(Calendar.YEAR);
		// 显示月份 (从0开始, 实际显示要加一)
		int month = calendar.get(Calendar.MONTH) + 1;
		// 今年的第 N 天
		// int DAY_OF_YEAR = calendar.get(Calendar.DAY_OF_YEAR);
		// System.out.println("DAY_OF_YEAR is = " + DAY_OF_YEAR);
		// 本月第 N 天
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		// 当前小时数
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		// 当前分钟数
		int minute = calendar.get(Calendar.MINUTE);
		GregorianCalendar ca = new GregorianCalendar(year, month, day, hour,
				minute);
		return ca.get(GregorianCalendar.AM_PM);
	}
	
	/**
	 * 字符串转时间戳
	 * @param date
	 * @return
	 */
	public static Timestamp getTime(String date){
		return Timestamp.valueOf(date);
	}
	
	/**
	 * 根据日期相加多少天
	 * @param time 相加前日期
	 * @param day 天数
	 * @return
	 */
	public static Timestamp daysPlus(Timestamp time, int day){
		Calendar cal = Calendar.getInstance();    
        cal.setTime(time);
        cal.add(Calendar.DAY_OF_MONTH, day);
        return getTime(cal.getTime());
	}
	/**
	 * 根据日期相加多少天
	 * @param time 相加前日期
	 * @param day 天数
	 * @return
	 */
	public static Date daysPlusOfDate(Date time, int day){
		Calendar cal = Calendar.getInstance();    
        cal.setTime(time);
        cal.add(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
	}
	
	/**
	 * 根据日期相减多少天
	 * @param time 相加前日期
	 * @param day 天数
	 * @return
	 */
	public static Timestamp daysLose(Timestamp date, int day){
		Calendar cal = Calendar.getInstance();    
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) - day);
        return getTime(cal.getTime());
	}
	
	/**
	 * 根据日期相加多少天
	 * @param time 相加前日期
	 * @param day 天数
	 * @return
	 */
	public static Date daysPlusOfDate(Timestamp time, int day){
		Calendar cal = Calendar.getInstance();    
        cal.setTime(time);
        cal.add(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
	}
	
	/**
	 * 根据日期相加多少分钟
	 * @param time 相加前日期
	 * @param minute 分钟数
	 * @return
	 */
	public static Timestamp minutePlus(Timestamp time, int minute){
		Calendar cal = Calendar.getInstance();    
        cal.setTime(time);
        cal.add(Calendar.MINUTE, minute);
        return getTime(cal.getTime());
	}
	
	/**
	 * 根据日期相加多少分钟
	 * @param time 相加前日期
	 * @param minute 分钟数
	 * @return
	 */
	public static Date minuteOfPlus(Timestamp time, int minute){
		Calendar cal = Calendar.getInstance();    
        cal.setTime(time);
        cal.add(Calendar.MINUTE, minute);
        return cal.getTime();
	}
	
	/**
	 * 根据日期相加多少分钟
	 * @param time 相加前日期
	 * @param minute 分钟数
	 * @return
	 */
	public static Date minutePlusOfDate(Timestamp time, int minute){
		Calendar cal = Calendar.getInstance();    
        cal.setTime(time);
        cal.add(Calendar.MINUTE, minute);
        return cal.getTime();
	}

    /**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int daysBetween(Date smdate,Date bdate) throws ParseException {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    }
}
