package com.example.harrypotterbooks

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //list cac sach
        val listBook = mutableListOf<BookModel>()
        listBook.add(BookModel(R.drawable.harrypotter1,"","Harry Potter - tập 1"))
        listBook.add(BookModel(R.drawable.harrypotter2, "", "Harry Potter - tập 2"))
        listBook.add(BookModel(R.drawable.harrypotter3, "", "Harry Potter - tập 3"))
        listBook.add(BookModel(R.drawable.harrypotter4, "", "Harry Potter - tập 4"))
        listBook.add(BookModel(R.drawable.harrypotter5, "", "Harry Potter - tập 5"))
        listBook.add(BookModel(R.drawable.harrypotter6, "", "Harry Potter - tập 6"))
        listBook.add(BookModel(R.drawable.harrypotter7, "", "Harry Potter - tập 7"))
        listBook.add(BookModel(R.drawable.harrypotter8, "", "Harry Potter - tập 8"))

        //Gắn adapter
        val lvBook = findViewById<ListView>(R.id.lvBook)
        lvBook.adapter = BookListView(this, listBook)

        //Click event
            lvBook.setOnItemClickListener { parent, view, position, id ->
                val bundle = Bundle()
                bundle.putInt("imgBook", listBook[position].imgBook)
                bundle.putString("txtBookName", listBook[position].txtBookName)
                bundle.putString("urlBook", listBook[position].urlBook)

                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("bookData", bundle)
                startActivity(intent)
        }
    }
}

