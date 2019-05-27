package com.example.movietrend.api;

import com.example.movietrend.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {

    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(@Query("api_key = b2040c23e991b52a42a0e5eb1f991e8c")String apiKey);

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key = b2040c23e991b52a42a0e5eb1f991e8c")String apiKey);
}
