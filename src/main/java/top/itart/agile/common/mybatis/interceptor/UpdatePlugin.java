package top.itart.agile.common.mybatis.interceptor;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import top.itart.agile.common.annotation.generator.StrategyFactory;
import top.itart.agile.common.util.BeanUtils;

/**
 * update plugin
 * 
 * @author piaohai
 */
@Intercepts({@Signature(type = Executor.class,
        method = "update",
        args = {MappedStatement.class, Object.class})})
public class UpdatePlugin implements Interceptor {
    // xml数据库操作类型-新增
    public final static String STATEMENT_TYPE_INSERT = "INSERT";
    // xml数据库操作类型-更新
    public final static String STATEMENT_TYPE_UPDATE = "UPDATE";

    public Object intercept(Invocation invocation) throws Throwable {
        Object parameter = invocation.getArgs()[1];
        if (null == parameter) {
            return invocation.proceed();
        }

        MappedStatement ms = (MappedStatement) invocation.getArgs()[0];

        // if is insert
        if (STATEMENT_TYPE_INSERT.equals(ms.getSqlCommandType().name())) {
            beforeInsert(parameter);
            // if is update data
        } else if (STATEMENT_TYPE_UPDATE.equals(ms.getSqlCommandType().name())) {
            beforeUpdate(parameter);
        }
        
        Object result = invocation.proceed();
        
        if("0".equals(result.toString())){
            throw new RuntimeException("No data update, the data can be out of date");
        }
        return result;
    }

    private void beforeInsert(Object parameter) throws IllegalAccessException, InvocationTargetException {
        // set updated and created time
        BeanUtils.setProperty(parameter, "updatedWhen", new Date());
        BeanUtils.setProperty(parameter, "createdWhen", new Date());

        // set version
        BeanUtils.setProperty(parameter, "version", 1);
        StrategyFactory.setId(parameter);
    }

    private void beforeUpdate(Object parameter) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        // set update time
        BeanUtils.setProperty(parameter, "updatedWhen", new Date());
        Object version = BeanUtils.getProperty(parameter, "version");
        if (null == version) {
            throw new RuntimeException("model = " + parameter.getClass() + "[ id = " + BeanUtils.getIdValue(parameter) + "]"
                    + " has no version value");
        }
        BeanUtils.setProperty(parameter, "version", Integer.parseInt(version.toString()) + 1);
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {

    }

}