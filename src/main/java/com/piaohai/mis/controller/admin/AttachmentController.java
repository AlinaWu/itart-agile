package com.piaohai.mis.controller.admin;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.piaohai.mis.common.entity.Constant;
import com.piaohai.mis.common.entity.Message;
import com.piaohai.mis.entity.AEntity;
import com.piaohai.mis.model.AttaRelation;
import com.piaohai.mis.model.Attachment;
import com.piaohai.mis.model.Document;
import com.piaohai.mis.service.AttaRelationService;
import com.piaohai.mis.service.AttachmentService;
import com.piaohai.mis.service.DocumentService;

@Controller
@RequestMapping(value = "/admin/attach")
public class AttachmentController {
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private AttaRelationService attaRelationService;
	@Autowired
	private DocumentService  documentService;
    /*@RequestMapping(value = "/upload")
    public @ResponseBody Document upload(@RequestParam(value = "upload", required = false) MultipartFile file, HttpServletRequest request, ModelMap model) {
    	String attachDocId=request.getParameter("docId");
    	
        String fileName = file.getOriginalFilename();
        String _fileName=addSuffix(fileName);
        File targetFile = new File(Constant.path+"/"+Constant.uploadFolderName,_fileName );
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        //保存
        try {
            file.transferTo(targetFile);
            Attachment record=new Attachment();
            record.setAttaLocation(_fileName);
            record.setAttaName(fileName);
            record.setAttaSize(file.getSize());
            record.setAttaType(getSuffix(fileName));
            attachmentService.insert(record);
            
            AttaRelation ar=new AttaRelation();
            ar.setAtreAttaId(record.getAttaId());
            ar.setAtreRelationId(attachDocId);
            attaRelationService.insert(ar);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return documentService.getDocDetail(attachDocId);
    }*/
	/**
	 * 附件上传
	 * @param file
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/attaUpload")
    public @ResponseBody Document attaUpload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, ModelMap model) {
    	String attachDocId=request.getParameter("docId");
    	
        String fileName = file.getOriginalFilename();
        String _fileName=addSuffix(fileName);
        File targetFile = new File(Constant.path+"/"+Constant.uploadFolderName,_fileName );
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        //保存
        try {
            file.transferTo(targetFile);
            Attachment record=new Attachment();
            record.setAttaLocation(_fileName);
            record.setAttaName(fileName);
            record.setAttaSize(file.getSize());
            record.setAttaType(getSuffix(fileName));
            attachmentService.insert(record);
            
            AttaRelation ar=new AttaRelation();
            ar.setAtreAttaId(record.getAttaId());
            ar.setAtreRelationId(attachDocId);
            attaRelationService.insert(ar);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Document doc= documentService.getDocDetail(attachDocId);
        doc.setDocContent("");
        return doc;
    }
	@RequestMapping(value = "/upload")
    public void upload(@RequestParam(value = "upload", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
    	String attachDocId=request.getParameter("docId");
    	
        String fileName = file.getOriginalFilename();
        String _fileName=addSuffix(fileName);
        File targetFile = new File(Constant.path+"/"+Constant.uploadFolderName,_fileName );
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        //保存
        try {
            file.transferTo(targetFile);
            Attachment record=new Attachment();
            record.setAttaLocation(_fileName);
            record.setAttaName(fileName);
            record.setAttaSize(file.getSize());
            record.setAttaType(getSuffix(fileName));
            attachmentService.insert(record);
            
            AttaRelation ar=new AttaRelation();
            ar.setAtreAttaId(record.getAttaId());
            ar.setAtreRelationId(attachDocId);
            attaRelationService.insert(ar);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String callback = request.getParameter("CKEditorFuncNum");//获取回调JS的函数Num  
        
        try {
			response.getWriter().print("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction("+callback+",'/mis/upload/"+_fileName+"')</script>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    @RequestMapping(value = "/delete")
    public @ResponseBody Message delete(String attaId) {
    	//删除附件
    	attachmentService.deleteByPrimaryKey(attaId);
        return new Message();
    }
    /**
     * 给文件名增加后缀
     * @param fileName
     * @return
     */
    public String addSuffix(String fileName){
    	String _fileName=fileName;
    	int dotIndex=fileName.lastIndexOf(".");
    	if(-1!=dotIndex){
    		Date date=new Date();
    		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd_hhmmss");
    		_fileName=fileName.substring(0,dotIndex)+"_"+sdf.format(date)+fileName.substring(dotIndex);
    	}
		return _fileName;
    }
    public String getSuffix(String fileName){
    	int dotIndex=fileName.lastIndexOf(".");
    	if(-1!=dotIndex){
    		return fileName.substring(dotIndex+1);
    	}
		return "";
    }
    /**
	 * 获取记录
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/browse",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView browse(HttpServletRequest request){
		//制定视图，也就是list.jsp
		ModelAndView mav=new ModelAndView("/admin/imageBrowse");
		File file=new File(Constant.path+"/"+Constant.uploadFolderName);
		File [] children=file.listFiles();
		List<AEntity> images=new ArrayList<AEntity>();
		for (File child : children) {
			AEntity image=new AEntity();
			image.setHref(Constant.uploadFolderName+"/"+child.getName());
			image.setTitle(image.getTitle());
			images.add(image);
		}
		mav.addObject("images",images);
		String callback = request.getParameter("CKEditorFuncNum");  
		mav.addObject("callback",callback);
		return mav;
	}
   
}