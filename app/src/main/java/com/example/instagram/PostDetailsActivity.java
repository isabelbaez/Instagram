package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import org.parceler.Parcels;

import java.util.Date;

public class PostDetailsActivity extends AppCompatActivity {

    Post post;

    ImageView ivPictureDetailed;
    TextView tvDescriptionDetailed;
    TextView tvTimestamp;
    Button btnBack;
    Button btnLike;
    TextView tvLikesDetailed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        ivPictureDetailed = findViewById(R.id.ivPictureDetailed);
        tvDescriptionDetailed = findViewById(R.id.tvDescriptionDetailed);
        tvTimestamp = findViewById(R.id.tvTimestamp);
        btnBack = findViewById(R.id.btnBack);
        btnLike = findViewById(R.id.btnLike);
        tvLikesDetailed = findViewById(R.id.tvLikesDetailed);

        post = (Post) Parcels.unwrap(getIntent().getParcelableExtra("post"));

        tvDescriptionDetailed.setText(post.getDescription());
        tvTimestamp.setText(calculateTimeAgo(post.getCreatedAt()));

        ParseFile image = post.getImage();
        if (image != null) {
            Glide.with(this).load(image.getUrl()).into(ivPictureDetailed);
            ivPictureDetailed.setVisibility(View.VISIBLE);
        } else {
            ivPictureDetailed.setVisibility(View.GONE);
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goMainActivity();
            }
        });

        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post.setLikes(post.getLikes() + 1);
                post.saveInBackground();
                Integer ay = post.getLikes();
                Log.i("DREAM", ay.toString());
                tvLikesDetailed.setText(ay.toString());
            }
        });

        Integer ay = post.getLikes();
        Log.i("DREAM", ay.toString());
        tvLikesDetailed.setText(ay.toString());
    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public static String calculateTimeAgo(Date createdAt) {

        int SECOND_MILLIS = 1000;
        int MINUTE_MILLIS = 60 * SECOND_MILLIS;
        int HOUR_MILLIS = 60 * MINUTE_MILLIS;
        int DAY_MILLIS = 24 * HOUR_MILLIS;

        try {
            createdAt.getTime();
            long time = createdAt.getTime();
            long now = System.currentTimeMillis();

            final long diff = now - time;
            if (diff < MINUTE_MILLIS) {
                return "just now";
            } else if (diff < 2 * MINUTE_MILLIS) {
                return "a minute ago";
            } else if (diff < 50 * MINUTE_MILLIS) {
                return diff / MINUTE_MILLIS + " m";
            } else if (diff < 90 * MINUTE_MILLIS) {
                return "an hour ago";
            } else if (diff < 24 * HOUR_MILLIS) {
                return diff / HOUR_MILLIS + " h";
            } else if (diff < 48 * HOUR_MILLIS) {
                return "yesterday";
            } else {
                return diff / DAY_MILLIS + " d";
            }
        } catch (Exception e) {
            Log.i("Error:", "getRelativeTimeAgo failed", e);
            e.printStackTrace();
        }

        return "";
    }
}