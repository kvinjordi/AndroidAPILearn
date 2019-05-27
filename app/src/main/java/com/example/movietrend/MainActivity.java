    package com.example.movietrend;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.movietrend.adapter.MoviesAdapter;
import com.example.movietrend.api.Client;
import com.example.movietrend.api.Service;
import com.example.movietrend.model.Movie;
import com.example.movietrend.model.MoviesResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

    public class MainActivity extends AppCompatActivity {

        private RecyclerView recyclerView;
        private MoviesAdapter adapter;
        private List<Movie> movieList;
        ProgressDialog progressDialog;
        private SwipeRefreshLayout swipeContainer;
        public static final String LOG_TAG = MoviesAdapter.class.getName();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            initViews();

            swipeContainer = (SwipeRefreshLayout) findViewById(R.id.main_content);
            swipeContainer.setColorSchemeResources(android.R.color.holo_orange_dark);
            swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    initViews();
                    Toast.makeText(MainActivity.this, "Movies Refreshed", Toast.LENGTH_SHORT).show();
                }
            });
        }

        public Activity getActivity() {
            Context context = this;
            while (context instanceof ContextWrapper) {
                if (context instanceof Activity) {
                    return (Activity) context;

                }
                context = ((ContextWrapper) context).getBaseContext();
            }
            return null;
        }

        private void initViews() {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Fetching Movies...");
            progressDialog.setCancelable(false);
            progressDialog.show();

            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

            movieList = new ArrayList<>();
            adapter = new MoviesAdapter(getApplicationContext(), this.movieList);

            if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
            }
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            loadJSON();
        }

        private void loadJSON() {

            try {
                if (BuildConfig.THE_MOVIE_DB_API_TOKEN.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please obtain API Key First from themoviedb.org", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                    return;
                }
                Client Client = new Client();
                Service apiservice =
                        Client.getClient().create(Service.class);
                Call<MoviesResponse> call = apiservice.getTopRatedMovies(BuildConfig.THE_MOVIE_DB_API_TOKEN);
                call.enqueue(new Callback<MoviesResponse>() {
                    @Override
                    public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                        List<Movie> movies;
                        movies = response.body().getResults();
                        Log.d("Data", String.valueOf(movies));
                        recyclerView.setAdapter(new MoviesAdapter(getApplicationContext(), movies));
                        recyclerView.smoothScrollToPosition(0);
                        if (swipeContainer.isRefreshing()) {
                            swipeContainer.setRefreshing(false);
                        }
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<MoviesResponse> call, Throwable t) {
                        Log.d("Error", t.getMessage());
                        Toast.makeText(MainActivity.this, "Error Fetching Data!", Toast.LENGTH_SHORT).show();
                    }


                });
            } catch (Exception e) {
                Log.d("Error", e.getMessage());
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu){
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }
        @Override
        public boolean onOptionsItemSelected(MenuItem item){
            switch (item.getItemId()){
                case R.id.menu_settings:
                    return true;
                    default:
                        return super.onOptionsItemSelected(item);
            }
        }
    }

