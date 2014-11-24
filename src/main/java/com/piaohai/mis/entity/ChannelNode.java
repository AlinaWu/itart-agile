package com.piaohai.mis.entity;
/**
 * 频道 node格式
 * example:
 * [{"id":"5","text":"Node 1.1","state":"closed"},{"id":"6","text":"Node 1.2","state":"open"}]
 * @author piaohai
 *
 */
public class ChannelNode {
	private String id;
	private String text;
	private String state;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
