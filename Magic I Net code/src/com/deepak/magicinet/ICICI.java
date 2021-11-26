package com.deepak.magicinet;

import com.deepak.magicinet.R;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class ICICI extends Activity{
	WebView webView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	setContentView(R.layout.boi);
	webView = (WebView) findViewById(R.id.webView1);
	webView.getSettings().setJavaScriptEnabled(true);
	webView.loadUrl("https://infinity.icicibank.co.in/BANKAWAY?Action.RetUser.Init.001=Y&AppSignonBankId=ICI&AppType=corporate&abrdPrf=N");

	}
}
