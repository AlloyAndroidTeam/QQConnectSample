package com.alloyteam.qqconnect;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import com.tencent.tauth;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    private void auth(String clientId, String target) 
    {
        // 使用WebView打开
        Intent intent = new Intent(TAuthDemoActivity.this, com.tencent.tauth.TAuthView.class);
        intent.putExtra(TAuthView.CLIENT_ID, clientId);
        intent.putExtra(TAuthView.SCOPE, scope);	
        intent.putExtra(TAuthView.TARGET, target);
        intent.putExtra(TAuthView.CALLBACK, CALLBACK); 
        startActivity(intent);		
    }
}
