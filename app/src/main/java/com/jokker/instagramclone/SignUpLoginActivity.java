package com.jokker.instagramclone;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLoginActivity extends AppCompatActivity {

    private EditText loginUsername, loginPassword, signUpUsername, signUpPassword;
    private Button loginButton, signUpButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);

        loginUsername = findViewById(R.id.usernameLoginId);
        loginPassword = findViewById(R.id.passwordLoginId);
        loginButton = findViewById(R.id.loginButtonId);

        signUpUsername = findViewById(R.id.usernameSignUpId);
        signUpPassword = findViewById(R.id.passwordSignUpId);
        signUpButton = findViewById(R.id.signUpButtonId);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ParseUser parseUser = new ParseUser();
                parseUser.setUsername(signUpUsername.getText().toString());
                parseUser.setPassword(signUpPassword.getText().toString());
                parseUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            FancyToast.makeText(SignUpLoginActivity.this,parseUser.getUsername() + " is Signed Up",FancyToast.LENGTH_LONG, FancyToast.SUCCESS,true).show();
                        } else {
                            FancyToast.makeText(SignUpLoginActivity.this,"Couldn't Sign in... " + parseUser.getUsername() + " \nReason : " + e,FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                            Log.i("SIGNUP_FAILED", "SIGNUP_FAILED : " + e );
                        }
                    }
                });

            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser.logInInBackground(loginUsername.getText().toString(), loginPassword.getText().toString(),
                        new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {
                                if ( user != null && e == null) {
                                    FancyToast.makeText(SignUpLoginActivity.this,user.getUsername() + " is Logged In",FancyToast.LENGTH_LONG, FancyToast.SUCCESS,true).show();
                                } else {
                                    Log.i("LOGIN_FAILED", "LOGIN_FAILED : " + e.getMessage() );
                                    FancyToast.makeText(SignUpLoginActivity.this,"Couldn't Log in... " + " \nReason : " + e,FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                                }
                            }
                        });

            }
        });

    }
}
