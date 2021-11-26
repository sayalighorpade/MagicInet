package com.deepak.magicinet;

import com.deepak.magicinet.R;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RingerActivity extends Activity implements View.OnClickListener {
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ringer);
	stopButton = (Button)findViewById(R.id.stop);
	stopButton.setOnClickListener(this);
	
	AudioManager am = (AudioManager)getSystemService(AUDIO_SERVICE);
	am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
	
	Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
	player = new MediaPlayer();
	try {
		player.setDataSource(this, alert);
		player.setAudioStreamType(AudioManager.STREAM_ALARM);
		player.setLooping(true);
		player.prepare();
		player.start();
	}
	catch(Exception e) {
	}
}

public void onClick(View view) {
	player.stop();
	Toast.makeText(RingerActivity.this,"Thank You...",Toast.LENGTH_LONG).show();
}

private Button stopButton;
private MediaPlayer player;
}
