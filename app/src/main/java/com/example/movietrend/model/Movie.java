package com.example.movietrend.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    @SerializedName("original_name")
    private String originalName;
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("popularity")
    private Double popularity;
    @SerializedName("vote_count")
    private String voteCount;
    @SerializedName("vote_average")
    private Double voteAverage;
    @SerializedName("first_air_date")
    private String firstAirDate;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("genre_ids")
    private List<Integer> genreIds = new ArrayList<Integer>();
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("overview")
    private String overview;
    @SerializedName("origin_country")
    private String originCountry;

    public Movie(String originalName, Integer id, String name, Double popularity, String voteCount, Double voteAverage,
                 String firstAirDate, String posterPath, List<Integer> genreIds, String originalLanguage, String backdropPath,
                 String overview, String originCountry) {
        this.originalName = originalName;
        this.id = id;
        this.name = name;
        this.popularity = popularity;
        this.voteCount = voteCount;
        this.voteAverage = voteAverage;
        this.firstAirDate = firstAirDate;
        this.posterPath = posterPath;
        this.genreIds = genreIds;
        this.originalLanguage = originalLanguage;
        this.backdropPath = backdropPath;
        this.overview = overview;
        this.originCountry = originCountry;
    }
    public String getOriginalName(){
        return originalName;
    }
    public void setOriginalName (String originalName){
        this.originalName = originalName;
    }
    public Integer getId(){
        return id;
    }
    public void setId (Integer id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName (String name){
        this.name = name;
    }
    public Double getPopularity(){
        return popularity;
    }
    public void setPopularity (Double popularity){
        this.popularity = popularity;
    }
    public String getVoteCount(){
        return voteCount;
    }
    public void setVoteCount(String voteCount){
        this.voteCount = voteCount;
    }
    public Double getVoteAverage(){
        return voteAverage;
    }
    public void setVoteAverage(Double voteAverage){
        this.voteAverage = voteAverage;
    }
    public String getFirstAirDate(){
        return firstAirDate;
    }
    public void setFirstAirDate(String firstAirDate){
        this.firstAirDate = firstAirDate;
    }
    String baseImageUrl = "https://image.tmdb.org/t/p/w500";

    public String getPosterPath(){
        return "https://image.tmdb.org/t/p/w500" + posterPath;
    }
    public void setPosterPath(String posterPath){
        this.posterPath = posterPath;
    }
    public List<Integer> getGenreIds(){
        return genreIds;
    }
    public void setGenreIds(List<Integer>genreIds){
        this.genreIds = genreIds;
    }
    public String getOriginalLanguage(){
        return originalLanguage;
    }
    public void setOriginalLanguage(String originalName){
        this.originalName = originalName;
    }
    public String getBackdropPath(){
        return backdropPath;
    }
    public void setBackdropPath(String backdropPath){
        this.backdropPath = backdropPath;
    }
    public String getOverview(){
        return overview;
    }
    public void setOverview(String overview){
        this.overview = overview;
    }
    public String getOriginCountry(){
        return originCountry;
    }
    public void setOriginCountry(String originCountry){
        this.originCountry = originCountry;
    }

}
