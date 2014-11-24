package com.piaohai.mis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.piaohai.mis.common.entity.ChangePassword;
import com.piaohai.mis.common.entity.Message;
import com.piaohai.mis.common.util.MD5;
import com.piaohai.mis.dao.UserInfoMapper;
import com.piaohai.mis.model.UserInfo;
import com.piaohai.mis.service.UserInfoService;
@Service
public class UserInfoServiceImpl implements UserInfoService{
	@Autowired
	private UserInfoMapper userinfo;
	public void getUserInfo() {
		UserInfo record=new UserInfo();
		record.setUserId("test");
		record.setUserName("");
		userinfo.updateByPrimaryKeySelective(record);
		userinfo.selectByPrimaryKey("test");
	}
	public Message checkUser(String userName, String password) {
		Message result=new Message();
		UserInfo userInfo=userinfo.selectByUserName(userName);
		//用戶不存在
		if(null==userInfo){
			result.setSuccess(false);
			result.setText("用戶不存在!");
			return result;
		}
		if(!MD5.encode(password).equals(userInfo.getUserPassword())){//如果密码正确
			result.setSuccess(false);
			result.setText("密码错误!");
			return result;
		}
		//校驗通過
		result.setData(userInfo);
		return result;
	}
	public Message changePassword(ChangePassword entity) {
		Message result=new Message();
		UserInfo ui=userinfo.selectByUserName(entity.getUserName());
		//校驗新密碼和確認密碼是否一致
		if(!entity.getConfirmPassword().equals(entity.getNewPassword())){
			result.setSuccess(false);
			result.setText("新密码和确认密码不一致!");
			return result;
		}
		//用戶不存在
		if(null==ui){
			result.setSuccess(false);
			result.setText("用戶不存在!");
			return result;
		}
		String newPassword=MD5.encode(entity.getNewPassword());
		String orgPassword=MD5.encode(entity.getOrgPassword());
		if(!orgPassword.equals(ui.getUserPassword())){//如果密码正确
			result.setSuccess(false);
			result.setText("原始密码輸入错误!");
			return result;
		}
		ui.setUserPassword(newPassword);
		userinfo.updateByPrimaryKey(ui);
		result.setData(entity);
		return result;
	}

}
