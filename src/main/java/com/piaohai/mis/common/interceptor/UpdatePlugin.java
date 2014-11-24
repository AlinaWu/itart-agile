package com.piaohai.mis.common.interceptor;

import java.util.Date;
import java.util.Properties;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import com.piaohai.mis.common.entity.Constant;
/**
 * 更新拦截器
 * @author piaohai
 *
 */
@Intercepts({  @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class UpdatePlugin implements Interceptor {
	
	public Object intercept(Invocation invocation) throws Throwable {
		MappedStatement ms=(MappedStatement) invocation.getArgs()[0];
		//如果是新增语句时,设置创建时间
		if(Constant.STATEMENT_TYPE_INSERT.equals(ms.getSqlCommandType().name())){
			Object parameter=invocation.getArgs()[1];
			//设置创建时间为当前时间
			PropertyUtils.setProperty(parameter, "dateCreated", new Date());
		//如果是更新时，设置修改时间
		}else if(Constant.STATEMENT_TYPE_UPDATE.equals(ms.getSqlCommandType().name())){
			Object parameter=invocation.getArgs()[1];
			//设置创建时间为当前时间
			PropertyUtils.setProperty(parameter, "dateModified", new Date());
		}
		return invocation.proceed();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
		
	}

}