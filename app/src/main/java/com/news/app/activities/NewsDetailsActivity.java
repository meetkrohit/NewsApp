package com.news.app.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.news.app.R;
import com.squareup.picasso.Picasso;

public class NewsDetailsActivity extends AppCompatActivity {

    TextView newTitle;
    ImageView newsImage;

    LinearLayout back_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        String new_title = getIntent().getExtras().getString("newsTitle");
        String news_image = getIntent().getExtras().getString("newsImage");

        newTitle = findViewById(R.id.descriptionNews);
        newsImage = findViewById(R.id.imageNews);
        back_ = findViewById(R.id.back_);

        newTitle.setText(new_title);
        Picasso.with(this).load(news_image.replace(" ", "%20")).placeholder(R.drawable.newsicon).into(newsImage);

        back_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewsDetailsActivity.this.finish();
            }
        });
    }
}
