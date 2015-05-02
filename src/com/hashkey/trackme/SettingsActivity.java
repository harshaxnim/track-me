package com.hashkey.trackme;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class SettingsActivity extends Activity{
	
	private EditText emergencyNumber = null;
	private CheckBox smsCheckBox = null;
	private EditText smsInterval = null;
	public static SharedPreferences pref = null;;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		emergencyNumber = (EditText) findViewById(R.id.emergency_phone_edit_text);
		smsCheckBox = (CheckBox) findViewById(R.id.sms_check_box);
		smsInterval = (EditText) findViewById(R.id.sms_interval_edit_text);
		pref = PreferenceManager.getDefaultSharedPreferences(this);
		//update content
		updateContent();
		
	}
	
  //public void onSavePressed, save the preferences, and update the cloud on changed values.
	public void onSavePressed(View v){
		String emergencyContact = emergencyNumber.getText().toString().trim();
		if (emergencyContact.length() < 10) {
			Toast.makeText(getApplicationContext(), "Couldn't save. Enter a mobile number.", Toast.LENGTH_SHORT).show();
			return;
	    }
		
		//online datastore
		ParseUser currentUser = ParseUser.getCurrentUser();
		if(currentUser == null){
			Toast.makeText(getApplicationContext(), "Couldn't save. Session Failed. Login Again", Toast.LENGTH_SHORT).show();
			Log.d("MINE","FAIL");
			return;
		}
		currentUser.put("emergencyContact", emergencyContact);
		currentUser.saveEventually(new SaveCallback() {
			@Override
			public void done(ParseException e) {
				if (e == null) {
			    } else {
			    	Toast.makeText(getApplicationContext(), "Couldn't save\nERROR: "+e.getMessage(), Toast.LENGTH_LONG).show();
			    }
			}
		});
		
		//Local datastore
		pref.edit().putBoolean("smsEnabled", smsCheckBox.isChecked()).apply();
		pref.edit().putInt("smsInterval", Integer.valueOf(smsInterval.getText().toString())).apply();
		pref.edit().putString("emergencyContact", emergencyContact).apply();
		
		Toast.makeText(getApplicationContext(), "Preferences Saved", Toast.LENGTH_SHORT).show();
		this.finish();
	}
	
	private void updateContent(){
		ParseUser currentUser = ParseUser.getCurrentUser();
		emergencyNumber.setText(currentUser.getString("emergencyContact"));
		boolean check;
		check = pref.getBoolean("smsEnabled", true);
		smsCheckBox.setChecked(check);
		int interval;
		interval = pref.getInt("smsInterval", 10);
		smsInterval.setText(String.valueOf(interval));
		Log.d("MINE","update complete");
	}
	
	public void onShortcutPressed(View v) {
		
		//Adding shortcut for MainActivity on Home screen
	    Intent shortcutIntent = new Intent(getApplicationContext(),InitiateEmergencyActivity.class);

	    shortcutIntent.setAction(Intent.ACTION_MAIN);

	    Intent addIntent = new Intent();
	    addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
	    addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "TrackMe");
	    addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,Intent.ShortcutIconResource.fromContext(getApplicationContext(),
	                        R.drawable.ic_shortcut_emergency));
	    addIntent.putExtra("duplicate", false);

	    addIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
	    getApplicationContext().sendBroadcast(addIntent);
	}
}
