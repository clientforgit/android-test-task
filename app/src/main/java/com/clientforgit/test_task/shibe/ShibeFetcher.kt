package com.clientforgit.test_task.shibe

import android.content.Context
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random

class ShibeFetcher(private var context: Context) {
    private var shibesList = emptyList<Shibe>()

    private var imageRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://shibe.online")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var imageApi: ImageApi = imageRetrofit.create(ImageApi::class.java)

    private var nicknameRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://randommer.io")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var nicknameApi: NicknameApi = nicknameRetrofit.create(NicknameApi::class.java)

    private var imageList =  emptyList<String>()
    private var nicknameList = emptyList<String>()

    fun fetchList(passToAdapter: (List<Shibe>) -> Unit) {
        nicknameApi.getNickname().enqueue(object : Callback<List<String>> {
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                nicknameList = response.body()!!
                createShibes(passToAdapter)
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Toast.makeText(context, "Failed to load nicknames", Toast.LENGTH_SHORT).show()
            }
        })
        imageApi.getImages().enqueue(object : Callback<List<String>> {
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                imageList = response.body()!!
                createShibes(passToAdapter)
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Toast.makeText(context, "Failed to load images", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun createShibes(passToAdapter: (List<Shibe>) -> Unit) {
        if (nicknameList.isNotEmpty() and imageList.isNotEmpty()) {
            val result = mutableListOf<Shibe>()
            for (i in 0..9) {
                result.add(
                    Shibe(
                        nicknameList[i],
                        imageList[i],
                        Random.nextInt(1, 20)
                    )
                )
            }
            shibesList = result.toList()
            imageList = emptyList()
            nicknameList = emptyList()
            passToAdapter(result.toList())
        }
    }
}