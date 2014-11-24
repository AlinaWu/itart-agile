package com.piaohai.mis.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.piaohai.mis.common.entity.Constant;
import com.piaohai.mis.common.navigate.ChannelModel;
import com.piaohai.mis.common.navigate.NavigationUtil;
import com.piaohai.mis.common.page.Page;
import com.piaohai.mis.common.util.EmptyUtil;
import com.piaohai.mis.entity.AEntity;
import com.piaohai.mis.entity.ChannelNode;
import com.piaohai.mis.model.Attachment;
import com.piaohai.mis.model.Channel;
import com.piaohai.mis.model.Document;
import com.piaohai.mis.service.ChannelService;
import com.piaohai.mis.service.DocumentService;

@Controller
@RequestMapping("/public")
public class PublicController {
	@Autowired
	ChannelService channelService;
	@Autowired
	DocumentService documentService;

	/**
	 * 获取记录
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/index",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView index(){
		//制定视图，也就是list.jsp
		ModelAndView mav=new ModelAndView("/public/_index");
		//公司简介
		mav.addObject("summary",documentService.selectByPrimaryKey("A582B2D6256E4EC298BE238A40573376").getDocContent());
		//公司图片
		List<Attachment> atts=documentService.getDocDetail("A582B2D6256E4EC298BE238A40573376").getAttachments();
		for (Attachment attachment : atts) {
			attachment.setAttaLocation(Constant.uploadFolderName+"/"+attachment.getAttaLocation());
		}
		mav.addObject("companyImages",atts);
		//产品
		Page page=new Page();
    	page.setCurrentPage(30);
    	 List<Document> productDocs=documentService.docListPageByChannel( "6893D5D827F246889828960975C822CF",page);
		List<Attachment> productImages=new ArrayList<Attachment>();
		for (Document productDoc : productDocs) {
			List<Attachment> proAtts=productDoc.getAttachments();
			for (Attachment proAtt : proAtts) {
				proAtt.setAttaLocation(Constant.uploadFolderName+"/"+proAtt.getAttaLocation());
				productImages.add(proAtt);
			}
		}
		
		mav.addObject("productImages",productImages);
		
		return mav;
	}
	@RequestMapping(value="/aboutUs",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView aboutUs(String chanId,String docId){
		if(EmptyUtil.isEmpty(chanId)){
			chanId="4695AEB0197C4099A98F9CD16A61D242";
		}
		if(EmptyUtil.isEmpty(docId)){
			docId="A582B2D6256E4EC298BE238A40573376";
		}
		//制定视图，也就是list.jsp
		ModelAndView mav=new ModelAndView("/public/_list");
		List<AEntity> docTitles=new ArrayList<AEntity>();
		
		List<Document> docs=documentService.selectByChannelId(chanId);
		for (Document document : docs) {
			AEntity docTitle=new AEntity();
			docTitle.setTitle(document.getDocTitle());
			docTitle.setHref("public/aboutUs.do?chanId="+chanId+"&docId="+document.getDocId());
			docTitles.add(docTitle);
		}
		//公司简介
		mav.addObject("docList",docTitles);
		mav.addObject("title",channelService.selectByPrimaryKey(chanId).getChanName());
		mav.addObject("currDoc",documentService.selectByPrimaryKey(docId));
		//公司图片
		List<Attachment> atts=documentService.getDocDetail("A582B2D6256E4EC298BE238A40573376").getAttachments();
		for (Attachment attachment : atts) {
			attachment.setAttaLocation(Constant.uploadFolderName+"/"+attachment.getAttaLocation());
		}
		mav.addObject("companyImages",atts);
		//导航
		List<AEntity> navigation=new ArrayList<AEntity>();
		AEntity main=new AEntity();
		main.setTitle("首页");
		main.setHref("public/index.do");
		navigation.add(main);
		AEntity aboutUs=new AEntity();
		aboutUs.setTitle("关于我们");
		aboutUs.setHref("public/aboutUs.do?chanId=&docId=");
		navigation.add(aboutUs);
		mav.addObject("navigate",NavigationUtil.getChannel(chanId));
		return mav;
	}
/*	@RequestMapping(value="/aboutUs",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView aboutUs(String chanId,String docId){
		if(EmptyUtil.isEmpty(chanId)){
			chanId="4695AEB0197C4099A98F9CD16A61D242";
		}
		if(EmptyUtil.isEmpty(docId)){
			docId="A582B2D6256E4EC298BE238A40573376";
		}
		//制定视图，也就是list.jsp
		ModelAndView mav=new ModelAndView("/public/_list");
		List<AEntity> docTitles=new ArrayList<AEntity>();
		
		List<Document> docs=documentService.selectByChannelId(chanId);
		for (Document document : docs) {
			AEntity docTitle=new AEntity();
			docTitle.setTitle(document.getDocTitle());
			docTitle.setHref("public/aboutUs.do?chanId="+chanId+"&docId="+document.getDocId());
			docTitles.add(docTitle);
		}
		//公司简介
		mav.addObject("docList",docTitles);
		mav.addObject("title",channelService.selectByPrimaryKey(chanId).getChanName());
		mav.addObject("currDoc",documentService.selectByPrimaryKey(docId));
		
		//导航
		List<AEntity> navigation=new ArrayList<AEntity>();
		AEntity main=new AEntity();
		main.setTitle("首页");
		main.setHref("public/index.do");
		navigation.add(main);
		AEntity aboutUs=new AEntity();
		aboutUs.setTitle("关于我们");
		aboutUs.setHref("public/aboutUs.do?chanId=&docId=");
		navigation.add(aboutUs);
		mav.addObject("navigation",navigation);
		return mav;
	}
	*/
	@RequestMapping(value="/news",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView news(HttpServletRequest request){
		String chanId=request.getParameter("chanId");
		Page pageModel=new Page();
		String page=request.getParameter("page"); //页号，从1计起。
		String rows=request.getParameter("rows");// 每页记录大小。
		if(EmptyUtil.isNotEmpty(page)){
			pageModel.setCurrentPage(Integer.parseInt(page));
		}
		if(EmptyUtil.isNotEmpty(rows)){
			pageModel.setShowCount(Integer.parseInt(rows));
		}
		//制定视图，也就是list.jsp
		ModelAndView mav=new ModelAndView("/public/channelList");
		//获取列表
		List<Document> docs=documentService.docListPage(chanId, pageModel);
		List<AEntity> aDocs=new ArrayList<AEntity>();
		for (Document document : docs) {
			AEntity a=new AEntity();
			a.setTitle(document.getDocTitle());
			a.setHref("public/newsDetail.do?docId="+document.getDocId());
			a.setData(document.getDateCreated());
			aDocs.add(a);
		}
		mav.addObject("docList",aDocs);
		
		Channel currChannel=channelService.selectByPrimaryKey(chanId);
		Channel pChannel=channelService.selectByPrimaryKey(currChannel.getChanParentId());//父channel
		//左边分类
		List<ChannelNode> channels=channelService.selectByParentId(currChannel.getChanParentId());
		List<AEntity> aChannels=new ArrayList<AEntity>();
		for (ChannelNode channelNode : channels) {
			AEntity aChannel=new AEntity();
			aChannel.setTitle(channelNode.getText());
			aChannel.setHref("public/news.do?chanId="+channelNode.getId());
			aChannels.add(aChannel);
		}
		mav.addObject("channList",aChannels);
		//导航
		List<AEntity> navigation=new ArrayList<AEntity>();
		AEntity main=new AEntity();
		main.setTitle("首页");
		main.setHref("public/index.do");
		navigation.add(main);
		AEntity aCurrChannel=new AEntity();
		aCurrChannel.setTitle(currChannel.getChanName());
		aCurrChannel.setHref("public/news.do?chanId="+currChannel.getChanId());
		navigation.add(aCurrChannel);
		mav.addObject("navigation",navigation);
		
		mav.addObject("title",pChannel.getChanName());
		mav.addObject("page",pageModel);
		
		mav.addObject("url","public/news.do?chanId="+currChannel.getChanId());
		
		return mav;
	}
	/**
	 * 新闻明细
	 * @param chanId
	 * @return
	 */
	@RequestMapping(value="/newsDetail",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView newsDetail(String docId){
		//制定视图，也就是list.jsp
		ModelAndView mav=new ModelAndView("/public/docDetail");
		//获取列表
		
		
		return mav;
	}
	/**
	 * Description :获取明细界面
	 * @return
	 * @return
	 * @Author: piaohai
	 * @Create Date: 2013-11-12
	 */
	private String getDetailPage(Document docModel){
		String detailPage=docModel.getDocDetailPage();
		//如果文档没有单独配置明细页面，则去频道中配置的明细
		if(EmptyUtil.isEmpty(detailPage)){
			detailPage=NavigationUtil.getChannel(docModel.getDocChannel()).getChanDetailPage();
		}
		if(EmptyUtil.isEmpty(detailPage)){
			throw new RuntimeException("该文档没有配置对应明细页面!");
		}
		return detailPage;
	}
	/**
	 * Description :获取列表页面
	 * @return
	 * @return
	 * @Author: piaohai
	 * @Create Date: 2013-11-12
	 */
	private String getListPage(ChannelModel channelModel){
		String listPage=channelModel.getChanListPage();
		if(EmptyUtil.isEmpty(listPage)){
			throw new RuntimeException("该文档没有配置对应列表页面!");
		}
		return listPage;
	}
	/**
	 * Description :访问文档
	 * @param docId
	 * @return
	 * @return
	 * @Author: piaohai
	 * @Create Date: 2013-11-12
	 */
	@RequestMapping(value="/doc",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView doc(String docId){
		Document docModel=documentService.getDocDetail(docId);
		//对应明细视图
		ModelAndView mav=new ModelAndView(getDetailPage(docModel));
		mav.addObject(Constant.DOC_MODEL,docModel);
		return mav;
	}
	/**
	 * Description :访问频道
	 * @param docId
	 * @return
	 * @return
	 * @Author: piaohai
	 * @Create Date: 2013-11-12
	 */
	@RequestMapping(value="/chan",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView chan(String chanId){
		ChannelModel channelModel=NavigationUtil.getChannel(chanId);
		//对应明细视图
		ModelAndView mav=new ModelAndView(getListPage(channelModel));
		mav.addObject(Constant.CHAN_MODEL,channelModel);
		return mav;
	}
}
