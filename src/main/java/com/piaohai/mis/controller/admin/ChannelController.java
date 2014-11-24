package com.piaohai.mis.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.piaohai.mis.common.entity.Message;
import com.piaohai.mis.entity.ChannelNode;
import com.piaohai.mis.model.Channel;
import com.piaohai.mis.service.ChannelService;

@Controller
@RequestMapping("/admin/channel")
public class ChannelController {
	@Autowired
	ChannelService channelService;
	/**
	 * 树形加载
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/tree",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<ChannelNode> list(HttpServletRequest request){
		String parentId=request.getParameter("id");//父频道ID
		if(null==parentId){
			return channelService.selectRoot();
		}else{
			return channelService.selectByParentId(parentId);
		}
	}
	/**
	 * 保存
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/save",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Channel save(Channel channel){
		if(null==channel.getChanId()||"".equals(channel.getChanId())){
			channelService.insert(channel);
		}else{
			channelService.update(channel);
		}
		return channel;
	}
	/**
	 * 获取记录
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/get",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView get(String chanId){
		//制定视图，也就是list.jsp
		ModelAndView mav=new ModelAndView("/admin/channelEdit");
		mav.addObject("data",channelService.selectByPrimaryKey(chanId));
		return mav;
	}
	/**
	 * 删除记录
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/delete",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Message remove(String chanId,String chanParentId){
		return channelService.deleteByPrimaryKey(chanId,chanParentId);
	}
	/**
	 * 新增记录
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/add",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView add(String chanId){
		Channel newChannel=new Channel();
		newChannel.setChanParentId(chanId);
		//制定视图，也就是list.jsp
		ModelAndView mav=new ModelAndView("/admin/channelEdit");
		mav.addObject("data",newChannel);
		return mav;
	}
}
