package com.piaohai.mis.service;

import com.piaohai.mis.common.entity.ChangePassword;
import com.piaohai.mis.common.entity.Message;
/**
 * 用户管理类
 * @author piaohai
 *
 */
public interface UserInfoService {
	public void getUserInfo();
	public Message checkUser(String userName,String password);
	public Message changePassword(ChangePassword entity);
}
