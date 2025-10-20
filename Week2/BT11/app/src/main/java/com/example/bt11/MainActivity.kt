package com.example.bt11  // Your package name

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find buttons by their IDs
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)

        // Set click listeners (what happens when you click)
        button1.setOnClickListener {
            Toast.makeText(this, "Button 1 clicked!", Toast.LENGTH_SHORT).show()
        }

        button2.setOnClickListener {
            Toast.makeText(this, "Button 2 clicked!", Toast.LENGTH_SHORT).show()
        }

        button3.setOnClickListener {
            Toast.makeText(this, "Button 3 clicked!", Toast.LENGTH_SHORT).show()
        }

        button4.setOnClickListener {
            Toast.makeText(this, "Button 4 clicked!", Toast.LENGTH_SHORT).show()
        }
    }
}
