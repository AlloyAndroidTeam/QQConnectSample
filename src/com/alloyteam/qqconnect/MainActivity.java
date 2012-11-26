package com.alloyteam.qqconnect;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import com.tencent.tauth.TAuthView;
import com.tencent.tauth.TencentOpenHost;
import com.tencent.tauthdemo.TAuthDemoActivity.AuthReceiver;

public class MainActivity extends Activity {

	private AuthReceiver receiver;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth();     
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
	private void registerIntentReceivers() {
		receiver =  new AuthReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(TencentOpenHost.AUTH_BROADCAST);
		registerReceiver(receiver, filter);
	}
	
	private void unregisterIntentReceivers() {
		unregisterReceiver(receiver);
	}
	    
    private void auth() 
    {
        // 使用WebView打开
        Intent intent = new Intent(MainActivity.this, com.tencent.tauth.TAuthView.class);
		intent.putExtra(TencentOpenHost.CLIENT_ID, Config.APPID);
		intent.putExtra(TencentOpenHost.SCOPE, "get_user_info,get_user_profile,add_share,add_topic,list_album,upload_pic,add_album");
		intent.putExtra(TencentOpenHost.TARGET, "_self");
		intent.putExtra(TencentOpenHost.CALLBACK, "auth://tauth.qq.com/");
		
       startActivity(intent);		
    }
}
