package com.example.bt12 // Your package name here

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextTo: EditText
    private lateinit var editTextSubject: EditText
    private lateinit var editTextMessage: EditText
    private lateinit var buttonSend: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Connect to UI elements
        editTextTo = findViewById(R.id.editTextTo)
        editTextSubject = findViewById(R.id.editTextSubject)
        editTextMessage = findViewById(R.id.editTextMessage)
        buttonSend = findViewById(R.id.buttonSend)

        // Set button click listener
        buttonSend.setOnClickListener {
            sendEmail()
        }
    }

    private fun sendEmail() {
        // Get text from all fields
        val recipient = editTextTo.text.toString().trim()
        val subject = editTextSubject.text.toString().trim()
        val message = editTextMessage.text.toString().trim()

        // Validate: check if at least recipient is filled
        if (recipient.isEmpty()) {
            Toast.makeText(this, "Please enter recipient email!", Toast.LENGTH_SHORT).show()
            return
        }

        // Create an Intent to send email
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, message)

        // Show chooser (let user pick email app)
        try {
            startActivity(Intent.createChooser(intent, "Choose Email App"))
        } catch (e: Exception) {
            Toast.makeText(this, "No email app found!", Toast.LENGTH_SHORT).show()
        }
    }
}
