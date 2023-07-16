package com.jokker.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private EditText name_editText, age_editText, college_editText;
    private TextView exception_textView;
    private Button addToDbButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name_editText = findViewById(R.id.name_editTextId);
        age_editText = findViewById(R.id.age_editTextId);
        college_editText = findViewById(R.id.college_editTextId);

        exception_textView = findViewById(R.id.exceptionTextViewId);

        addToDbButton = findViewById(R.id.addButtonId);
        addToDbButton.setOnClickListener(SignUp.this);

    }

    @Override
    public void onClick(View v) {

        try {
            ParseObject student = new ParseObject("Kiran");
            student.put("name", name_editText.getText().toString());
            student.put("age", Integer.parseInt(age_editText.getText().toString()));
            student.put("college", college_editText.getText().toString());

            student.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if( e == null ) {
                        
//                        Toast.makeText(SignUp.this, "object is saved successfully", Toast.LENGTH_SHORT).show();
                        FancyToast.makeText(SignUp.this,student.get("name") + " is added",FancyToast.LENGTH_LONG, FancyToast.SUCCESS,true).show();
                        exception_textView.setText("Success");
                    } else {
//                        Toast.makeText(SignUp.this, "ParseException : " + e, Toast.LENGTH_SHORT).show();
                        exception_textView.setText("ParseException : " + e);
                        FancyToast.makeText(SignUp.this,"Hello World !",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                    }
                }
            });
        } catch (Exception e) {
            Toast.makeText(SignUp.this, "exception : " + e, Toast.LENGTH_SHORT).show();
            FancyToast.makeText(SignUp.this,"Hello World !",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
            exception_textView.setText("Exception : " + e);
        }

    }
}