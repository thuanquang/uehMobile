package com.example.harrypotterbooks

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)

        //Nhan du lieu tu Intent + Bundle
        val bundle = intent.getBundleExtra("bookData")

        if (bundle != null) {
            val imgBook = bundle.getInt("imgBook")
            val txtBookName = bundle.getString("txtBookName")
            val urlBook = bundle.getString("urlBook")

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
}
