package com.ag.imitroid.rest;

import com.ag.imitroid.models.Articles;

import retrofit2.Call;
import retrofit2.http.GET;

public interface api {

    @GET("api/articles")
    Call<Articles> loadArticles();
}
