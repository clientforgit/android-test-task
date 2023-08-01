package com.clientforgit.test_task.shibe

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface NicknameApi {
    @Headers("X-Api-Key: 65e91f1b3143420f871a8576f4899c8b")
    @GET("/api/Name?")
    fun getNickname(@Query("nameType") category: String = "firstname", @Query("quantity") limit: Int = 10): Call<List<String>>
}