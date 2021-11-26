package com.deepak.magicinet;

import com.deepak.magicinet.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class News_Reader extends Activity implements OnClickListener  {
	
	ImageButton lokmat,sakal;
	Intent intent;

	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_layout);
        
        lokmat=(ImageButton)findViewById(R.id.news1);
        sakal=(ImageButton)findViewById(R.id.news2);
        
        lokmat.setOnClickListener(this);
        sakal.setOnClickListener(this);
  
    }

	@Override
	public void onClick(View newsview) {
		// TODO Auto-generated method stub
		switch (newsview.getId()) {
		case R.id.news1:
			intent=new Intent(News_Reader.this,lokmat.class);
			startActivity(intent);
			break;
		case R.id.news2:
			intent=new Intent(News_Reader.this,sakal.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
	
	
	}

