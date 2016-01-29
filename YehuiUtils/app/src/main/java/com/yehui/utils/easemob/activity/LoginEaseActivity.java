package com.yehui.utils.easemob.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.alsfox.chatlibrary.constant.Constant;
import com.easemob.EMCallBack;
import com.easemob.EMError;
import com.easemob.chat.EMChat;
import com.easemob.chat.EMChatManager;
import com.easemob.exceptions.EaseMobException;
import com.yehui.utils.easemob.helper.EaseHelper;

/**
 * Created by yehuijifeng
 * on 2016/1/26.
 * 环信客服登录页面
 */
public class LoginEaseActivity extends BaseEaseActivity {

    private boolean progressShow;
    private ProgressDialog progressDialog;
    private int selectedIndex = Constant.INTENT_CODE_IMG_SELECTED_DEFAULT;
    private int messageToIndex = Constant.MESSAGE_TO_DEFAULT;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        Intent intent = getIntent();
        selectedIndex = intent.getIntExtra(Constant.INTENT_CODE_IMG_SELECTED_KEY,
                Constant.INTENT_CODE_IMG_SELECTED_DEFAULT);
        messageToIndex = intent.getIntExtra(Constant.MESSAGE_TO_INTENT_EXTRA, Constant.MESSAGE_TO_DEFAULT);
        getLogin();
        //EMChat.getInstance().isLoggedIn() 可以检测是否已经登录过环信，如果登录过则环信SDK会自动登录，不需要再次调用登录操作


    }

    private void getLogin() {
        if (EMChat.getInstance().isLoggedIn()) {
            progressDialog = getProgressDialog();
            progressDialog.setMessage(getResources().getString(com.alsfox.chatlibrary.R.string.is_contact_customer));
            progressDialog.show();
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        //加载本地数据库中的消息到内存中
                        EMChatManager.getInstance().loadAllConversations();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    toChatActivity();
                }
            }).start();
        } else {
            createRandomAccountAndLoginChatServer("yehuijifeng", Constant.DEFAULT_COSTOMER_PWD);
        }
    }

    private void createRandomAccountAndLoginChatServer(String userName, String userPwds) {
        final String randomAccount = userName;
        final String userPwd = userPwds;
        progressDialog = getProgressDialog();
        progressDialog.setMessage(getResources().getString(com.alsfox.chatlibrary.R.string.system_is_regist));
        progressDialog.show();
        createAccountToServer(randomAccount, userPwd, new EMCallBack() {

            @Override
            public void onSuccess() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        //登录环信服务器
                        loginHuanxinServer(randomAccount, userPwd);
                    }
                });
            }

            @Override
            public void onProgress(int progress, String status) {
            }

            @Override
            public void onError(final int errorCode, final String message) {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        if (!LoginEaseActivity.this.isFinishing()) {
                            progressDialog.dismiss();
                        }
                        if (errorCode == EMError.NONETWORK_ERROR) {
                            Toast.makeText(getApplicationContext(), "网络不可用", Toast.LENGTH_SHORT).show();
                            finish();
                        } else if (errorCode == EMError.USER_ALREADY_EXISTS) {
                            Toast.makeText(getApplicationContext(), "用户已存在", Toast.LENGTH_SHORT).show();
                            loginHuanxinServer(randomAccount, userPwd);
                        } else if (errorCode == EMError.UNAUTHORIZED) {
                            Toast.makeText(getApplicationContext(), "无开放注册权限", Toast.LENGTH_SHORT).show();
                            finish();
                        } else if (errorCode == EMError.ILLEGAL_USER_NAME) {
                            Toast.makeText(getApplicationContext(), "用户名非法", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "注册失败：" + message, Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
            }
        });
    }

    //注册用户
    private void createAccountToServer(final String uname, final String pwd, final EMCallBack callback) {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    EMChatManager.getInstance().createAccountOnServer(uname, pwd);
                    if (callback != null) {
                        callback.onSuccess();
                    }
                } catch (EaseMobException e) {
                    if (callback != null) {
                        callback.onError(e.getErrorCode(), e.getMessage());
                    }
                }
            }
        });
        thread.start();
    }

    private ProgressDialog getProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(LoginEaseActivity.this);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    progressShow = false;
                }
            });
        }
        return progressDialog;
    }

    public void loginHuanxinServer(final String uname, final String upwd) {
        progressShow = true;
        progressDialog = getProgressDialog();
        progressDialog.setMessage(getResources().getString(com.alsfox.chatlibrary.R.string.is_contact_customer));
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
        // login huanxin server
        EMChatManager.getInstance().login(uname, upwd, new EMCallBack() {

            @Override
            public void onSuccess() {

                if (!progressShow) {
                    return;
                }
                EaseHelper.getInstance().setCurrentUserName(uname);
                EaseHelper.getInstance().setCurrentPassword(upwd);
                try {
                    EMChatManager.getInstance().loadAllConversations();
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
                toChatActivity();
            }

            @Override
            public void onProgress(int progress, String status) {
            }

            @Override
            public void onError(final int code, final String message) {
                if (!progressShow) {
                    return;
                }
                runOnUiThread(new Runnable() {
                    public void run() {
                        if(progressDialog!=null)
                        progressDialog.dismiss();
                        Toast.makeText(LoginEaseActivity.this,
                                getResources().getString(com.alsfox.chatlibrary.R.string.is_contact_customer_failure_seconed) + message,
                                Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
        });
    }

    private void toChatActivity() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!LoginEaseActivity.this.isFinishing())
                    progressDialog.dismiss();
                // 进入主页面
                startActivity(new Intent(LoginEaseActivity.this, ChatActivity.class).putExtra(
                        Constant.INTENT_CODE_IMG_SELECTED_KEY, selectedIndex).putExtra(
                        Constant.MESSAGE_TO_INTENT_EXTRA, messageToIndex));
                finish();
            }
        });
    }

}

