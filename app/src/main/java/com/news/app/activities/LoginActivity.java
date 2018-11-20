package com.news.app.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.news.app.R;
import com.news.app.databse.DatabaseHelper;
import com.news.app.utils.Prefs;

public class LoginActivity extends AppCompatActivity {

    EditText emailId, pwd;
    DatabaseHelper databaseHelper;
    TextView registerUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailId = findViewById(R.id.emailId);
        pwd = findViewById(R.id.pwd);
        registerUser = findViewById(R.id.registerUser);

        databaseHelper = new DatabaseHelper(this);

        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
                LoginActivity.this.finish();
            }
        });

    }


    public void doLogin(View view) {

        if (!emailId.getText().toString().equals("") || !emailId.getText().toString().isEmpty()) {
            if (!pwd.getText().toString().equals("") || !pwd.getText().toString().isEmpty()) {
                if (isValidEmail(emailId.getText().toString())){
                    if (databaseHelper.checkUser(emailId.getText().toString().trim()
                            , pwd.getText().toString().trim())) {

                        String email = databaseHelper.getUserId(emailId.getText().toString().trim());
                        Prefs.setUserId(this,email);
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        LoginActivity.this.finish();
                    } else {
                        Toast.makeText(this, "Incorrect Email Id or Password!",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Please enter a valid email",Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Enter your password",Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Enter your mail id",Toast.LENGTH_SHORT).show();
        }

    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}
