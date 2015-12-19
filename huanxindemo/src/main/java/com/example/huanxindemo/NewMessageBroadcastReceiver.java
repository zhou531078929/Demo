package com.example.huanxindemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMConversation;
import com.easemob.chat.EMMessage;
import com.easemob.chat.FileMessageBody;
import com.easemob.chat.ImageMessageBody;
import com.easemob.chat.LocationMessageBody;
import com.easemob.chat.TextMessageBody;
import com.easemob.chat.VoiceMessageBody;

public class NewMessageBroadcastReceiver extends BroadcastReceiver {
    public NewMessageBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // 注销广播
        abortBroadcast();

        // 消息id（每条消息都会生成唯一的一个id，目前是SDK生成）
        String msgId = intent.getStringExtra("msgid");
        //发送方
        String username = intent.getStringExtra("from");
        // 收到这个广播的时候，message已经在db和内存里了，可以通过id获取mesage对象
        EMMessage message = EMChatManager.getInstance().getMessage(msgId);
        EMConversation conversation = EMChatManager.getInstance().getConversation(username);
        // 如果是群聊消息，获取到group id
        if (message.getChatType() == EMMessage.ChatType.GroupChat) {
            username = message.getTo();
        }
        if (!username.equals(username)) {
            // 消息不是发给当前会话，return
            return;
        }
        switch (message.getType()) {
            case TXT:
                TextMessageBody txtBody = (TextMessageBody)message.getBody();
                Log.e("huanxindemo", txtBody.getMessage());
                break;
            case IMAGE:
                ImageMessageBody imgBody = (ImageMessageBody)message.getBody();
                break;
            case VOICE:
                VoiceMessageBody voiceBody = (VoiceMessageBody)message.getBody();
                break;
            case LOCATION:
                LocationMessageBody locationBody = (LocationMessageBody)message.getBody();
                break;
            case FILE:
                FileMessageBody fileMessageBody = (FileMessageBody) message.getBody();
                HuanXinUtils.downLaodFile(fileMessageBody);
                break;

        }
        Log.e("huanxindemo", "收到" + username + "发来的" + message.getBody());
    }
}
