package com.hashkey.trackme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class InitiateEmergencyActivity extends Activity {
	
	private boolean helpVisible = false;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_initiate_emergency);
		
	}
	
	public void toggleHelp(View v){
		if (helpVisible){
			helpVisible = false;
			findViewById(R.id.help_text_view).setVisibility(View.INVISIBLE);
			findViewById(R.id.help_usage_button).setVisibility(View.VISIBLE);
		}else if (!helpVisible){
			helpVisible = true;
			findViewById(R.id.help_text_view).setVisibility(View.VISIBLE);
			findViewById(R.id.help_usage_button).setVisibility(View.INVISIBLE);
		}
	}
	
	public void startTracking(View v){
//		to go to the home screen
		Intent startMain = new Intent(Intent.ACTION_MAIN);
    	startMain.addCategory(Intent.CATEGORY_HOME);
    	startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	startActivity(startMain);
//    	start the tracking service
    	int delay = 0;
    	if (v == findViewById(R.id.emergency_button_015)){
    		delay = 60 * 15;
    	}else if(v == findViewById(R.id.emergency_button_030)){
    		delay = 60 * 30;
    	}else if(v == findViewById(R.id.emergency_button_045)){
    		delay = 60 * 30;
    	}else if(v == findViewById(R.id.emergency_button_100)){
    		delay = 60 * 60;
    	}else if(v == findViewById(R.id.emergency_button_130)){
    		delay = 60 * 90;
    	}else if(v == findViewById(R.id.emergency_button_200)){
    		delay = 60 * 120;
    	}else if(v == findViewById(R.id.emergency_button_230)){
    		delay = 60 * 150;
    	}else if(v == findViewById(R.id.emergency_button_300)){
    		delay = 60 * 60 * 3;
    	}else if(v == findViewById(R.id.emergency_button_400)){
    		delay = 60 * 60 * 4;
    	}else if(v == findViewById(R.id.emergency_button_500)){
    		delay = 60 * 60 * 5;
    	}else if(v == findViewById(R.id.emergency_button_700)){
    		delay = 60 * 60 * 7;
    	}else if(v == findViewById(R.id.emergency_button_1000)){
    		delay = 60 * 60 * 10;
    	}
    	if (delay!=0){
    		Toast.makeText(getBaseContext(), "Will check back on you in "+ String.valueOf((int)delay/60) +" minutes.", Toast.LENGTH_LONG).show();
    	}
    	Intent startPostLocation = new Intent(getApplicationContext(), PostLocation.class);
    	startPostLocation.putExtra("delay", delay);
		startService(startPostLocation);
	}
	public void endTracking(View v){
//		to go to the home screen
		Intent startMain = new Intent(Intent.ACTION_MAIN);
    	startMain.addCategory(Intent.CATEGORY_HOME);
    	startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	startActivity(startMain);
//    	stop the tracking service
		stopService(new Intent(getApplicationContext(), PostLocation.class));
	}
}