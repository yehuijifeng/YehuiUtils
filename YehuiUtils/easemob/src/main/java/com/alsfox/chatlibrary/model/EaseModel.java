package com.alsfox.chatlibrary.model;

import android.content.Context;

import com.alsfox.chatlibrary.utils.PreferenceManager;


public class EaseModel {
	public Context context = null;

	public EaseModel(Context ctx) {
		context = ctx;
		PreferenceManager.init(context);
	}

	/**
	 * 设置当前用户的环信id
	 * 
	 * @param username
	 */
	public void setCurrentUserName(String username) {
		PreferenceManager.getInstance().setCurrentUserName(username);
	}

	/**
	 * 设置当前用户的环信密码
	 */
	public void setCurrentUserPwd(String password){
		PreferenceManager.getInstance().setCurrentUserPwd(password);
	}
	
	
	/**
	 * 获取当前用户的环信id
	 */
	public String getCurrentUsernName() {
		return PreferenceManager.getInstance().getCurrentUsername();
	}

}
