package com.iyb.ak.utils;

import java.math.BigDecimal;
import java.util.Random;


public class NumberUtils {
	
	public static final long getLongValue(Object value) {
		if (value == null || "".equals(value))
			return -1;
		if (value instanceof Long) {
			return ((Long) value).longValue();
		}
		if (value instanceof BigDecimal) {
			return ((BigDecimal) value).longValue();
		}
		if(value instanceof Double){
			return ((Double) value).longValue();
		}
		if(value instanceof Float){
			return ((Float) value).longValue();
		}
		if(value instanceof Short){
			return ((Short) value).longValue();
		}
		if(value instanceof Integer){
			return ((Integer) value).longValue();
		}
		if(value instanceof Byte){
			return ((Byte) value).longValue();
		}
		if (value instanceof String) {
			try{
				return Long.valueOf((String)value).longValue();
			}finally{
				
			}
		}
		return -1;
	}
	
	public static final int getIntValue(Object value) {
		if (value == null || "".equals(value))
			return -1;
		if (value instanceof Long) {
			return ((Long) value).intValue();
		}
		if (value instanceof BigDecimal) {
			return ((BigDecimal) value).intValue();
		}
		if(value instanceof Double){
			return ((Double) value).intValue();
		}
		if(value instanceof Float){
			return ((Float) value).intValue();
		}
		if(value instanceof Short){
			return ((Short) value).intValue();
		}
		if(value instanceof Integer){
			return ((Integer) value).intValue();
		}
		if(value instanceof Byte){
			return ((Byte) value).intValue();
		}
		if (value instanceof String) {
			try{
				return Integer.valueOf((String)value).intValue();
			}finally{
				
			}
		}
		return -1;
	}
	
	/**
	 * 获取随机数
	 * @param min 最小随机数
	 * @param max 最大随机数
	 * @return 随机数
	 */
	public static final int getRandomNum(final int min, final int max) {
		Random rand = new Random();
		int tmp = Math.abs(rand.nextInt());
		return tmp % (max - min + 1) + min;
	}
}
