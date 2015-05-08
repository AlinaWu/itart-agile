package top.itart.agile.common.util;

import java.util.Collection;
import java.util.Map;

/**
 * @ClassName: EmptyUtil.java
 * @Description: Determine object is empty
 * @author hymanz (www.itart.top)
 * @version V1.0
 * @Date Dec 18, 2014 5:23:39 PM
 */
public class EmptyUtils {

    public static boolean isEmpty(String value) {
        return null == value || "".equals(value.trim());
    }

    public static boolean isNotEmpty(String value) {
        return !isEmpty(value);
    }

    public static boolean isEmpty(Collection<?> value) {
        return null == value || value.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> value) {
        return !isEmpty(value);
    }

    public static boolean isEmpty(Map<Object, Object> value) {
        return null == value || value.isEmpty();
    }

    public static boolean isNotEmpty(Map<Object, Object> value) {
        return !isEmpty(value);
    }

    public static boolean isEmpty(Object[] value) {
        return null == value || value.length <= 0;
    }

    public static boolean isNotEmpty(Object[] value) {
        return !isEmpty(value);
    }
}
