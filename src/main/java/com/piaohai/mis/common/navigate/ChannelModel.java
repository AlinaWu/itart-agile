package com.piaohai.mis.common.navigate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.piaohai.mis.model.Channel;
/**
 * Description: 频道模型
 * @author: piaohai
 * Create Date: 2013-11-11
 * <pre>
 * 修改记录:
 * 版本				修改人		修改内容 
 * 2013-11-11.1		piaohai		create					
 * </pre>
 */
public class ChannelModel extends Channel{
    //父频道
    private ChannelModel parent;
    //子频道
    private List<ChannelModel> children=new ArrayList<ChannelModel>();
	public ChannelModel getParent() {
		return parent;
	}
	public void setParent(ChannelModel parent) {
		this.parent = parent;
		if(null!=parent){
			parent.addChildren(this);
		}
	}
	/**
	 * Description :新增子频道
	 * @param child
	 * @return
	 * @Author: piaohai
	 * @Create Date: 2013-11-11
	 */
	private void addChildren(ChannelModel child) {
		children.add(child);
	}
	/**
	 * Description :删除子频道
	 * @param child
	 * @return
	 * @Author: piaohai
	 * @Create Date: 2013-11-11
	 */
	public void removeChildren(ChannelModel child) {
		child.setParent(null);
		children.remove(child);
	}
	public List<ChannelModel> getChildren() {
		return Collections.unmodifiableList(children);
	}
	/**
	 * Description :获取兄弟频道数据
	 * @return
	 * @return
	 * @Author: piaohai
	 * @Create Date: 2013-11-11
	 */
	public List<ChannelModel> getBrothers() {
		return Collections.unmodifiableList(getParent().getChildren());
	} 
}
