package com.piaohai.mis.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.piaohai.mis.common.entity.Message;
import com.piaohai.mis.dao.ChannelMapper;
import com.piaohai.mis.entity.ChannelNode;
import com.piaohai.mis.model.Channel;
import com.piaohai.mis.service.ChannelService;
@Service
public class ChannelServiceImpl implements ChannelService{
	@Autowired
	ChannelMapper channelMapper;
	public Channel insert(Channel record) {
		channelMapper.insert(record);
		Channel parentChannel=channelMapper.selectByPrimaryKey(record.getChanParentId());
		if("1".equals(parentChannel.getChanType())){//如果父节点是叶子节点时，更新为枝干
			parentChannel.setChanType("0");
			channelMapper.updateByPrimaryKey(parentChannel);
		}
		return record;
	}
	public Channel update(Channel record) {
		channelMapper.updateByPrimaryKey(record);
		return record;
	}
	public Channel selectByPrimaryKey(String chanId){
		return channelMapper.selectByPrimaryKey(chanId);
	}
	public List<ChannelNode>  selectByParentId(String chanParentId){
		List<Channel> channels=channelMapper.selectByParentId(chanParentId);
		return changelToChannelNodes(channels);
	}
	public List<ChannelNode>  selectRoot(){
		List<Channel> channels= channelMapper.selectRoot();
		return changelToChannelNodes(channels);
	}
	public List<ChannelNode> changelToChannelNodes(List<Channel> channel){
		List<ChannelNode> nodes=new ArrayList<ChannelNode>();
		for (Channel channelNode : channel) {
			nodes.add(changelToChannelNode(channelNode));
		}
		return nodes;
	}
	public ChannelNode changelToChannelNode(Channel channel){
		ChannelNode node=new ChannelNode();
		node.setId(channel.getChanId());
		node.setText(channel.getChanName());
		node.setState("1".equals(channel.getChanType())?"open":"closed");
		return node;
	}
	public Message deleteByPrimaryKey(String chanId,String chanParentId){
		int count=channelMapper.deleteByPrimaryKey(chanId);
		List<Channel> channels=channelMapper.selectByParentId(chanParentId);
		Message msg=new Message();
		if(channels.size()==0){
			Channel parentChannel=new Channel();
			parentChannel.setChanType("1");
			parentChannel.setChanId(chanParentId);
			channelMapper.updateByPrimaryKeySelective(parentChannel);
			msg.setData("true");
		}
		if(count==0){
			msg.setSuccess(false);
			msg.setText("删除失败!");
		}else{
			msg.setText("删除成功!");
		}
		return msg;
	}

}
