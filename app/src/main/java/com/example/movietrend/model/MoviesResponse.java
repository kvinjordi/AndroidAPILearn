package com.example.movietrend.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesResponse {
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<Movie> results;
    @SerializedName("total_results")
    private int totalresult;
    @SerializedName("total_pages")
    private int totalpages;

    public int getPage(){
        return page;
    }
    public void setPage(int page){
        this.page = page;
    }
    public List<Movie> getResults(){
        return results;
    }
    public void setResults(List<Movie> results){
        this.results = results;
    }
    public int getTotalpages(){
        return totalpages;
    }
    public void setTotalpages(int totalpages){
        this.totalpages = totalpages;
    }
    public int getTotalresult(){
        return totalresult;
    }
    public void setTotalresult(int totalresult){
        this.totalresult = totalresult;
    }

}
