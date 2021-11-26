package com.deepak.magicinet;

import com.deepak.magicinet.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;

public class SimAlert extends PreferenceActivity {
	/** Called when the activity is first created. */
	static int count = 0;

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.preferences);
		prefs = PreferenceManager.getDefaultSharedPreferences(this);

		EditTextPreference serialNo = (EditTextPreference) findPreference("keyword");
		EditTextPreference number1 = (EditTextPreference) findPreference("number1");
		EditTextPreference number2 = (EditTextPreference) findPreference("number2");
		EditTextPreference number3 = (EditTextPreference) findPreference("number3");
		EditTextPreference password = (EditTextPreference) findPreference("password");

		TelephonyManager tManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String simSerialNumber = tManager.getSimSerialNumber();
		serialNo.setText(simSerialNumber);
		SharedPreferences.Editor editor = prefs.edit();
		if (count == 0) {
			editor.putString(SERIAL_NUMBER, simSerialNumber);
			editor.putString("NUMBER1", number1 + "");
			editor.putString("NUMBER2", number2 + "");
			editor.putString("NUMBER3", number3 + "");
			editor.putString("PASSWORD", password + "");
			editor.commit();
			count++;
		}

	}

	SharedPreferences prefs, prefn;
	public static final String SERIAL_NUMBER = "serial_number";
	public static final String NUMBER1 = "number1";
	public static final String NUMBER2 = "number2";
	public static final String NUMBER3 = "number3";
	public static final String PASSWORD = "password";
}
