package com.deepak.magicinet;

import com.deepak.magicinet.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Device_Security extends Activity implements OnClickListener {
	Button profile,simAuth;
	Intent i;
	
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_layout);
        
        profile=(Button)findViewById(R.id.profile);
        simAuth=(Button)findViewById(R.id.sim);
        
        profile.setOnClickListener(this);
        simAuth.setOnClickListener(this);
    }

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.profile:
			 i=new Intent(Device_Security.this,MobiDroid.class);
			startActivity(i);
			break;
case R.id.sim:
	i=new Intent(Device_Security.this,SimAlert.class);
	startActivity(i);
			break;

		default:
			break;
		}
	}
}
