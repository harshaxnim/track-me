package com.hashkey.trackme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseUser;

public class DispatchActivity extends Activity {
	public static ParseUser currentUser = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		// Enable Local Datastore.
    	if(PostLocation.currentUser == null){
			Parse.enableLocalDatastore(this);
			Parse.initialize(this, "TrRahQfX0V4ohVGjSsVWcXwtExQnmofyHnLDAPUm", "LO65dGPtVDZzmRNa87udTW8vL0XIU54SR6uZahlw");
    	}
		currentUser = ParseUser.getCurrentUser();
		//check if a user is already logged in
		if (currentUser != null) {
		      // Start an intent for the logged in activity
			startActivity(new Intent(DispatchActivity.this,HomeActivity.class));
			Toast.makeText(getApplicationContext(), "Welcome Back "+currentUser.getString("nick")+"!", Toast.LENGTH_SHORT).show();
		    } else {
		      // Start and intent for the logged out activity
		    	startActivity(new Intent(DispatchActivity.this,WelcomeActivity.class));
		    }
	}
}
