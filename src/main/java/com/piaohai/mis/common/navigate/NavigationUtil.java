package com.piaohai.mis.common.navigate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.piaohai.mis.common.util.SpringContextUtil;
import com.piaohai.mis.dao.ChannelMapper;
import com.piaohai.mis.model.Channel;
/**
 * Description: 导航工具类
 * @author: piaohai
 * Create Date: 2013-11-11
 * <pre>
 * 修改记录:
 * 版本				修改人		修改内容 
 * 2013-11-11.1		piaohai		create					
 * </pre>
 */
public class NavigationUtil {
	protected static Logger log = Logger.getLogger(NavigationUtil.class);   
	private static final HashMap<String,ChannelModel> navigates=new HashMap<String, ChannelModel>();
	static{
		//初始化navigates
		init();
	}
	/**
	 * Description :初始化数据
	 * 
	 * @return
	 * @Author: piaohai
	 * @Create Date: 2013-11-11
	 */
	private static void init(){
		long startTime=System.currentTimeMillis();
		List<ChannelModel> allChannelModel=new ArrayList<ChannelModel>();
		//获取所有频道，并且转化为ChannelModel对象
		ChannelMapper cm=SpringContextUtil.getBean(ChannelMapper.class);
		List<Channel> allChannels=cm.getAll();
		for (Channel channel : allChannels) {
			ChannelModel channelModel=new ChannelModel();
			try {
				BeanUtils.copyProperties(channelModel, channel);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("导航初始化失败");
			}
			allChannelModel.add(channelModel);
		}
		//建立频道父子关联
		for (ChannelModel parent : allChannelModel) {
			for (ChannelModel child : allChannelModel) {
				if(parent.getChanId().equals(child.getChanParentId())){
					child.setParent(parent);
				}
			}
		}
		for (ChannelModel channelModel : allChannelModel) {
			navigates.put(channelModel.getChanId(), channelModel);
		}
		log.debug("导航初始化耗时:"+(System.currentTimeMillis()-startTime));
	}
	/**
	 * Description :获取频道
	 * @param id
	 * @return
	 * @return
	 * @Author: piaohai
	 * @Create Date: 2013-11-11
	 */
	public  static  ChannelModel getChannel(String chanId){
		return navigates.get(chanId);
	}
	/**
	 * Description :更新导航对象
	 * 
	 * @return
	 * @Author: piaohai
	 * @Create Date: 2013-11-11
	 */
	public  static  void updateNavigate(){
		synchronized(navigates){
			navigates.clear();
			init();
		}
	}
}
