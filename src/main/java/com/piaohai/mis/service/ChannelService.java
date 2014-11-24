package com.piaohai.mis.service;

import java.util.List;

import com.piaohai.mis.common.entity.Message;
import com.piaohai.mis.entity.ChannelNode;
import com.piaohai.mis.model.Channel;

public interface ChannelService {
	public Channel insert(Channel record);
	public Channel update(Channel record);
	public Channel selectByPrimaryKey(String chanId);
	public List<ChannelNode>  selectByParentId(String chanParentId);
	public List<ChannelNode>  selectRoot();
	public Message deleteByPrimaryKey(String chanId,String chanParentId);
}
