package top.itart.agile.common.mybatis.provider;

import java.lang.reflect.Field;

import top.itart.agile.common.annotation.Id;
import top.itart.agile.common.model.BaseModel;
import top.itart.agile.common.util.BeanUtils;
import top.itart.agile.common.util.EmptyUtils;
import top.itart.agile.common.util.SQLUtils;

public class CommonUpdateProvider {
    public static final String UPDATE = "update";
    
    public String update(BaseModel parameter) {
        return updateWithPlaceholder(parameter);
    }
    
    private String updateWithPlaceholder(BaseModel model) {
        String tableName = BeanUtils.getTableNameByAnnotation(model);

        if (EmptyUtils.isEmpty(tableName)) {
            throw new RuntimeException("Model = " + model.getClass() + " has no definition table annotation.");
        }

        StringBuilder builder = new StringBuilder();
        builder.append("update ").append(tableName).append(" set ");
        
        Field[] fields = BeanUtils.getFields(model);
       
        Field idField = null;
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            builder.append(SQLUtils.camelToUnderscore(field.getName())).append("=");
            builder.append("#{").append(SQLUtils.nameToPlaceholder(field.getName(), field.getType())).append("} ");
            if(null != field.getAnnotation(Id.class)){
                idField = field;
            }
            if(i != fields.length-1){
                builder.append(",");
            }
        }
        
        if(null == idField){
            throw new RuntimeException("Model = " + model.getClass() + " has no ID annotation.");
        }
       
        builder.append(" where ");
        builder.append(SQLUtils.camelToUnderscore(idField.getName())).append("= #{").append(SQLUtils.nameToPlaceholder(idField.getName(), idField.getType())).append("} ");
        builder.append(" and ").append("VERSION+1=#{").append("version, jdbcType = INTEGER").append("} ");

        return builder.toString();
    }
}
