package com.yehui.utils.easemob.chatrow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.alsfox.chatlibrary.R;
import com.alsfox.chatlibrary.constant.Constant;
import com.easemob.chat.EMMessage;
import com.easemob.easeui.widget.chatrow.EaseChatRow;
import com.yehui.utils.easemob.activity.SatisfactionActivity;
import com.yehui.utils.easemob.fragment.ChatFragment;
import com.yehui.utils.easemob.helper.EaseHelper;

import org.json.JSONObject;

public class ChatRowEvaluation extends EaseChatRow{
	
	Button btnEval;

	public ChatRowEvaluation(Context context, EMMessage message, int position, BaseAdapter adapter) {
		super(context, message, position, adapter);
	}

	@Override
	protected void onInflatView() {
		if (EaseHelper.getInstance().isEvalMessage(message)) {
			inflater.inflate(message.direct == EMMessage.Direct.RECEIVE ? R.layout.em_row_received_satisfaction
					: R.layout.em_row_sent_satisfaction, this);
		}
	}

	@Override
	protected void onFindViewById() {
		btnEval = (Button) findViewById(R.id.btn_eval);
		
	}

	@Override
	protected void onUpdateView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onSetUpView() {
		try {
			final JSONObject jsonObj = message.getJSONObjectAttribute(Constant.WEICHAT_MSG);
			if(jsonObj.has("ctrlType")&&!jsonObj.isNull("ctrlType")){
				btnEval.setEnabled(true);
				btnEval.setText("立即评价");
				btnEval.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						//这是评价页面
						((Activity)context).startActivityForResult(new Intent(context, SatisfactionActivity.class)
								.putExtra("msgId", message.getMsgId()), ChatFragment.REQUEST_CODE_EVAL);
					}
				});
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onBubbleClick() {
		
	}

}
