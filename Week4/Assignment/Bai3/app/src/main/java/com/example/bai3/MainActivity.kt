package com.example.bai3

import android.os.Bundle
import android.widget.GridView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val listDog = mutableListOf<DogModel>()
        listDog.add(DogModel(R.drawable.caobang, "Con chó cao bằng bộ PC"))
        listDog.add(DogModel(R.drawable.brian, "Con chó Brian cao bằng bộ PC"))
        listDog.add(DogModel(R.drawable.courage, "Con chó Courage cao bằng bộ PC"))


        val gvDog = findViewById<GridView>(R.id.gvDogs)
        gvDog.adapter = DogGridView(this, listDog)

        gvDog.setOnItemClickListener { parent, view, position, id ->
            val bundle = Bundle()
            bundle.putInt("imgDog", listDog[position].imgDog)
            bundle.putString("dogName", listDog[position].dogName)

            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("dogData", bundle)
            startActivity(intent)
        }
    }
}