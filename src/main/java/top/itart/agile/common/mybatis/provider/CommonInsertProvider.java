package top.itart.agile.common.mybatis.provider;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Joiner;

import top.itart.agile.common.model.BaseModel;
import top.itart.agile.common.util.BeanUtils;
import top.itart.agile.common.util.EmptyUtils;
import top.itart.agile.common.util.SQLUtils;

public class CommonInsertProvider {
    public static final String INSERT = "insert";
    
    public String insert(BaseModel parameter) {
        return insertWithPlaceholder(parameter);
    }
    
    private String insertWithPlaceholder(BaseModel model) {
        String tableName = BeanUtils.getTableNameByAnnotation(model);

        if (EmptyUtils.isEmpty(tableName)) {
            throw new RuntimeException("Model = " + model.getClass() + " has no definition table annotation.");
        }

        Map<String, String> nameValueMap = new HashMap<String, String>();
        Field[] fields = BeanUtils.getFields(model);
        try {

            for (Field field : fields) {
                nameValueMap.put(SQLUtils.camelToUnderscore(field.getName()), 
                        SQLUtils.nameToPlaceholder(field.getName(), field.getType()));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        StringBuilder builder = new StringBuilder();
        builder.append("Insert into ").append(tableName);
        builder.append(" (").append(Joiner.on(",").join(nameValueMap.keySet())).append(")");
        builder.append(" values ");
        builder.append(" ( #{").append(Joiner.on("},#{").join(nameValueMap.values())).append("} )");

        return builder.toString();
    }
}
