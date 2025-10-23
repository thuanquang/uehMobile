package com.example.viewbinding

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.viewbinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the binding
        binding = ActivityMainBinding.inflate(layoutInflater)

        // Set content view with root view from binding
        setContentView(binding.root)

        // Access views directly through binding
        binding.buttonLogin.setOnClickListener {
            val username = binding.editTextUsername.text.toString()
            val password = binding.editTextPassword.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                // Navigate to Course Registration Activity
                val intent = Intent(this, CourseRegistrationActivity::class.java)
                intent.putExtra("username", username)
                intent.putExtra("password", password)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Vui lòng nhập Username và Password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
