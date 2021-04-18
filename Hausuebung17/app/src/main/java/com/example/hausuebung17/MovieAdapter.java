package com.example.hausuebung17;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends BaseAdapter {

    Context context;
    List<Movie> movies;
    String url = "http://image.tmdb.org/t/p/w154/";

    public MovieAdapter(Context context,  List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Movie getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, final View givenView, ViewGroup parent) {
        ImageView imageView;
        if(givenView == null){
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams((GridView.LayoutParams.MATCH_PARENT), GridView.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setAdjustViewBounds(true);
            imageView.setPadding(5, 5,5,5);
        } else imageView = (ImageView) givenView;

        final Movie movie = movies.get(position);
        Picasso.get().load(url + movie.poster_path).into(imageView);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailedActivity = new Intent(context, DetailedActivity.class);
                detailedActivity.putExtra("title", movie.title);
                detailedActivity.putExtra("score", movie.vote_average + "/10");
                detailedActivity.putExtra("posterPath", movie.getPoster_path());
                detailedActivity.putExtra("releaseDate", movie.release_date);
                detailedActivity.putExtra("overview", movie.overview);
                context.startActivity(detailedActivity);
            }
        });
        return imageView;
    }
}
