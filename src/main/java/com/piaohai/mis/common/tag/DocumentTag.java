package com.piaohai.mis.common.tag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.beanutils.PropertyUtils;

import com.piaohai.mis.common.entity.Constant;
import com.piaohai.mis.common.util.EmptyUtil;
/**
 * Description: 文档标签
 * @author: piaohai
 * Create Date: 2013-11-12
 * <pre>
 * 修改记录:
 * 版本				修改人		修改内容 
 * 2013-11-12.1		piaohai		create					
 * </pre>
 */
public class DocumentTag extends TagSupport {
	private static final long serialVersionUID = 371956270899170614L;
	private String name;
	private String format="yyyy-MM-dd";//格式化
	public void setFormat(String format) {
		this.format = format;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int doStartTag() throws JspException {
		
		ServletRequest sc = pageContext.getRequest();
		//如果父标签标示channel时
		ChannelTag chanTag=(ChannelTag)getParent();
		//获取文档对象
		Object docModel=null;
		if(null==chanTag){
			docModel=sc.getAttribute(Constant.DOC_MODEL);
		}else{
			docModel=chanTag.getVarModel();
		}
		
		String result="";
		try {
			Object _result=PropertyUtils.getProperty(docModel, name);
			result=format(_result);//格式化数据
		} catch (Exception e) {
			throw new RuntimeException(docModel+"中不存在属性："+name);
		}
		write(result);
		if(EmptyUtil.isEmpty(result)){
			return Tag.SKIP_BODY;
		}
		return Tag.EVAL_BODY_INCLUDE;
	}
	/**
	 * Description :格式化数据
	 * @param value
	 * @return
	 * @return
	 * @Author: piaohai
	 * @Create Date: 2013-11-12
	 */
	private String format(Object value){
		if(EmptyUtil.isEmpty(value)){
			return "";
		}
		//value不为空时
		String result = value.toString();
		if(value instanceof Date){
			SimpleDateFormat sdf=new SimpleDateFormat(format);
			result=sdf.format((Date)value);
		}
		return result;
	}
	//输出到页面
	private void write(Object value){
		try {
			this.pageContext.getOut().write(value.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
