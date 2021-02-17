package com.h.test.action;

import online.morn.actionlike.work.instance.ActionLike;
import online.morn.actionlike.work.test.Tester;

/**
 * 用户页面
 * @author Morn4ever 2015-10-27
 */
public class UserAction extends ActionLike{
	
	private String userName;
	
	public void index(){
		Tester.print("用户首页:" + userName);
		Tester.print("用户首页:" + super.getHttpPro().getStringPara(""));
		//return "continue";
	}
	
	public void center(){
		Tester.print("用户中心:" + userName);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
