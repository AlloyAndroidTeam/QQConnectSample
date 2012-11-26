package com.alloyteam.qqconnect;

import com.alloyteam.qqconnect.sample.AuthSample;
import com.tencent.tauth.TencentOpenRes;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button loginButton = (Button) findViewById(R.id.loginButton);
//		loginButton.setImageDrawable(TencentOpenRes.getBigLoginBtn(getAssets()));	
		loginButton.setOnClickListener(onViewClickListener);
		
	}

	private View.OnClickListener onViewClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			Context context = MainActivity.this;
			switch (v.getId()) {
			case R.id.loginButton:
				AuthSample.getInstance(context).auth();
				break;
			default:
				break;
			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
