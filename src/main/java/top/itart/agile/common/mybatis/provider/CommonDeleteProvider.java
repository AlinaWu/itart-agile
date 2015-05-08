package top.itart.agile.common.mybatis.provider;

import java.lang.reflect.Field;

import top.itart.agile.common.annotation.Id;
import top.itart.agile.common.model.BaseModel;
import top.itart.agile.common.util.BeanUtils;
import top.itart.agile.common.util.EmptyUtils;
import top.itart.agile.common.util.SQLUtils;

public class CommonDeleteProvider {
    
    public static final String DELETE = "delete";

    public String delete(BaseModel parameter) {
        return deleteWithPlaceholder(parameter);
    }

    private String deleteWithPlaceholder(BaseModel model) {
        String tableName = BeanUtils.getTableNameByAnnotation(model);

        if (EmptyUtils.isEmpty(tableName)) {
            throw new RuntimeException("Model = " + model.getClass() + " has no definition table annotation.");
        }

        StringBuilder builder = new StringBuilder();
        builder.append("delete ").append(tableName);
        builder.append("where ").append(tableName);

        Field[] fields = BeanUtils.getFields(model);
        try {
            for (Field field : fields) {
                if (null != field.getAnnotation(Id.class)) {
                    builder.append(SQLUtils.camelToUnderscore(field.getName()));
                    builder.append("=");
                    builder.append(SQLUtils.nameToPlaceholder(field.getName(), field.getType()));
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return builder.toString();
    }
}
