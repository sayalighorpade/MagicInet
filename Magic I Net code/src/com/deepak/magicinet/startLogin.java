package com.deepak.magicinet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class startLogin extends BroadcastReceiver { 
Intent i;
	  public void onReceive(Context context, Intent intent) { 
		    if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {  

	    i=new Intent(context,LoginActivity.class);
	   
	   i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	   context.startActivity(i);
	    
	    Log.d("BootReceiver", "onReceived");
	    Toast.makeText(context,"LoginActivity Started",Toast.LENGTH_LONG).show();
	  }
	  }
	  }
