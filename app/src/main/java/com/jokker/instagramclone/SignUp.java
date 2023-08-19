package com.jokker.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    public EditText emailEditText, usernameEditText, passwordEditText;
    private Button signUpButton, logInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setTitle("Sign Up");

        emailEditText = findViewById(R.id.emailSignUpId);
        usernameEditText = findViewById(R.id.usernameSignUpId);
        passwordEditText = findViewById(R.id.passwordSignUpId);

        passwordEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_ENTER &&
                        event.getAction() == KeyEvent.ACTION_DOWN ) {
                    onClick(signUpButton);
                }
                return false;
            }
        });

        signUpButton = findViewById(R.id.signUpInSignUpId);
        logInButton = findViewById(R.id.loginInSignupId);

        signUpButton.setOnClickListener(this);
        logInButton.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null) {
//            ParseUser.getCurrentUser().logOut();
            transitionToSocialMediaActivity();
        }

    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        if(id == R.id.signUpInSignUpId) {

            if( emailEditText.getText().toString().equals("") ||
                usernameEditText.getText().toString().equals("") ||
                passwordEditText.getText().toString().equals("")
            ) {
                FancyToast.makeText(SignUp.this, "Email, username & password, all are required",
                        FancyToast.LENGTH_SHORT, FancyToast.INFO, true).show();
            } else {

                final ParseUser appUser = new ParseUser();
                appUser.setEmail(emailEditText.getText().toString());
                appUser.setUsername(usernameEditText.getText().toString());
                appUser.setPassword(passwordEditText.getText().toString());

                ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Signing up " + usernameEditText.getText().toString());
                progressDialog.show();

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null) {
                            FancyToast.makeText(SignUp.this, appUser.getUsername() + " is signed up",
                                    FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                            transitionToSocialMediaActivity();
                        } else {
                            FancyToast.makeText(SignUp.this, "Error when signing up",
                                    FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                        }
                        progressDialog.dismiss();
                    }
                });
            }

        } else if (id == R.id.loginInSignupId) {
            Intent loginIntent = new Intent(SignUp.this, LoginActivity.class);
            startActivity(loginIntent);
        }

    }

    public void rootLayoutTapped(View view) {

        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void transitionToSocialMediaActivity() {
        Intent socialMediaIntent = new Intent(SignUp.this, SocialMediaActivity.class);
        startActivity(socialMediaIntent);
    }


}