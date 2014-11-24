package com.piaohai.mis.common.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
/**
 * Description: 频道标签
 * @author: piaohai
 * Create Date: 2013-10-21
 * <pre>
 * 修改记录:
 * 版本				修改人		修改内容 
 * 2013-10-21.1		piaohai		create					
 * </pre>
 */
public class EachTag extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		getJspBody().invoke(null);
		super.doTag();
	}
	

}
