package com.piaohai.mis.service;

import java.util.List;

import com.piaohai.mis.common.entity.Message;
import com.piaohai.mis.common.page.Page;
import com.piaohai.mis.model.Document;

public interface DocumentService {
	public Document insert(Document record);
	public Document update(Document record);
	public Document selectByPrimaryKey(String docId);
	public Message deleteByPrimaryKey(String docId);
	public List<Document> selectByChannelId(String channelId);

    public Document getDocDetail(String docId);
    public List<Document> docListPage(String channelId,Page page);
    public List<Document> docListPageByChannel(String channelId,Page page);
    
}
