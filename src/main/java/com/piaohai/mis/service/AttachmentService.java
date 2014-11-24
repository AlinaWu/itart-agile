package com.piaohai.mis.service;

import com.piaohai.mis.common.entity.Message;
import com.piaohai.mis.model.Attachment;

public interface AttachmentService {
	public Attachment insert(Attachment record);
	public Attachment update(Attachment record);
	public Attachment selectByPrimaryKey(String attaId);
	public Message deleteByPrimaryKey(String attaId);
}
