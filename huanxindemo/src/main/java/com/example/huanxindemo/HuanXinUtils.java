package com.example.huanxindemo;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.easemob.EMCallBack;
import com.easemob.EMConnectionListener;
import com.easemob.EMError;
import com.easemob.chat.EMChat;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMConversation;
import com.easemob.chat.EMGroupManager;
import com.easemob.chat.EMMessage;
import com.easemob.chat.FileMessageBody;
import com.easemob.chat.MessageBody;
import com.easemob.chat.NormalFileMessageBody;
import com.easemob.chat.TextMessageBody;
import com.easemob.util.NetUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jackiechan on 15/12/19.
 */
public class HuanXinUtils {
    /**
     * 环信的注册 一般都是服务端来注册  给大家个建议
     和服务端进行协商 注册由服务端注册, 注册的方式  因为每个用户都有一个唯一的标示,用这个唯一的标示做账号,唯一标示的MD5或者是什么SHA1等唯一值做密码,来注册环信
     比如说,如果你们的唯一标示是自增的数字,那么服务端就可以预先注册一部分环信账号,  到时候只要返回回来就可以

     10000
     10001
     10002

     -1010000

     好友管理的建议:

     不要通过环信的好友管理来加好友:
     通过自己的服务器来加好友,将好友放在自己的服务端
     服务端写一个接口 添加好友的接口, 我加你后, 服务端推送一条消息给对方, 对方点击同意后 服务端将两者置为好友关系,如果聊天,可以从好友列表中获取到好友的id然后给他发送消息
     */


    /**
     * 登录
     *
     * @param userName
     * @param password
     */
    public static void login(final String userName, String password) {
        if (EMChat.getInstance().isLoggedIn() && !EMChatManager.getInstance().isConnected()) {//判断是否登录
            return;
        }
        EMChatManager.getInstance().login(userName, password, new EMCallBack() {//回调
            @Override
            public void onSuccess() {
//                runOnUiThread(new Runnable() {
//                    public void run() {
                EMGroupManager.getInstance().loadAllGroups();
                EMChatManager.getInstance().loadAllConversations();
                Log.e("huanxindemo", userName + "==登陆聊天服务器成功！");
//                    }
//                });
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                Log.e("huanxindemo", "登陆聊天服务器失败！");
            }
        });

    }

    /**
     * 注册链接状态的监听
     *
     * @param context
     */
    public static void registConnectionListener(final Context context) {
        //注册一个监听连接状态的listener
        EMChatManager.getInstance().addConnectionListener(new MyConnectionListener(context));
    }

    //实现ConnectionListener接口
    private static class MyConnectionListener implements EMConnectionListener {
        private Context context;

        public MyConnectionListener(Context context) {
            this.context = context;
        }

        @Override
        public void onConnected() {
            //已连接到服务器
        }

        @Override
        public void onDisconnected(final int error) {
//                runOnUiThread(new Runnable() {

//            @Override
//            public void run() {
            if (error == EMError.USER_REMOVED) {
                // 显示帐号已经被移除
            } else if (error == EMError.CONNECTION_CONFLICT) {
                // 显示帐号在其他设备登陆
            } else {
                if (NetUtils.hasNetwork(context)) {
                    //判断了网络状态
                }
                //连接不到聊天服务器
                else {

                }
                //当前网络不可用，请检查网络设置
            }
        }
//                });
    }

    /**
     * 同步退出登录
     */
    public static void logoutSync() {
        EMChatManager.getInstance().logout();//此方法为同步方法
    }

    /**
     * 异步退出登录
     */
    public static void logoutAsync() {
        //此方法为异步方法
        EMChatManager.getInstance().logout(new EMCallBack() {

            @Override
            public void onSuccess() {
                // TODO Auto-generated method stub

            }

            @Override
            public void onProgress(int progress, String status) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onError(int code, String message) {
                // TODO Auto-generated method stub

            }
        });
    }

    //注册监听收到消息的广播
    public static void registBroadReciver(Context context) {
        //只有注册了广播才能接收到新消息，目前离线消息，在线消息都是走接收消息的广播（离线消息目前无法监听，在登录以后，接收消息广播会执行一次拿到所有的离线消息）
        NewMessageBroadcastReceiver msgReceiver = new NewMessageBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(EMChatManager.getInstance().getNewMessageBroadcastAction());
        intentFilter.setPriority(3);
        context.registerReceiver(msgReceiver, intentFilter);
    }

    /**
     * 发送文本消息
     *
     * @param username 收消息方
     * @param content  发送的内容,或者是文件路径
     * @param type     消息的类型
     */
    public static void sendMessage(String username, String content, EMMessage.Type type, int chatType) {
        //获取到与聊天人的会话对象。参数username为聊天人的userid或者groupid，后文中的username皆是如此
        EMConversation conversation = EMChatManager.getInstance().getConversation(username);
//创建一条文本消息
        EMMessage message = EMMessage.createSendMessage(type);
//如果是群聊，设置chattype,默认是单聊\,如果传递1过来就设置为群聊
        if (chatType == 1) {
            message.setChatType(EMMessage.ChatType.GroupChat);
        }
//设置消息body
        MessageBody messageBody = null;
        switch (type) {
            case TXT:
                messageBody = new TextMessageBody(content);
                break;

            case FILE:
                messageBody = new NormalFileMessageBody(new File(content));
                break;
        }
//        TextMessageBody txtBody = new TextMessageBody(content);
        message.addBody(messageBody);
//设置接收人
        message.setReceipt(username);
//把消息加入到此会话对象中
        conversation.addMessage(message);
//发送消息
        EMChatManager.getInstance().sendMessage(message, new EMCallBack() {
            @Override
            public void onSuccess() {
//发送消息成功
                Log.e("huanxindemo", "消息发送成");
            }

            @Override
            public void onError(int i, String s) {
                //发送失败
                Log.e("huanxindemo", "发送失败" + "====" + i + "======" + s);
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }

    public static void downLaodFile(FileMessageBody fileMessageBody) {

        Map<String, String> maps = new HashMap();
        if (!TextUtils.isEmpty(fileMessageBody.getSecret())) {
            maps.put("share-secret", fileMessageBody.getSecret());
        }
        EMChatManager.getInstance().downloadFile(fileMessageBody.getRemoteUrl(), Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+fileMessageBody.getFileName(), maps, new EMCallBack() {
            @Override
            public void onSuccess() {
                Log.e("huanxindemo", "文件下载成功");
            }

            @Override
            public void onError(int i, String s) {

            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }
}
