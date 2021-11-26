package com.deepak.magicinet;

import com.deepak.magicinet.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class business extends Activity implements OnClickListener {
	
	Button stockest,bank;
	Intent intent;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stock_layout);
		
		 bank = (Button) findViewById(R.id.bank);
		// stockest=(Button)findViewById(R.id.sto);
		bank.setOnClickListener(this); 
		//stockest.setOnClickListener(this); 
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	switch (v.getId()) {
	case R.id.bank:
		intent=new Intent(business.this,banklist.class);
		startActivity(intent);
		break;
/**case R.id.sto:
	intent=new Intent(business.this,stocest.class);
	startActivity(intent);
		break;*/

	default:
		break;
	}	
	}
	 
	     
	}