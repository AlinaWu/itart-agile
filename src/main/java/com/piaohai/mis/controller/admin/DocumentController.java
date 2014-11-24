package com.piaohai.mis.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.piaohai.mis.common.entity.Message;
import com.piaohai.mis.common.page.Page;
import com.piaohai.mis.common.util.EmptyUtil;
import com.piaohai.mis.model.Document;
import com.piaohai.mis.service.AttachmentService;
import com.piaohai.mis.service.DocumentService;

@Controller
@RequestMapping("/admin/document")
public class DocumentController {
	@Autowired
	DocumentService documentService;
	@Autowired
	AttachmentService attachmentService;
	/**
	 * 文章初列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/list",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Map<String, Object> list(HttpServletRequest request){
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
		
		List<Document> list=documentService.docListPage(chanId, pageModel);
		//包装结果
		//{total:15,rows:[{name:'test',age:12},{name:'test2',age:18}]}
		Map<String, Object> result=new HashMap<String, Object>();
		result.put("total", pageModel.getTotalResult());
		result.put("rows", list);
		return result;
	}
	@RequestMapping(value="/open",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView open(String chanId){
		//制定视图，也就是list.jsp
		ModelAndView mav=new ModelAndView("/admin/documentList");
		mav.addObject("docChannel",chanId);
		return mav;
	}
	/**
	 * 打开维护页面
	 * @param chanId
	 * @return
	 */
	@RequestMapping(value="/openEdit",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView openEdit(Document doc){
//		String docChannel,String chanId
		//制定视图，也就是list.jsp
		ModelAndView mav=new ModelAndView("/admin/documentEdit");
		mav.addObject("doc",doc);
		return mav;
	} 

	/**
	 * 获取维护数据
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/get",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Document get(String docId){
		return  documentService.getDocDetail(docId);
	}
	/**
	 * 删除记录
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/delete",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Message remove(String docId){
		return documentService.deleteByPrimaryKey(docId);
	}
	/**
	 * 保存
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/save",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Document save(Document doc){
		if(null== doc.getDocId()||"".equals( doc.getDocId())){
			documentService.insert( doc);
		}else{
			documentService.update( doc);
		}
		doc.setDocContent("");
		return doc ;
	}

}
