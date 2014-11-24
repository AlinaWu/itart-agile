package com.piaohai.mis.common.tag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import com.piaohai.mis.common.entity.Constant;
import com.piaohai.mis.common.page.Page;
import com.piaohai.mis.common.util.EmptyUtil;
import com.piaohai.mis.common.util.SpringContextUtil;
import com.piaohai.mis.service.DocumentService;
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
public class ChannelTag extends TagSupport {
	private static final long serialVersionUID = -1781141754933250727L;
	private String name;
	private String format="yyyy-MM-dd";//格式化
	
	/**如果是循环体时 start**/
	private Iterator<?> iter;
	//当前变量
	private Object varModel;
	/**如果是循环体时 end**/
	private String item;
	public Object getVarModel() {
		return varModel;
	}
	//存放数据，供子标签调用
	private HashMap<String, Collection<?>> data=new HashMap<String, Collection<?>>();
	public HashMap<String, Collection<?>> getData() {
		return data;
	}
	public void addData(String name, Collection<?> value) {
		data.put(name, value);
	}
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
			docModel=sc.getAttribute(Constant.CHAN_MODEL);
		}else{
			docModel=chanTag.getVarModel();
		}
		Object _result=getValue(docModel);//获取对象中的值
		return execute(_result);
		
	}
	/**
	 * Description :获取值
	 * 1、如果有配置Item,且item值是对象时，返回item对象中的name属性值
	 * 2、如果没配置Item，直接从模型对象中获取name属性值
	 * @param docModel
	 * @return
	 * @return
	 * @Author: piaohai
	 * @Create Date: 2013-11-13
	 */
	private final static String DOCS="docs";//获取文章列表
	private Object getValue(Object docModel){
		Object _result=null;
		try {
			Object srcObj=docModel;
			if(EmptyUtil.isNotEmpty(item)){//如果Item有值，则取item中的name属性
				srcObj=PropertyUtils.getProperty(docModel, item);
			}
			if(DOCS.equals(name)){//如果属性值为docs
				DocumentService ds=SpringContextUtil.getBean(DocumentService.class);
				Page page = (Page) pageContext.getRequest().getAttribute("page");
				String channelId;
				channelId = BeanUtils.getProperty(docModel, "chanId");
				_result= ds.docListPageByChannel(channelId, page);
			}else{
				_result=PropertyUtils.getProperty(srcObj, name);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("取值异常");
		}
		return _result;
	}
	public void setItem(String item) {
		this.item = item;
	}
	/**
	 * Description : 处理数据
	 * @param value
	 * @return
	 * @return
	 * @Author: piaohai
	 * @Create Date: 2013-11-12
	 */
	private int execute(Object value){
		//如果数据位空，忽略标签内容
		if(EmptyUtil.isEmpty(value)){
			return SKIP_BODY;
		}
		if(value instanceof String||value instanceof Integer){
			return write(value.toString());
		}else if(value instanceof Date){
			return write(format((Date)value));
		}else if(value instanceof Collection<?>){//如果是集合类时
			return executeCollection((Collection<?>) value);
		}
		return EVAL_BODY_INCLUDE;
	}
	/**
	 * Description : 处理集合
	 * @param value
	 * @return
	 * @return
	 * @throws IOException 
	 * @throws JspException 
	 * @Author: piaohai
	 * @Create Date: 2013-11-12
	 */
	private int executeCollection( Collection<?> value){
		iter= value.iterator(); 
		if(value.isEmpty()||!iter.hasNext()){
			return SKIP_BODY;
		}
		varModel=iter.next();
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doAfterBody() throws JspException {
		if(iter!=null&&iter.hasNext()){//如果还有元素时 
			varModel=iter.next();
			return EVAL_BODY_AGAIN;
		}else{
			varModel=null;
			return SKIP_BODY;
		}
	}
	/**
	 * Description :格式化数据
	 * @param value
	 * @return
	 * @return
	 * @Author: piaohai
	 * @Create Date: 2013-11-12
	 */
	private String format(Date value){
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.format((Date)value);
	}
	//输出到页面
	private int write(Object value){
		try {
			pageContext.getOut().write(value.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}
	

}
