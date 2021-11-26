package com.deepak.magicinet;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class smBoot extends BroadcastReceiver {
	SharedPreferences prefs, prefn;
	SharedPreferences.Editor editor;
Intent i;
	@SuppressLint("NewApi")
	@Override
	public void onReceive(Context ctx, Intent arg1) {
		if ("android.intent.action.BOOT_COMPLETED".equals(arg1.getAction())) {

			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			System.out.println("BootReceiver Started");
			Log.d("BootReceiver", "BootReceive Started");
			Toast.makeText(ctx, "sim boot stared", Toast.LENGTH_LONG).show();
			prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
			editor = prefs.edit();
			String srno = prefs.getString(SimAlert.SERIAL_NUMBER, "");
			String no1 = prefs.getString(SimAlert.NUMBER1, "");
			String no2 = prefs.getString(SimAlert.NUMBER2, "");
			String no3 = prefs.getString(SimAlert.NUMBER3, "");

			TelephonyManager tManager = (TelephonyManager) ctx
					.getSystemService(Context.TELEPHONY_SERVICE);
			String currentNumber = tManager.getLine1Number();
			Log.d("mobile  no", "" + currentNumber);

			Toast.makeText(ctx, "" + currentNumber, Toast.LENGTH_LONG).show();
			String simSerialNumber = tManager.getSimSerialNumber();

			Log.d("serial no", "" + currentNumber);

			Toast.makeText(
					ctx,
					"old serial no " + srno + " new serial no "
							+ simSerialNumber, Toast.LENGTH_LONG).show();
			if (!srno.equals(simSerialNumber)) {

				Toast.makeText(ctx, "SMS SEND To " + no1, Toast.LENGTH_LONG)
						.show();

				SmsManager sm = SmsManager.getDefault();
				sm.sendTextMessage(
						no1,
						null,
						"New SIM Card Detected. New Number : " + currentNumber
								+ " Location is :" + tManager.getCellLocation()
								+ " And Network Operator is: "
								+ tManager.getNetworkOperatorName(), null, null);
				sm.sendTextMessage(
						no2,
						null,
						"New SIM Card Detected. New Number : " + currentNumber
								+ " Location is :" + tManager.getCellLocation()
								+ " And Network Operator is: "
								+ tManager.getNetworkOperatorName(), null, null);
				sm.sendTextMessage(
						no3,
						null,
						"New SIM Card Detected. New Number : " + currentNumber
								+ " Location is: " + tManager.getCellLocation()
								+ " And Network Operator is :"
								+ tManager.getNetworkOperatorName(), null, null);
			}
		}

	}
}