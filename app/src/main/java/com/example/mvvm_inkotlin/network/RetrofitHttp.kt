package com.example.mvvm_inkotlin.network

import com.example.mvvm_inkotlin.network.service.PostService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHttp {
    private val IS_TESTER = false
    private val SERVER_DEVELOPMENT = "https://jsonplaceholder.typicode.com/"
    private val SERVER_PRODUCTION = "https://626711e378638336421b08e9.mockapi.io/"

    val retrofit = Retrofit.Builder().baseUrl(server()).addConverterFactory(GsonConverterFactory.create()).build()

    private fun server(): String {
        if (IS_TESTER) return SERVER_DEVELOPMENT
        return SERVER_PRODUCTION
    }

    val postService: PostService = retrofit.create(PostService::class.java)
}