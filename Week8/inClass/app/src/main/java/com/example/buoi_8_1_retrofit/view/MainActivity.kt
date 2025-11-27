package com.example.buoi_8_1_retrofit.view

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.buoi_8_1_retrofit.R
import com.example.buoi_8_1_retrofit.repository.APIPostRepository
import com.example.buoi_8_1_retrofit.viewmodel.PostViewModel
import com.example.buoi_8_1_retrofit.viewmodel.PostViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModelFactory = PostViewModelFactory(APIPostRepository())
        viewModel = ViewModelProvider(this, viewModelFactory).get(PostViewModel::class.java)

        val txtThongBao = findViewById<TextView>(R.id.txtThongBao)
        val listView = findViewById<ListView>(R.id.listViewPosts)

        // Get single post item
        viewModel.getPostItem()
        viewModel.responseValue.observe(this) { response ->
            if (response.isSuccessful) {
                val postItem = response.body()
                if (postItem != null) {
                    val id = postItem.id
                    val title = postItem.title
                    Log.d("MainActivity", "Thành công rồi...UserID = " + postItem.userId + "\n" + id.toString() + "\n" + title)
                    Toast.makeText(this, "Tùng - userID = " + id + "\n" + title, Toast.LENGTH_LONG).show()
                    txtThongBao.text = "Tùng" + id + "\n" + title
                    txtThongBao.textSize = 20F
                    txtThongBao.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                }
            } else {
                Log.d("MainActivity", "Thất bại rồi! :((")
            }
        }

        // Get all posts and display in ListView
        viewModel.getPosts()
        viewModel.postsResponse.observe(this) { response ->
            if (response.isSuccessful) {
                val posts = response.body()
                if (posts != null) {
                    val postTitles = posts.map { it.title }
                    val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, postTitles)
                    listView.adapter = adapter
                }
            } else {
                Log.d("MainActivity", "Failed to get posts")
            }
        }
    }
}


