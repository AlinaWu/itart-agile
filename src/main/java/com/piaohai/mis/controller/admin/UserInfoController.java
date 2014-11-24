package com.piaohai.mis.controller.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.piaohai.mis.common.entity.ChangePassword;
import com.piaohai.mis.common.entity.Constant;
import com.piaohai.mis.common.entity.Message;
import com.piaohai.mis.common.util.EmptyUtil;
import com.piaohai.mis.entity.UserDetail;
import com.piaohai.mis.service.UserInfoService;

@Controller
@RequestMapping("/admin/user")
public class UserInfoController {
	@Autowired
	private UserInfoService userInfoService;

	@RequestMapping("/changePassword")
	public @ResponseBody Message changePassword(ChangePassword changePassword) {
		Message result=new Message();
		if(EmptyUtil.isEmpty(changePassword.getConfirmPassword())||EmptyUtil.isEmpty(changePassword.getNewPassword())||EmptyUtil.isEmpty(changePassword.getOrgPassword())){
			result.setSuccess(false);
		}else{
			result=userInfoService.changePassword(changePassword);
		}
		return result;
	}
	/**
	 * 打開修改密碼頁面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/openChangePassword",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView openChangePassword(HttpSession session){
		UserDetail ud=(UserDetail) session.getAttribute(Constant.SESSION_USER_ID);
		//制定视图，也就是list.jsp
		ModelAndView mav=new ModelAndView("/admin/changePassword");
		mav.addObject("userName",ud.getUserName());
		return mav;
	}
	
}
