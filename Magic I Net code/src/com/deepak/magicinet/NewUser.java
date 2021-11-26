package com.deepak.magicinet;

import com.deepak.magicinet.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class NewUser extends Activity implements OnClickListener {
	private SharedPreferences sp;

	Intent i;
	Button b1, b2;
	EditText ed1, ed2, ed3, ed4;
	String user, pass, cpass, eid, chk;
	CheckBox ck;
	String stat = "a";

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newuserreg);
		this.setTitle("Registration");

		ed1 = (EditText) findViewById(R.id.edit1);
		ed2 = (EditText) findViewById(R.id.edit2);
		ed3 = (EditText) findViewById(R.id.edit3);

		b1 = (Button) findViewById(R.id.button1);
		b2 = (Button) findViewById(R.id.button2);
		ck = (CheckBox) findViewById(R.id.check1);
		b1.setOnClickListener(this);
		b2.setOnClickListener(this);

		sp = this.getSharedPreferences("Register", MODE_WORLD_READABLE);
		chk = sp.getString("USERNAME", "");
		if (chk.length() != 0) {
			sp = getSharedPreferences("Register", MODE_WORLD_WRITEABLE);
			Editor myEditor = sp.edit();
			myEditor.putString("Status", stat);
			myEditor.commit();

			/*i = new Intent(this,LoginActivity.class);
			startActivity(i);
			finish();*/
	
		}

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Toast.makeText(this, "First Create Username And Password", Toast.LENGTH_LONG)
					.show();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@SuppressWarnings("deprecation")
	public void onClick(View arg0) {
		user = ed1.getText().toString();
		pass = ed2.getText().toString();
		cpass = ed3.getText().toString();
		if (arg0 == b1) {

			if (ck.isChecked()) {

				if ((user.length() != 0)) {
					if ((pass.length() != 0)) {
						if (pass.compareTo(cpass) == 0) {

							sp = getSharedPreferences("Register",
									MODE_WORLD_WRITEABLE);
							Editor myEditor = sp.edit();
							myEditor.putString("USERNAME", user);
							myEditor.putString("PASSWORD", pass);
							myEditor.commit();
							Toast.makeText(this, "Registration is successfull",
									Toast.LENGTH_LONG).show();
							i = new Intent(this, LoginActivity.class);
							startActivity(i);
						} else {
							Toast.makeText(this, "Password Mismatch", Toast.LENGTH_LONG)
									.show();
						}
					} else {
						Toast.makeText(this, "Please Enter password", Toast.LENGTH_LONG)
								.show();

					}
				} else {
					Toast.makeText(this, "Please Enter Username", Toast.LENGTH_LONG).show();
				}
			} else {
				Toast.makeText(this,
						"Please Accept all our terms and conditions", Toast.LENGTH_LONG)
						.show();
			}
		} else if (arg0 == b2) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Exit");
			builder.setMessage("Do you want to exit");
			builder.setCancelable(false);
			builder.setPositiveButton("Yes",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							finish();
						}
					});
			builder.setNegativeButton("No",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface arg0, int arg1) {
							arg0.cancel();
						}
					});
			AlertDialog alert = builder.create();
			alert.show();

		}
	}
}
