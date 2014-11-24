package com.piaohai.mis.common.util;
import java.util.Collection;
import java.util.Map;

/**
 * <p>Description:  判斷非空工具類</p>
 * @author 张建伟
 * @version 1.00
 * <pre>
 * 修改记录:
 * 修改后版本			修改人		修改日期			修改内容	
 * 2010-7-24.1		张建伟		2010-7-24		create
 * </pre>
 */
public class EmptyUtil {
	/**
	 *  判断String类型是否为空，null和空白返回true
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(String value){
		return null==value||"".equals(value.trim());
	}
	/**
	 * 判断String类型是否为不为空，null和空白返回false
	 * @param value
	 * @return
	 */
	public static boolean isNotEmpty(String value){
		return !isEmpty(value);
	}
	/**
	 * 判断Object类型是否为空，null和空白返回false
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(Object value){
		return null==value||"".equals(value);
	}
	/**
	 * 判断Object类型是否为不为空，null和空白返回false
	 * @param value
	 * @return
	 */
	public static boolean isNotEmpty(Object value){
		return !isEmpty(value);
	}
	/**
	 *  判断Collection类型是否为空，null和空白返回true
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(Collection<?> value){
		return null==value||value.isEmpty();
	}
	/**
	 * 判断Collection类型是否为不为空，null和空白返回false
	 * @param value
	 * @return
	 */
	public static boolean isNotEmpty(Collection<?> value){
		return !isEmpty(value);
	}
	/**
	 *  判断Map类型是否为空，null和空白返回true
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(Map<Object, Object> value){
		return null==value||value.isEmpty();
	}
	/**
	 * 判断Map类型是否为不为空，null和空白返回false
	 * @param value
	 * @return
	 */
	public static boolean isNotEmpty(Map<Object, Object> value){
		return !isEmpty(value);
	}
	/**
	 * 判断数组是否为空，null和数组长度小于0时返回true
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(Object [] value){
		return null==value||value.length<=0;
	}
	/**
	 * 判断数组是否不为空，null和数组长度小于0时返回false
	 * @param value
	 * @return
	 */
	public static boolean isNotEmpty(Object [] value){
		return !isEmpty(value);
	}
}
