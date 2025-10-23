package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    
    private val TAG = "DetailActivity"
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        Log.d(TAG, "onCreate() called")
        
        // Nhận dữ liệu từ Intent + Bundle
        val bundle = intent.getBundleExtra("bookData")
        
        if (bundle != null) {
            val imgBook = bundle.getInt("imgBook")
            val txtBookName = bundle.getString("txtBookName")
            val urlBook = bundle.getString("urlBook")
            
            Log.d(TAG, "Received book: $txtBookName")
            
            val imgBookView = findViewById<ImageView>(R.id.imgBook)
            val txtBookNameView = findViewById<TextView>(R.id.txtBookName)
            
            // Hiển thị ảnh
            if (imgBook != 0) {
                imgBookView.setImageResource(imgBook)
            } else {
                Glide.with(this).load(urlBook).into(imgBookView)
            }
            
            // Hiển thị tên sách
            txtBookNameView.text = txtBookName
        }
    }
    
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }
    
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }
    
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }
    
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }
    
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }
}
