package com.deepak.magicinet;

import com.deepak.magicinet.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class banklist extends Activity implements OnClickListener {
	ImageButton sbi, bom, boi, icici, hdfc;
	Intent intent = new Intent();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.banklist);

		sbi = (ImageButton) findViewById(R.id.sbiimage);
		bom = (ImageButton) findViewById(R.id.bomimage);
		boi = (ImageButton) findViewById(R.id.boiimage);
		icici = (ImageButton) findViewById(R.id.iciciimage);
		hdfc = (ImageButton) findViewById(R.id.hdfcimage);

		sbi.setOnClickListener(this);
		bom.setOnClickListener(this);
		boi.setOnClickListener(this);
		icici.setOnClickListener(this);
		hdfc.setOnClickListener(this);
	}

	@Override
	public void onClick(View bankclick) {
		// TODO Auto-generated method stub
		switch (bankclick.getId()) {
		case R.id.boiimage:
			intent = new Intent(getApplicationContext(), PNB.class);
			break;
		case R.id.bomimage:
			intent = new Intent(getApplicationContext(), BOM.class);
			break;
		case R.id.sbiimage:
			intent = new Intent(getApplicationContext(), SBI.class);
			break;
		case R.id.iciciimage:
			intent = new Intent(getApplicationContext(), ICICI.class);
			break;
		case R.id.hdfcimage:
			intent = new Intent(getApplicationContext(), HDFC.class);
			break;
		}
		startActivity(intent);
	}

}
