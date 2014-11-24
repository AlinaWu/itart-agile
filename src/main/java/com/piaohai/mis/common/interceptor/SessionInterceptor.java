package com.piaohai.mis.common.interceptor;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.piaohai.mis.common.entity.Constant;
import com.piaohai.mis.entity.UserDetail;

public class SessionInterceptor extends HandlerInterceptorAdapter {


	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		 UserDetail user=(UserDetail) request.getSession().getAttribute(Constant.SESSION_USER_ID);
         if(user==null){
//        	 	response.getWriter().write("loginTimeout");
//             response.sendRedirect("/mis/admin/login.jsp");
//             return false;
         }
         return true;
	}
	
/*	private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 多次反向代理后会有多个IP值，第一个为真实IP。
       if (EmptyUtil.isNotEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
            if(ip.indexOf(",") > 0){
                return ip.substring(0,ip.indexOf(","));
            }else {
                return ip;
            }
        }
        return ip;
    } */
	   public String getMACAddress(String ip){
	         String str = "";
	         String macAddress = "";
	         try {
	             Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
	             InputStreamReader ir = new InputStreamReader(p.getInputStream());
	             LineNumberReader input = new LineNumberReader(ir);
	             for (int i = 1; i < 100; i++) {
	                 str = input.readLine();
	                 if (str != null) {
	                     if (str.indexOf("MAC Address") > 1) {
	                         macAddress = str.substring(str.indexOf("MAC Address") + 14, str.length());
	                         break;
	                     }
	                 }
	             }
	         } catch (IOException e) {
	             e.printStackTrace(System.out);
	         }
	         return macAddress;
	     }


 }
