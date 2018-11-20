package com.news.app.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.news.app.R;
import com.news.app.databse.DatabaseHelper;
import com.news.app.model.User;
import com.news.app.utils.Prefs;

public class UserDetailsActivity extends AppCompatActivity {

    TextView userName, userEmail, userAdd;
    DatabaseHelper databaseHelper;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        userName = findViewById(R.id.userName);
        userEmail = findViewById(R.id.userEmail);
        userAdd = findViewById(R.id.userAdd);

        databaseHelper = new DatabaseHelper(this);
        user = new User();

        user = databaseHelper.getUser(Prefs.getUserId(this));

        String user_name = user.getFirstName() + " " + user.getLastName();
        userName.setText(user_name);
        userEmail.setText(user.getEmail());
        userAdd.setText(user.getAddress());


    }

    public void doEdit(View view) {

        startActivity(new Intent(UserDetailsActivity.this, UpdateUserActivity.class));
        UserDetailsActivity.this.finish();

    }
}
