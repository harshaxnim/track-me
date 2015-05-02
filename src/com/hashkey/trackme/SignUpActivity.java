package com.hashkey.trackme;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends Activity {

	private EditText firstNameEditText;
	private EditText lastNameEditText;
	private EditText nickEditText;
	private EditText usernameEditText;
	private EditText passwordEditText;
	private EditText passwordAgainEditText;
	private EditText emergencyContactEditText;
	private SharedPreferences pref = null;
	
	ProgressDialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_sign_up);
		
		firstNameEditText = (EditText) findViewById(R.id.first_name_edit_text);
		lastNameEditText = (EditText) findViewById(R.id.last_name_edit_text);
		nickEditText = (EditText) findViewById(R.id.nick_edit_text);
		usernameEditText = (EditText) findViewById(R.id.username_edit_text);
	    passwordEditText = (EditText) findViewById(R.id.password_edit_text);
	    passwordAgainEditText = (EditText) findViewById(R.id.password_again_edit_text);
	    emergencyContactEditText = (EditText) findViewById(R.id.emergency_phone_edit_text);
	    pref = PreferenceManager.getDefaultSharedPreferences(this);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_up, menu);
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
	
	public void signUp(View view){
		String firstName = firstNameEditText.getText().toString().trim();
		String lastName = lastNameEditText.getText().toString().trim();
		final String nick = nickEditText.getText().toString().trim();
		String username = usernameEditText.getText().toString().trim();
	    String password = passwordEditText.getText().toString().trim();
	    String passwordAgain = passwordAgainEditText.getText().toString().trim();
	    String emergencyContact = emergencyContactEditText.getText().toString().trim();
	    
		
	    boolean validationError = false;
	    StringBuilder validationErrorMessage = new StringBuilder(getString(R.string.error_intro));
	    if (username.length() == 0) {
	      validationError = true;
	      validationErrorMessage.append(getString(R.string.error_blank_username));
	    }
	    if (emergencyContact.length() == 0) {
		      validationError = true;
		      validationErrorMessage.append(getString(R.string.error_blank_emergency));
		    }
	    if (password.length() == 0) {
	      if (validationError) {
	        validationErrorMessage.append(getString(R.string.error_join));
	      }
	      validationError = true;
	      validationErrorMessage.append(getString(R.string.error_blank_password));
	    }
	    if (!password.equals(passwordAgain)) {
	      if (validationError) {
	        validationErrorMessage.append(getString(R.string.error_join));
	      }
	      validationError = true;
	      validationErrorMessage.append(getString(R.string.error_mismatched_passwords));
	    }
	    validationErrorMessage.append(getString(R.string.error_end));

	    // If there is a validation error, display the error
	    if (validationError) {
	      Toast.makeText(SignUpActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG).show();
	      return;
	    }
	    
	    
	    
		ParseUser user = new ParseUser();
		user.setUsername(username);
		user.setPassword(password);
		user.put("firstName", firstName);
		user.put("lastName", lastName);
		user.put("nick", nick);
		user.put("emergencyContact", emergencyContact);
		
		dialog = ProgressDialog.show(this, "", "Signing you up...", true);
		 
		user.signUpInBackground(new SignUpCallback() {
		  public void done(ParseException e) {
			dialog.dismiss();
		    if (e == null) {
		    	Toast.makeText(getApplicationContext(), "Welcome "+nick, Toast.LENGTH_SHORT).show();
		    	startActivity(new Intent(getApplicationContext(),HomeActivity.class));
		    } else {
		    	Toast.makeText(getApplicationContext(), "ERROR: "+e.getMessage(), Toast.LENGTH_LONG).show();
		    }
		  }
		});
	
		//loacalDatastore
		pref.edit().putBoolean("smsEnabled", true).apply();
		pref.edit().putInt("smsInterval", 10).apply();
		pref.edit().putString("emergencyContact", emergencyContact).apply();
	}
}
