package com.piaohai.mis.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.piaohai.mis.common.entity.Constant;
import com.piaohai.mis.common.entity.Message;
import com.piaohai.mis.dao.AttachmentMapper;
import com.piaohai.mis.model.Attachment;
import com.piaohai.mis.service.AttaRelationService;
import com.piaohai.mis.service.AttachmentService;
@Service
public class AttachmentServiceImpl implements AttachmentService{
	
	@Autowired
	AttachmentMapper attachmentMapper;
	@Autowired
	AttaRelationService attaRelationService;
	public Attachment insert(Attachment record) {
		attachmentMapper.insert(record);
		return record;
	}

	public Attachment update(Attachment record) {
		// TODO Auto-generated method stub
		return null;
	}

	public Attachment selectByPrimaryKey(String attaId) {
		return attachmentMapper.selectByPrimaryKey(attaId);
	}

	public Message deleteByPrimaryKey(String attaId) {
		//获取文件信息
    	Attachment attachment=selectByPrimaryKey(attaId);
    	//删除关联
    	attaRelationService.deleteByAttachId(attaId);
    	//删除附件
    	attachmentMapper.deleteByPrimaryKey(attaId);
    	//删除文件
    	File file=new File(Constant.path+"/"+Constant.uploadFolderName+"/"+attachment.getAttaLocation());
    	file.delete();
        return new Message();
	}
	

}
