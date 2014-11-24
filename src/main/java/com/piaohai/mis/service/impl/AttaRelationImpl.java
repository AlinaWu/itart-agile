package com.piaohai.mis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.piaohai.mis.common.entity.Message;
import com.piaohai.mis.dao.AttaRelationMapper;
import com.piaohai.mis.model.AttaRelation;
import com.piaohai.mis.service.AttaRelationService;
@Service
public class AttaRelationImpl implements AttaRelationService{
	
	@Autowired
	AttaRelationMapper attaRelationMapper;
	public AttaRelation insert(AttaRelation record) {
		attaRelationMapper.insert(record);
		return record;
	}

	public AttaRelation update(AttaRelation record) {
		// TODO Auto-generated method stub
		return null;
	}

	public AttaRelation selectByPrimaryKey(String atreId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Message deleteByPrimaryKey(String atreId) {
		// TODO Auto-generated method stub
		return null;
	}

	public int deleteByAttachId(String attaId) {
		return attaRelationMapper.deleteByAttachId(attaId);
	}
	

}
