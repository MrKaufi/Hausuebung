package com.example.hausuebung17;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ScrollView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Movie> movies = new ArrayList<>();
    JSONArray jsonArray;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = findViewById(R.id.gridView);

        try {
            loadJson();
            loadMovies();
            loadMoviesintoGridView();
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    private void loadJson() throws IOException, JSONException {
        String content = "";

        AssetManager assetManager = getAssets();
        InputStream inputStream = assetManager.open("movies.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String currentLine;
        while ((currentLine = reader.readLine()) != null){
            content += currentLine;
        }

        JSONObject jsonObject = new JSONObject(content);
        jsonArray = jsonObject.getJSONArray("results");
    }

    private void loadMovies() throws JSONException {
        Movie movie;
        JSONObject jsonObject;

        for (int i = 0; i < jsonArray.length(); i++) {
            jsonObject = jsonArray.getJSONObject(i);
            ArrayList<Integer> movieIds = new ArrayList<>();
            JSONArray movieIdsJson = jsonObject.getJSONArray("genre_ids");
            for (int j = 0; j < movieIdsJson.length(); j++) {
                movieIds.add(movieIdsJson.getInt(j));
            }
            int[] movieIdsArray = new int[movieIds.size()];
            for (int j = 0; j < movieIdsArray.length; j++) {
                movieIdsArray[j] = movieIds.get(j);
            }
            movie = new Movie(jsonObject.getInt("vote_count"),jsonObject.getInt("id"), jsonObject.getBoolean("video"),jsonObject.getDouble("vote_average"),jsonObject.getString("title"),jsonObject.getDouble("popularity"),jsonObject.getString("poster_path"),jsonObject.getString("original_language"),jsonObject.getString("original_title"), movieIdsArray, jsonObject.getString("backdrop_path"),jsonObject.getBoolean("adult"),jsonObject.getString("overview"),jsonObject.getString("release_date"));
            movies.add(movie);
        }
    }

    private void loadMoviesintoGridView(){
        gridView.setAdapter(new MovieAdapter(this, movies));
    }
}