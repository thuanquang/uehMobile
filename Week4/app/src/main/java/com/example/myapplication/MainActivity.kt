package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    
    private val TAG = "MainActivity"
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate() called")
        
        //List các Sách
        val listBook = mutableListOf<BookModel>()
        listBook.add(BookModel(0, "", "Harry Potter - hình Internet"))
        listBook.add(BookModel(R.drawable.harrypotter1, "", "Harry Potter - tập 1"))
        listBook.add(BookModel(R.drawable.harrypotter2, "", "Harry Potter - tập 2"))
        listBook.add(BookModel(R.drawable.harrypotter3, "", "Harry Potter - tập 3"))
        listBook.add(BookModel(R.drawable.harrypotter4, "", "Harry Potter - tập 4"))
        listBook.add(BookModel(R.drawable.harrypotter5, "", "Harry Potter - tập 5"))
        listBook.add(BookModel(R.drawable.harrypotter6, "", "Harry Potter - tập 6"))
        listBook.add(BookModel(R.drawable.harrypotter7, "", "Harry Potter - tập 7"))
        
        //Gán Adapter cho GridView
        val gvBook = findViewById<GridView>(R.id.gvBook)
        gvBook.adapter = BookGridView(this, listBook)
        
        //Xử lý sự kiện click item trong GridView
        gvBook.setOnItemClickListener { parent, view, position, id ->
            val bundle = Bundle()
            bundle.putInt("imgBook", listBook[position].imgBook)
            bundle.putString("txtBookName", listBook[position].txtBookName)
            bundle.putString("urlBook", listBook[position].urlBook)
            
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("bookData", bundle)
            startActivity(intent)
            Log.d(TAG, "Item clicked at position: $position")
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