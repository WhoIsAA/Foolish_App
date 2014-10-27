package com.foolish.app.common;

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
		return (src == null || src.equals("")) ? "0" : src;
	}
	
	
	/**
	 * 获得当前时间 格式：MM--dd HH:mm
	 * @return
	 */
	public static String getCurTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA);
		return sdf.format(new Date());
	}
	
	
}
