package com.clientforgit.test_task.shibe

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ImageApi {
    @GET("/api/shibes?")
    fun getImages(@Query("count") count: Int = 10,
                @Query("urls") urls: Boolean = true,
                @Query("httpsUrls") httpsUrls: Boolean = true): Call<List<String>>
}