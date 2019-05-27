package com.example.movietrend;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
    TextView nameOfMovie, plotSynopsis, userRating, firstAirTime;
    ImageView imageView;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initCollapsingToolbar();

        imageView = (ImageView) findViewById(R.id.thumbnail);
        nameOfMovie = (TextView) findViewById(R.id.movietitle);
        plotSynopsis = (TextView) findViewById(R.id.synopsis);
        userRating = (TextView) findViewById(R.id.userrating);
        firstAirTime = (TextView) findViewById(R.id.releasedate);

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity.hasExtra("movie_title")){

            String thumbnail = getIntent().getExtras().getString("poster_path");
            String movieTitle = getIntent().getExtras().getString("movie_title");
            String synopsis = getIntent().getExtras().getString("movie_details");
            String rating = getIntent().getExtras().getString("vote_average");
            String dateOfRelease = getIntent().getExtras().getString("release_date");

            Glide.with(this)
                    .load(thumbnail)
                    .placeholder(R.drawable.load)
                    .into(imageView);

            nameOfMovie.setText(movieTitle);
            plotSynopsis.setText(synopsis);
            userRating.setText(rating);
            firstAirTime.setText(dateOfRelease);
        }else {
            Toast.makeText(this, "No API Data", Toast.LENGTH_SHORT).show();

        }
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

    private void initCollapsingToolbar(){
        final CollapsingToolbarLayout collapsingToolbarLayout =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
         boolean isShow = false;
         int scrollRange = -1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange==-1){
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0){
                 collapsingToolbarLayout.setTitle(getString(R.string.movie_details));
                 isShow = true;
                }else if (isShow){
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }
}
