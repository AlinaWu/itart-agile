package top.itart.agile.common.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import top.itart.agile.common.model.BaseModel;

import com.google.common.base.CaseFormat;
import com.google.common.base.Joiner;

public class SQLUtils {

    public static final String DEFAULT_DATE_PATTERN = "mm/dd/yyyy hh24:mi:ss";
    
    public static final Map<Class<?>, String> classjdbcTypeMap = new HashMap<Class<?>, String>();
    
    static {
        classjdbcTypeMap.put(String.class, "VARCHAR");
        classjdbcTypeMap.put(Integer.class, "INTEGER");
        classjdbcTypeMap.put(Float.class, "FLOAT");
        classjdbcTypeMap.put(Date.class, "DATE");
        classjdbcTypeMap.put(Long.class, "INTEGER");
        classjdbcTypeMap.put(Double.class, "DOUBLE");
        
    }

    public static String insert(BaseModel model) {
        String tableName = BeanUtils.getTableNameByAnnotation(model);

        if (EmptyUtils.isEmpty(tableName)) {
            throw new RuntimeException("Model = " + model.getClass() + " has no definition table annotation.");
        }

        List<String> fieldNames = new ArrayList<>();
        List<String> fieldValues = new ArrayList<>();

        Field[] fields = BeanUtils.getFields(model);
        try {

            for (Field field : fields) {
                fieldNames.add(camelToUnderscore(field.getName()));

                Object value = BeanUtils.getProperty(model, field.getName());
                fieldValues.add(value2String(value, field.getType()));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        StringBuilder builder = new StringBuilder();
        builder.append("Insert into ").append(tableName);
        builder.append(" (").append(Joiner.on(",").join(fieldNames)).append(")");
        builder.append(" values ");
        builder.append(" (").append(Joiner.on(",").useForNull("null").join(fieldValues)).append(")");

        return builder.toString();
    }

    public static String value2String(Object value, Class<?> claze) {
        if (null == value) {
            return null;
        }

        if (claze == String.class) {
            return "'" + value + "'";
        }

        if (claze == Double.class) {
            return NumberUtils.format((Double) value);
        }

        if (claze == Float.class) {
            NumberUtils.format((Float) value);
        }

        if (claze == Date.class) {
            return "to_date('" + DateUtils.format((Date) value) + "', '" + DEFAULT_DATE_PATTERN + "')";
        }
        return value + "";
    }

    public static String camelToUnderscore(String name) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, name);
    }

    public static String nameToPlaceholder(String name, Class<?> type) {

        String jdbcType = classjdbcTypeMap.get(type);
        if (StringUtils.isNotEmpty(jdbcType)) {
            return name + ", jdbcType = " + jdbcType;
        }
        return name;
    }

  /*  public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException,
            InvocationTargetException {
        User user = new User();
        user.setUserId(1111);
        user.setCreatedBy("name");
        user.setCreatedWhen(new Date());
        System.out.println(insertWithPlaceholder(user));
    }*/
}
