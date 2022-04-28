package com.example.mvvm_inkotlin.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_inkotlin.R
import com.example.mvvm_inkotlin.activity.MainActivity
import com.example.mvvm_inkotlin.model.Post
import com.example.mvvm_inkotlin.utils.Utils
import kotlinx.android.synthetic.main.alert_dialog_background.view.*
import kotlinx.android.synthetic.main.item_poster_list.view.*
import kotlinx.android.synthetic.main.item_poster_list.view.tv_title

class PostAdapter(var activity: MainActivity, var items: ArrayList<Post>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_poster_list, parent, false)
        return PosterViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val post: Post = items[position]
        if (holder is PosterViewHolder) {
            holder.view.tv_title.setText(post.title.toUpperCase())
            holder.view.tv_body.setText(post.body)

            holder.view.ll_poster.setOnLongClickListener {
                deletePostDialog(post)
                false
            }

            holder.view.ll_poster.setOnClickListener {
                updatePostDialog(post)
            }
        }
    }

    inner class PosterViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

    }

    fun deletePostDialog(post: Post) {
        val title = "Delete"
        val body = "Do you want to delete?"
        Utils.customDialog(activity, title, body, object : Utils.DialogListener {
            override fun onPositiveClick() {
                activity.viewModel.apiPostDelete(post)
                activity.viewModel.deletePost.observe(activity, {
                    activity.viewModel.apiPostList()
                })
            }

            override fun onNegativeClick() {

            }
        })
    }

    fun updatePostDialog(post: Post) {
        val title = "Update Poster"
        val body = "Do you want to update?"
        val inflate = R.layout.alert_dialog_background
        val view = View.inflate(activity, inflate,null)
        Utils.customDialogUpdate(activity,view, title, body,inflate, object : Utils.DialogListener {
            @SuppressLint("StaticFieldLeak")
            override fun onPositiveClick() {
                var user = Post(post.id,post.userId,view.et_title.text.toString(),view.et_body.text.toString())
                activity.viewModel.apiPostUpdates(user)
                activity.viewModel.apiPostUpdate.observe(activity, {
                    activity.viewModel.apiPostList()
                })
            }

            override fun onNegativeClick() {

            }
        })
    }

}