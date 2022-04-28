package com.example.mvvm_inkotlin.presenter

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm_inkotlin.model.Post
import com.example.mvvm_inkotlin.network.RetrofitHttp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel  : ViewModel() {
    val allPosts = MutableLiveData<ArrayList<Post>>()
    val deletePost = MutableLiveData<Post>()
    val apiPostUpdate = MutableLiveData<Post>()

    fun apiPostList(){
        RetrofitHttp.postService.listPost().enqueue(object  : Callback<ArrayList<Post>> {
            override fun onResponse(
                call: Call<ArrayList<Post>>,
                response: Response<ArrayList<Post>>,
            ) {
                allPosts.value = response.body()
            }

            override fun onFailure(call: Call<ArrayList<Post>>, t: Throwable) {
                allPosts.value = null
            }

        })
    }

    fun apiPostDelete(post: Post) {
        RetrofitHttp.postService.deletePost(post.id).enqueue(object  : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                deletePost.value = response.body()
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                deletePost.value = null
            }

        })

    }

    fun apiPostUpdates(post: Post) {
        RetrofitHttp.postService.updatePost(post.id,data = post).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                apiPostUpdate.value = response.body()
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                apiPostUpdate.value = null
            }

        })
    }
}