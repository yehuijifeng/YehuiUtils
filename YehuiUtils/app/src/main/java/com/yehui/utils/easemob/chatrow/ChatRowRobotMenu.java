package com.yehui.utils.easemob.chatrow;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alsfox.chatlibrary.R;
import com.alsfox.chatlibrary.constant.Constant;
import com.easemob.chat.EMMessage;
import com.easemob.easeui.widget.chatrow.EaseChatRow;
import com.easemob.util.DensityUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.yehui.utils.application.YehuiApplication;
import com.yehui.utils.easemob.activity.ChatActivity;
import com.yehui.utils.easemob.helper.EaseHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ChatRowRobotMenu extends EaseChatRow {

    TextView tvTitle;
    LinearLayout tvList;
    ImageView tvImg;

    public ChatRowRobotMenu(Context context, EMMessage message, int position, BaseAdapter adapter) {
        super(context, message, position, adapter);
    }

    @Override
    protected void onInflatView() {
        if (EaseHelper.getInstance().isRobotMenuMessage(message)) {
            inflater.inflate(message.direct == EMMessage.Direct.RECEIVE ? R.layout.em_row_received_menu
                    : R.layout.ease_row_sent_message, this);

        }
//		else if (DemoHelper.getInstance().isEvalMessage(message)) {
//			inflater.inflate(message.direct == EMMessage.Direct.RECEIVE ? R.layout.row_received_satisfaction
//					: R.layout.row_sent_satisfaction, this);
//		} else if (message.getStringAttribute(Constant.PICTURE_MSG, null) != null) {
//			inflater.inflate(message.direct == EMMessage.Direct.RECEIVE ? R.layout.ease_row_received_message
//					: R.layout.row_received_picture_new, this);
//		}
    }

    @Override
    protected void onFindViewById() {
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvList = (LinearLayout) findViewById(R.id.ll_layout);
        tvImg= (ImageView) findViewById(R.id.iv_userhead);
        if(message.direct != EMMessage.Direct.RECEIVE){
            ImageLoader imageLoader=ImageLoader.getInstance();
            imageLoader.displayImage("drawable://" + com.yehui.utils.R.mipmap.ic_launcher,tvImg, YehuiApplication.roundOptions);
        }
    }

    @Override
    protected void onUpdateView() {

    }

    @Override
    protected void onSetUpView() {
        try {
            JSONObject jsonObj = message.getJSONObjectAttribute(Constant.MESSAGE_ATTR_MSGTYPE);

            if (jsonObj.has("choice")) {
                JSONObject jsonChoice = jsonObj.getJSONObject("choice");
                String title = jsonChoice.getString("title");
                tvTitle.setText(title);
                setRobotMenuMessageLayout(tvList, jsonChoice.getJSONArray("list"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onBubbleClick() {
        // TODO Auto-generated method stub

    }

    private void setRobotMenuMessageLayout(LinearLayout parentView, JSONArray jsonArr) {
        try {
            parentView.removeAllViews();
            for (int i = 0; i < jsonArr.length(); i++) {
                final String itemStr = jsonArr.getString(i);
                final TextView textView = new TextView(context);
                textView.setText(itemStr);
                textView.setTextSize(15);
                try {
                    //XmlPullParser xrp = context.getResources().getXml(R.drawable.em_menu_msg_text_color);
                    textView.setTextColor(context.getResources().getColor(R.color.em_menu_msg_text_color));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                textView.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        ((ChatActivity) context).sendTextMessage(itemStr);
                    }
                });
                LayoutParams llLp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                llLp.bottomMargin = DensityUtil.dip2px(context, 3);
                llLp.topMargin = DensityUtil.dip2px(context, 3);
                parentView.addView(textView, llLp);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
