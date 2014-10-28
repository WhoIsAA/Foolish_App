package com.foolish.app.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 字符串处理类
 * @author AA
 * @date 2014-10-20
 *
 */
public class StringUtils {

	/**
	 * 如果为null，返回空字符串
	 * @param src
	 * @return
	 */
	public static String checkNull(String src) {
		return src == null ? "" : src;
	}
	
	
	/**
	 * 如果为null或为空，返回字符串："0"
	 * @param src
	 * @return
	 */
	public static String emptyConvertZero(String src) {
		return (src == null || src.trim().equals("")) ? "0" : src;
	}
	
	
	/**
	 * 获得当前时间 格式：MM--dd HH:mm
	 * @return
	 */
	public static String getCurTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA);
		return sdf.format(new Date());
	}
	
	
	/**
	 * 是否不为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNotEmpty(String s) {
		return s != null && !"".equals(s.trim());
	}

	
	/**
	 * 是否为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		return s == null || "".equals(s.trim());
	}

	
	/**
	 * 通过{n},格式化.
	 * 
	 * @param src
	 * @param objects
	 * @return
	 */
	public static String format(String src, Object... objects) {
		int k = 0;
		for (Object obj : objects) {
			src = src.replace("{" + k + "}", obj.toString());
			k++;
		}
		return src;
	}
	
}
