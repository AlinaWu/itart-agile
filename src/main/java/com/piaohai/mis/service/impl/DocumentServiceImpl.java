package com.piaohai.mis.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.piaohai.mis.common.entity.Message;
import com.piaohai.mis.common.page.Page;
import com.piaohai.mis.dao.DocumentMapper;
import com.piaohai.mis.model.Attachment;
import com.piaohai.mis.model.Document;
import com.piaohai.mis.service.AttachmentService;
import com.piaohai.mis.service.DocumentService;
@Service
public class DocumentServiceImpl implements DocumentService{
	@Autowired
	DocumentMapper documentMapper;
	@Autowired
	AttachmentService attachmentService;
	public Document insert(Document record) {
		documentMapper.insert(record);
		return null;
	}

	public Document update(Document record) {
		documentMapper.updateByPrimaryKeyWithBLOBs(record);
		return record;
	}

	public Document selectByPrimaryKey(String docId) {
		return documentMapper.selectByPrimaryKey(docId);
	}

	public Message deleteByPrimaryKey(String docId) {
		Document doc=getDocDetail(docId);
		List<Attachment> attas=doc.getAttachments();
		//删除附件
		for (Attachment attachment : attas) {
			attachmentService.deleteByPrimaryKey(attachment.getAttaId());
		}
		//删除文档
		documentMapper.deleteByPrimaryKey(docId);
		return new Message();
	}

	public List<Document> selectByChannelId(String channelId) {
		return documentMapper.selectByChannelId(channelId);
	}

	public Document getDocDetail(String docId) {
		return documentMapper.getDocDetail(docId);
	}

	public List<Document> docListPage(String channelId, Page page) {
		return documentMapper.docListPage(page, channelId);
	}

	public List<Document> docListPageByChannel(String channelId, Page page) {
		return documentMapper.docListPageByChannel(page, channelId);
	}
	
}
