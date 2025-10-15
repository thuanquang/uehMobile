package com.example.bt13  // Your package name here

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Declare UI elements
    private lateinit var editTextA: EditText
    private lateinit var editTextB: EditText
    private lateinit var buttonAdd: Button
    private lateinit var buttonSubtract: Button
    private lateinit var buttonMultiply: Button
    private lateinit var buttonDivide: Button
    private lateinit var textViewResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Connect to UI elements from XML
        editTextA = findViewById(R.id.editTextA)
        editTextB = findViewById(R.id.editTextB)
        buttonAdd = findViewById(R.id.buttonAdd)
        buttonSubtract = findViewById(R.id.buttonSubtract)
        buttonMultiply = findViewById(R.id.buttonMultiply)
        buttonDivide = findViewById(R.id.buttonDivide)
        textViewResult = findViewById(R.id.textViewResult)

        // Set click listeners for each button
        buttonAdd.setOnClickListener {
            calculate("+")
        }

        buttonSubtract.setOnClickListener {
            calculate("-")
        }

        buttonMultiply.setOnClickListener {
            calculate("*")
        }

        buttonDivide.setOnClickListener {
            calculate("/")
        }
    }

    // Main calculation function
    private fun calculate(operation: String) {
        // Get text from input fields
        val aText = editTextA.text.toString().trim()
        val bText = editTextB.text.toString().trim()

        // Validate: check if fields are empty
        if (aText.isEmpty() || bText.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập cả hai số!", Toast.LENGTH_SHORT).show()
            textViewResult.text = ""
            return
        }

        // Convert String to Double (allows decimals)
        val a = aText.toDoubleOrNull()
        val b = bText.toDoubleOrNull()

        // Check if conversion was successful
        if (a == null || b == null) {
            Toast.makeText(this, "Số không hợp lệ!", Toast.LENGTH_SHORT).show()
            textViewResult.text = ""
            return
        }

        // Perform calculation based on operation
        val result = when(operation) {
            "+" -> a + b
            "-" -> a - b
            "*" -> a * b
            "/" -> {
                // Special handling for division by zero
                if (b == 0.0) {
                    Toast.makeText(this, "Không thể chia cho 0!", Toast.LENGTH_SHORT).show()
                    textViewResult.text = ""
                    return
                }
                a / b
            }
            else -> 0.0
        }

        // Display result in formatted string
        // Format: "9999 + 1111 = 11110"
        textViewResult.text = formatResult(a, b, operation, result)
    }

    // Helper function to format the result display
    private fun formatResult(a: Double, b: Double, operation: String, result: Double): String {
        // Remove unnecessary decimal points for whole numbers
        val aFormatted = if (a % 1.0 == 0.0) a.toInt().toString() else a.toString()
        val bFormatted = if (b % 1.0 == 0.0) b.toInt().toString() else b.toString()
        val resultFormatted = if (result % 1.0 == 0.0) result.toInt().toString() else String.format("%.2f", result)

        return "$aFormatted $operation $bFormatted = $resultFormatted"
    }
}
