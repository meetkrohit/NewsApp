package com.news.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.news.app.R;
import com.news.app.databse.DatabaseHelper;
import com.news.app.model.User;
import com.news.app.utils.Prefs;

public class UpdateUserActivity extends AppCompatActivity {

    EditText firstName, lastName, emailId, addr;
    User user;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        emailId = findViewById(R.id.emailId);
        addr = findViewById(R.id.addr);

        user = new User();
        databaseHelper = new DatabaseHelper(this);

        user = databaseHelper.getUser(Prefs.getUserId(this));

        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        emailId.setText(user.getEmail());
        addr.setText(user.getAddress());

    }

    public void doSubmit(View view) {

        if (!firstName.getText().toString().equals("") || !firstName.getText().toString().isEmpty()) {
            if (!lastName.getText().toString().equals("") || !lastName.getText().toString().isEmpty()) {
                if (!emailId.getText().toString().equals("") || !emailId.getText().toString().isEmpty()) {
                    if (!addr.getText().toString().equals("") || !addr.getText().toString().isEmpty()) {
                        if (isValidEmail(emailId.getText().toString())) {

                            user.setFirstName(firstName.getText().toString().trim());
                            user.setLastName(lastName.getText().toString().trim());
                            user.setEmail(emailId.getText().toString().trim());
                            user.setAddress(addr.getText().toString().trim());

                            databaseHelper.updateUser(user);
                            startActivity(new Intent(UpdateUserActivity.this, UserDetailsActivity.class));
                            UpdateUserActivity.this.finish();

                        } else {
                            Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(this, "Please enter Address", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(this, "Please enter Email Id", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Please enter Last Name", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please enter First Name", Toast.LENGTH_SHORT).show();
        }

    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(UpdateUserActivity.this, UserDetailsActivity.class));
        UpdateUserActivity.this.finish();
    }
}
