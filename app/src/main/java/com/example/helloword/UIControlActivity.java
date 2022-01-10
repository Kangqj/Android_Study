package com.example.helloword;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class UIControlActivity extends AppCompatActivity {

    private static final String LARGE_BASE_URL = "https://storage.googleapis.com/androiddevelopers/sample_data/activity_transition/large/flying_in_the_light.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uicontrol);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        setTitle(message);
    }

    public void loadImage(View view) {
        ImageView image = (ImageView) findViewById(R.id.imageView2);
        Picasso.with(image.getContext()).load(LARGE_BASE_URL).into(image);
    }
}