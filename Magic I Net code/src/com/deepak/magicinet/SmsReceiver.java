package com.deepak.magicinet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.SmsMessage;

public class SmsReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		if(intent.getAction().equals(ACTION_SMS_RECEIVED)) {
			if(receivedAttentionWordAsSms(intent.getExtras(), context)) {
				Intent ringerIntent = new Intent(context, RingerActivity.class);
				ringerIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(ringerIntent);
			}
		}
	}
	
	private boolean receivedAttentionWordAsSms(Bundle bundle, Context context) {
			SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
			String attentionWord = prefs.getString(MobiDroid.ATTENTION_WORD, null);
			
			if (attentionWord != null) {
				Object[] pdus  = (Object[])bundle.get("pdus");
			
				for(int i=0; i<pdus.length; i++) {
					SmsMessage sms = SmsMessage.createFromPdu((byte[])pdus[i]);
					if(attentionWord.equals(sms.getMessageBody())) {
						return true;
					}
				}
			}
			
			return false;
	}
	
	@SuppressWarnings("unused")
	private static final String TAG = "SmsReceiver";
	private static final String ACTION_SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
}