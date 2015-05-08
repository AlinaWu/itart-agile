package top.itart.agile.common.interceptor;

import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
/**
 * Description:   登陆拦截
 * @author: Kyle
 * Create Date: 2012-5-11
 * <pre>
 * 修改记录:
 * 修改后版本			修改人		修改日期			修改内容 
 * 2012-5-11.1		Kyle		2012-5-11		create					
 * </pre>
 */
public class AuthorityInterceptor extends MethodFilterInterceptor{
	
	private static final long serialVersionUID = 2045300992091167025L;
	static Logger logger = Logger.getLogger(AuthorityInterceptor.class);

	/*@Autowired
	private VisitManager visitManager;*/
	
	@Override
	protected String doIntercept(ActionInvocation actioninvocation) throws Exception {
		String visitId = UUID.randomUUID().toString().replace("-", "");
		MDC.put("VISIT_ID", visitId);
		MDC.put("USERNAME", "hymanz");
		String invoke = null;
		try{
			invoke = actioninvocation.invoke();
		}finally{
			System.out.print(6);
		}
		
		return invoke;
	}
}
