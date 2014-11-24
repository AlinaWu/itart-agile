package com.piaohai.mis.service;

import com.piaohai.mis.common.entity.Message;
import com.piaohai.mis.model.AttaRelation;

public interface AttaRelationService {
	public AttaRelation insert(AttaRelation record);
	public AttaRelation update(AttaRelation record);
	public AttaRelation selectByPrimaryKey(String atreId);
	public Message deleteByPrimaryKey(String atreId);
	/**
	 *	根据附件id删除 
	 * @param attaId
	 * @return
	 */
	public int deleteByAttachId(String attaId);
}
