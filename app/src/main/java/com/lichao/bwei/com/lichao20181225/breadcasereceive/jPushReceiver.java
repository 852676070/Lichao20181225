package com.lichao.bwei.com.lichao20181225.breadcasereceive;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.lichao.bwei.com.lichao20181225.shopdetails.view.ShopDetailsActivity;
import com.lichao.bwei.com.lichao20181225.shopshow.view.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;

/**
 * date:2018/12/26
 * author:李超(li)
 * function:
 */

public class jPushReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();

        String title = bundle.getString(JPushInterface.EXTRA_TITLE);
        String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
        String title2 = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
        String content = bundle.getString(JPushInterface.EXTRA_ALERT);
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        String fileHtml = bundle.getString(JPushInterface.EXTRA_RICHPUSH_HTML_PATH);

        Log.e("onReceive", "--------------------------------------------------");
        Log.e("onReceive", "title : " + title);
        Log.e("onReceive", "title2 : " + title2);
        Log.e("onReceive", "message: " + message);
        Log.e("onReceive", "content: 测试有值 " + content);
        Log.e("onReceive", "extras: " + extras);
        Log.e("onReceive", "fileHtml: " + fileHtml);

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            // 自定义消息不会展示在通知栏，完全要开发者写代码去处理
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction()))     {
             // 在这里可以做些统计，或者做些其他工作
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            // 在这里可以自己写代码去定义用户点击后的行为
            Intent i = new Intent(context, ShopDetailsActivity.class);  //自定义打开的界面
            i.putExtra("pid",1);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        } else {
        }

    }

}