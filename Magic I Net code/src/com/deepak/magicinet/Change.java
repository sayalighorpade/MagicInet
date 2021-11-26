package com.deepak.magicinet;

import com.deepak.magicinet.R;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Change extends Activity implements
		android.view.View.OnClickListener {
	EditText ed1, ed2, ed3;
	Button b1, b2;
	private SharedPreferences sp;
	String pass;

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.change);
		this.setTitle("Change Password");
		ed1 = (EditText) findViewById(R.id.edit1);
		ed2 = (EditText) findViewById(R.id.edit2);
		ed3 = (EditText) findViewById(R.id.edit3);
		sp = this.getSharedPreferences("Register", MODE_WORLD_READABLE);
		pass = sp.getString("PASSWORD", "");
		// ed1.setText(pass);
		ed1.getText().toString();
		b1 = (Button) findViewById(R.id.button1);
		b2 = (Button) findViewById(R.id.button2);
		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
	}

	@SuppressWarnings("deprecation")
	public void onClick(View arg0) {
		String o = ed1.getText().toString();
		String p = ed2.getText().toString();
		String c = ed3.getText().toString();
		sp = getSharedPreferences("Register", MODE_WORLD_WRITEABLE);
		if (o.equals("")) {
			Toast.makeText(this, "Old Password must be filled",Toast.LENGTH_LONG).show();
		} else if (o.equals(pass)) {
			Editor myeditor = sp.edit();
			if (arg0 == b1) {
				if (p.compareTo(c) == 0) {
					if (p.equals("")) {
						Toast.makeText(
								this,
								"Password and Confirm Password Must be Filled",
								200).show();
					} else {
						myeditor.putString("PASSWORD", p);
						myeditor.commit();
						Toast.makeText(this, "Password Changed",Toast.LENGTH_LONG).show();
						Intent i = new Intent(this, Register.class);
						startActivity(i);
					}

				} else {
					Toast.makeText(this, "Password Mismatch",Toast.LENGTH_LONG).show();
				}
			} else {
				Intent i = new Intent(this, Register.class);
				startActivity(i);
			}
		}
		else
		{
			Toast.makeText(this, "Old Password is not correct",Toast.LENGTH_LONG).show();
		}
	}

	public void onClick(DialogInterface arg0, int arg1) {
		// TODO Auto-generated method stub

	}

}