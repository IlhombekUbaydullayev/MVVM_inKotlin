package com.example.mvvm_inkotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mvvm_inkotlin.R
import com.example.mvvm_inkotlin.adapter.PostAdapter
import com.example.mvvm_inkotlin.model.Post
import com.example.mvvm_inkotlin.presenter.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        recyclerView.setLayoutManager(GridLayoutManager(this, 1))

        viewModel.apiPostList()
        viewModel.allPosts.observe(this, {
            refreshAdapter(it)
        })
    }

    fun refreshAdapter(posters: ArrayList<Post>) {
        val adapter = PostAdapter(this, posters)
        recyclerView.setAdapter(adapter)
    }
}