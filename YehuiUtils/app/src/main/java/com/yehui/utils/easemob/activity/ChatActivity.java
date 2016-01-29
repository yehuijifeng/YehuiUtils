package com.yehui.utils.easemob.activity;

import android.content.Intent;
import android.os.Bundle;

import com.alsfox.chatlibrary.R;
import com.alsfox.chatlibrary.constant.Constant;
import com.alsfox.chatlibrary.utils.HelpDeskPreferenceUtils;
import com.yehui.utils.easemob.fragment.ChatFragment;
import com.yehui.utils.easemob.fragment.EaseChatFragmentX;


/**
 * 聊天页面，需要fragment的使用{@link # CEaseChatFragment}
 * 
 */
public class ChatActivity extends BaseEaseActivity {

	public static ChatActivity activityInstance;
	private EaseChatFragmentX chatFragment;
	String toChatUsername;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.em_activity_chat);
		activityInstance = this;
		// 聊天人或群id,即后台配置的IM号
		toChatUsername = HelpDeskPreferenceUtils.getInstance(this).getSettingCustomerAccount();
		// 可以直接new EaseChatFratFragment使用
		chatFragment = new ChatFragment();
		Intent intent = getIntent();
		intent.putExtra(Constant.EXTRA_USER_ID, toChatUsername);
		// 传入参数
		chatFragment.setArguments(intent.getExtras());
		getSupportFragmentManager().beginTransaction().add(R.id.container, chatFragment).commit();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		activityInstance = null;
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		// 点击notification bar进入聊天页面，保证只有一个聊天页面
		String username = intent.getStringExtra("userId");
		if (toChatUsername.equals(username))
			super.onNewIntent(intent);
		else {
			finish();
			startActivity(intent);
		}

	}

	@Override
	public void onBackPressed() {
		chatFragment.onBackPressed();
	}

	public String getToChatUsername() {
		return toChatUsername;
	}
	
	public void sendTextMessage(String txtContent){
		chatFragment.sendTextMessage(txtContent);
	}
	
}
