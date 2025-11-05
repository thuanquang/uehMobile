package com.example.bai3

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)

        val bundle = intent.getBundleExtra("dogData")

        if (bundle != null){
            val imgDog = bundle.getInt("imgDog")
            val dogName = bundle.getString("dogName")

            val imgDogView = findViewById<ImageView>(R.id.imgDog)
            val txtDogNameView = findViewById<TextView>(R.id.txtDogName)

            if (imgDog !=0){
                imgDogView.setImageResource(imgDog)
            }

            txtDogNameView.text = dogName
        }
    }
}