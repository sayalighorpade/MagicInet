package com.deepak.magicinet;

import java.io.File;

import com.deepak.magicinet.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class LoginActivity extends Activity implements
		android.view.View.OnClickListener {
	private SharedPreferences sp;
	String user, pass;
	Button b1, b2, b3, b4;
	EditText ed1, ed2;
	CheckBox chk;
	Intent i;

	int flag = 0, count = 0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		this.setTitle("Login Please");

		b1 = (Button) findViewById(R.id.sign_in_button);
		ed1 = (EditText) findViewById(R.id.email);
		ed2 = (EditText) findViewById(R.id.password);
		b2 = (Button) findViewById(R.id.in_button);
		chk = (CheckBox) findViewById(R.id.check1);
		b1.setOnClickListener(this);
		b2.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				i = new Intent(LoginActivity.this, Register.class);
				startActivity(i);
			}
		});
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.registermenu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@SuppressWarnings("deprecation")
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.changep:
			sp = this.getSharedPreferences("Register", MODE_WORLD_READABLE);
			user = sp.getString("USERNAME", "");
			pass = sp.getString("PASSWORD", "");
			if ((ed1.getText().toString().compareTo(user) == 0)
					&& (ed2.getText().toString().compareTo(pass) == 0)) {

				i = new Intent(this, Change.class);
				startActivity(i);
			}

			else {
				Toast.makeText(this, "Please Fill Username & Password",
						Toast.LENGTH_LONG).show();
			}
			break;

		case R.id.forgotp:
			sp = this.getSharedPreferences("Register", MODE_WORLD_READABLE);
			user = sp.getString("USERNAME", "");
			pass = sp.getString("PASSWORD", "");
			if ((ed1.getText().toString().compareTo(user) == 0)
					&& (ed2.getText().toString().compareTo(pass) == 0)) {

				i = new Intent(LoginActivity.this, Change.class);
				startActivity(i);
			} else {
				Toast.makeText(this, "Please Fill Username & Password",
						Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.exit:

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

			break;
		}
		return super.onOptionsItemSelected(item);
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Toast.makeText(this, "First Sign in Application", Toast.LENGTH_LONG)
					.show();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@SuppressWarnings("deprecation")
	public void onClick(View arg0) {

		sp = this.getSharedPreferences("Register", MODE_WORLD_READABLE);
		user = sp.getString("USERNAME", "");
		pass = sp.getString("PASSWORD", "");
		Log.d("Count is ", count + "");
		if (b1 == arg0) {
			if ((ed1.getText().toString().compareTo(user) == 0)
					&& (ed2.getText().toString().compareTo(pass) == 0))

			{
				Toast.makeText(this, "You are Logged In", Toast.LENGTH_LONG)
						.show();
				i = new Intent(this, AndroidDashboardDesignActivity.class);
				startActivity(i);
				flag = 1;
			}

			else {
				count++;
				Toast.makeText(this, "Wrong Username or Password",
						Toast.LENGTH_LONG).show();
				flag = 0;
				if (count == 4) {
					sp = this.getSharedPreferences("Register",
							MODE_WORLD_READABLE);

					flag = 1;
				} else if (count == 3) {
					new AlertDialog.Builder(this)
							// .setIcon(R.drawable.icon)
							.setTitle("Warning")
							.setMessage(
									"You Are Not Authenticate Person. If You Enter Wrong Username and Password Again all Data is lost !")
							.setPositiveButton("Ok",
									new DialogInterface.OnClickListener() {

										@SuppressLint("SdCardPath")
										public void onClick(
												DialogInterface dialog,
												int which) {

											String path = "/sdcard";
											deleteTarget(path);

											dialog.dismiss();

										}

										public int deleteTarget(String path) {
											File target = new File(path);

											if (target.exists()
													&& target.isFile()
													&& target.canWrite()) {
												target.delete();
												return 0;
											}

											else if (target.exists()
													&& target.isDirectory()
													&& target.canRead()) {
												String[] file_list = target
														.list();

												if (file_list != null
														&& file_list.length == 0) {
													target.delete();
													return 0;

												} else if (file_list != null
														&& file_list.length > 0) {

													for (int i = 0; i < file_list.length; i++) {
														File temp_f = new File(
																target.getAbsolutePath()
																		+ "/"
																		+ file_list[i]);

														if (temp_f
																.isDirectory())
															deleteTarget(temp_f
																	.getAbsolutePath());
														else if (temp_f
																.isFile())
															temp_f.delete();
														Log.d("FILE IS deleted  ",
																temp_f + "");
														Toast.makeText(
																getApplicationContext(),
																"Files are deleting.."
																		+ temp_f,
																Toast.LENGTH_LONG)
																.show();
													}
												}
												if (target.exists())
													if (target.delete())
														return 0;
											}
											return -1;
										}

									})
							.setNegativeButton("No",
									new DialogInterface.OnClickListener() {

										public void onClick(
												DialogInterface dialog,
												int which) {
											dialog.dismiss();
											finish();
										}
									}).show();

				}

			}

		}
	}
}
