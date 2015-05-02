package com.hashkey.trackme;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class WelcomeActivity extends Activity {

	private EditText username_edit_text;
	private EditText password_edit_text;
	ProgressDialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_welcome);
		username_edit_text = (EditText) findViewById(R.id.username_login_edit_text);
		password_edit_text = (EditText) findViewById(R.id.password_login_edit_text);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.welcome, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void logIn(View view){
		String username = username_edit_text.getText().toString().trim();
		String password = password_edit_text.getText().toString().trim();
		
		boolean validationError = false;
	    StringBuilder validationErrorMessage = new StringBuilder(getString(R.string.error_intro));
	    if (username.length() == 0) {
	      validationError = true;
	      validationErrorMessage.append(getString(R.string.error_blank_username));
	    }
	    if (password.length() == 0) {
	      if (validationError) {
	        validationErrorMessage.append(getString(R.string.error_join));
	      }
	      validationError = true;
	      validationErrorMessage.append(getString(R.string.error_blank_password));
	    }
	    validationErrorMessage.append(getString(R.string.error_end));
	    // If there is a validation error, display the error
	    if (validationError) {
	      Toast.makeText(getApplicationContext(), validationErrorMessage.toString(), Toast.LENGTH_LONG).show();
	      return;
	    }
	    
	    dialog = ProgressDialog.show(this, "", "Logging you in...", true);
	    
	    ParseUser.logInInBackground(username, password, new LogInCallback() {
	    	  public void done(ParseUser user, ParseException e) {
	    		dialog.dismiss();
	    	    if (user != null) {
	    	      Toast.makeText(getApplicationContext(), "Welcome back "+user.getString("nick")+"!", Toast.LENGTH_SHORT).show();
	    	      startActivity(new Intent(WelcomeActivity.this,HomeActivity.class));
	    	    } else {
	    	    	Toast.makeText(getApplicationContext(), "ERROR: "+e.getMessage(), Toast.LENGTH_SHORT).show();
	    	    }
	    	  }
	    	});
	}
	
	public void signUp(View view){
		startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
	}
}