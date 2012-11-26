/**
 * @author azraellong
 * @date 2012-11-26
 */
package com.alloyteam.qqconnect.sample;

import com.alloyteam.qqconnect.Config;
import com.tencent.tauth.TAuthView;
import com.tencent.tauth.TencentOpenHost;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

/**
 * @author azraellong
 * 
 */
public class AuthSample {
	
	Context context;
	AuthReceiver receiver;
	
	public static AuthSample instance;
	
	public static AuthSample getInstance(Context context){
		if(instance == null){
			instance = new AuthSample(context);
		}
		return instance;
	}
	
	private AuthSample(Context context){
		this.context = context;
	}
	
	public void auth() {
		Intent intent = new Intent(context, TAuthView.class);
		intent.putExtra("client_id", Config.APPID);// 必须, 申请注册成功后，分配给应用的appid
		intent.putExtra("scope", Config.ALL_RIGHT);// 可选, 默认只对get_user_info授权
		intent.putExtra("target", "_self");// 必须, “_slef”以webview方式打开;
											// "_blank"以内置安装的浏览器方式打开
		intent.putExtra("callback", ""); // 可选, 如果填了,
											// 则必须与注册时填写的回调地址一致，用来进行第三方应用的身份验证
		//注册 receiver, 接受授权成功的回调
		receiver =  new AuthReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(TencentOpenHost.AUTH_BROADCAST);
        context.registerReceiver(receiver, filter);
        
		context.startActivity(intent);
		
	}
	
	class AuthReceiver extends BroadcastReceiver {
		
		private static final String TAG="AuthReceiver";
		
		/*
		 * (non-Javadoc)
		 * 
		 * @see android.content.BroadcastReceiver#onReceive(android.content.Context,
		 * android.content.Intent)
		 */
		@Override
		public void onReceive(Context context, Intent intent) {
			Bundle exts = intent.getExtras();
			String raw = exts.getString("raw");
			String access_token = exts.getString("access_token");
			String expires_in = exts.getString("expires_in");
			Log.i(TAG, String.format("raw: %s, access_token:%s, expires_in:%s",
					raw, access_token, expires_in));
			if (access_token != null) {
				// 获取到access token 存起来
				Config.accessToken = access_token;
				Config.expiresIn = expires_in;
				
				new AlertDialog.Builder(context)
					.setMessage("授权成功, accessToken: " + Config.accessToken)
					.setNegativeButton("确定", null)
					.show();
			}
			
			context.unregisterReceiver(receiver);
		}

	}
}
