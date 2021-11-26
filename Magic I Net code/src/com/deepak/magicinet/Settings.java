package com.deepak.magicinet;

import com.deepak.magicinet.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Settings extends Activity implements OnClickListener {
 SharedPreferences sp;

	Intent i;
	Button b1;
	Button b2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_layout);
		b1 = (Button) findViewById(R.id.reg);
		b1.setOnClickListener(this);
		b2=(Button) findViewById(R.id.contact);
		b2.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.reg:
			i = new Intent(Settings.this, NewUser.class);
			startActivity(i);
			break;

		case R.id.contact:
			i = new Intent(Settings.this, userContact.class);
			startActivity(i);
			break;

		default:
			break;
		}
		
		
	}
}
