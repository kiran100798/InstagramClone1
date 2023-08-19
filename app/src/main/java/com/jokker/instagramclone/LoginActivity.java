package com.jokker.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText emailEditText, passwordEditText;
    private Button loginButton, signupButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Log In");

        emailEditText = findViewById(R.id.emailLoginId);
        passwordEditText = findViewById(R.id.passwordLoginId);

        signupButton = findViewById(R.id.signUpInLogInId);
        loginButton = findViewById(R.id.loginInLogInId);

        loginButton.setOnClickListener(this);
        signupButton.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
        }

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if(id == R.id.loginInLogInId) {
            ParseUser.logInInBackground(
                    emailEditText.getText().toString(),
                    passwordEditText.getText().toString(),
                    new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if( user != null && e == null ) {
                                FancyToast.makeText(LoginActivity.this, ParseUser.getCurrentUser().getUsername().toString() + " is logged in",
                                        FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                                transitionToSocialMediaActivity();
                            } else {
                                FancyToast.makeText(LoginActivity.this, "Error when logging in",
                                        FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                            }
                        }
                    });

        } else if (id == R.id.signUpInLogInId) {
            this.finish();
        }

    }

    private void transitionToSocialMediaActivity() {
        Intent socialMediaIntent = new Intent(LoginActivity.this, SocialMediaActivity.class);
        startActivity(socialMediaIntent);
    }
}