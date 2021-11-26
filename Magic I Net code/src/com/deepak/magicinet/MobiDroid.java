package com.deepak.magicinet;

import com.deepak.magicinet.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MobiDroid extends Activity implements OnClickListener {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	
	setContentView(R.layout.setword);
	prefs = PreferenceManager.getDefaultSharedPreferences(this);
	attentionWordEditText = (EditText) findViewById(R.id.attentionWord);
	saveButton = (Button) findViewById(R.id.save);

	saveButton.setOnClickListener(this);
}

public void onClick(View view) {

	SharedPreferences.Editor editor = prefs.edit();
	editor.putString(ATTENTION_WORD, attentionWordEditText.getText()
			.toString());
	editor.commit();
	Toast.makeText(getApplicationContext(), "Word is saved",Toast.LENGTH_LONG).show();

}

private SharedPreferences prefs;
private Button saveButton;
private EditText attentionWordEditText;

public static final String ATTENTION_WORD = "attention_word";

@Override
public boolean onCreateOptionsMenu(Menu menu) {
	getMenuInflater().inflate(R.menu.setting, menu);

	return true;
}

@Override
public boolean onOptionsItemSelected(MenuItem item) {
	// TODO Auto-generated method stub

	switch (item.getItemId()) {
	case R.id.menu_settings:

		Dialog dialog = new Dialog(MobiDroid.this);
		dialog.setTitle("May I Help You???");
		dialog.setContentView(R.layout.customdialog);

		dialog.setCancelable(true);
		TextView text = (TextView) dialog.findViewById(R.id.tv);

		text.setText("1).mobidroid is used when our mobile is in silent mode\n"
				+ "2).Using this we can locate our mobile when it is in silent mode\n "
				+ "3).Set a secret word for settings\n"
				+ "4).Send the secret word from other mobile by using message(SMS)\n"
				+ "5).It start ringing once it get same secret word\n"
				+ "6).It continuous ringing until we stop it\n");

		dialog.show();

		break;

	}
	return true;

}
}
