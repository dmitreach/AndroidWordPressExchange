package com.example.androidwordpressexchange.api

import com.example.androidwordpressexchange.model.ResultDataApi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(".")
    // such variant for url without parameters
    // https://test.dmitry-usov.ru/wp-json/wp/v2/posts/

    // https://newsapi.org/v2/everything?q=bitcoin&apiKey=cf846f15f76c468988247ad22fae21cc
    // @GET("everything")
    // https://stackoverflow.com/questions/31962817/retrofit-get-without-a-value-in-android

    fun request(
        //@Query("apiKey") apiKey: String,
        //@Query("q") query: String

    ) : Call<List<ResultDataApi>>
    //) : Call<ResultDataApi>
    //) : Call<List<ImageResultData>>
}