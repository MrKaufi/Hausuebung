package com.example.hausuebung17;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class DetailedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_activity);

        TextView titel = findViewById(R.id.titel);
        TextView score = findViewById(R.id.score);
        ImageView imageView = findViewById(R.id.filmPoster);
        TextView releaseDate = findViewById(R.id.releaseDate);
        TextView overview = findViewById(R.id.overview);
        String url = "http://image.tmdb.org/t/p/w154/";

        Intent intent = getIntent();

        titel.setText(intent.getStringExtra("titel"));
        score.setText(intent.getStringExtra("score"));
        Picasso.get().load(url + intent.getStringExtra("posterPath")).into(imageView);
        releaseDate.setText(intent.getStringExtra("releaseDate"));
        overview.setText(intent.getStringExtra("overview"));
    }
}
