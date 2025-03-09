package com.example.minijeux.model;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.minijeux.R;

import androidx.appcompat.app.AppCompatActivity;

public class GameDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamedetail);

        ImageView gameImage = findViewById(R.id.gameDetailImage);
        TextView gameTitle = findViewById(R.id.gameDetailTitle);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        int imageResId = intent.getIntExtra("imageResId", 0);

        gameTitle.setText(title);
        gameImage.setImageResource(imageResId);
    }
}
