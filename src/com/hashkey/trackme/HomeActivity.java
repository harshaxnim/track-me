package com.hashkey.trackme;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class HomeActivity extends Activity implements LocationListener{
	ProgressDialog dialog;
	private LocationManager locationManager;
	boolean canGetLocation = false;
	Location location;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
			return true;
		}
		if (id == R.id.about) {
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
			alertDialogBuilder.setTitle("About").setMessage("Created by Harshavardhan and Keerthana").create().show();
			return true;
		}
		if (id == R.id.logout) {
			dialog = ProgressDialog.show(this, "", "Logging out...", true);
			
			ParseUser.logOutInBackground(new LogOutCallback() {
				@Override
				public void done(ParseException e) {
					dialog.dismiss();
					if (e == null) {
				    	startActivity(new Intent(getApplicationContext(),WelcomeActivity.class));
				    	Toast.makeText(getApplicationContext(), "Logged Out!", Toast.LENGTH_SHORT).show();
				    } else {
				    	Toast.makeText(getApplicationContext(), "ERROR: "+e.getMessage(), Toast.LENGTH_LONG).show();
				    }
				}
			});
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void onEmergencyClicked(View view){
		startActivity(new Intent(getApplicationContext(), InitiateEmergencyActivity.class));
	}
	
	public void onFindClicked(View view){
		startActivity(new Intent(getApplicationContext(), Find.class));
	}

	@Override
	public void onLocationChanged(Location location) {
				
	}

	@Override
	public void onProviderDisabled(String provider) {
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		
	}
	
}
