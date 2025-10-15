package com.example.bt15  // Your package name

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    // UI element
    private lateinit var display: TextView

    // Calculator state variables
    private var currentNumber = ""
    private var previousNumber = ""
    private var operation = ""
    private var isNewOperation = true

    // Number formatter
    private val decimalFormat = DecimalFormat("#,###.##########")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Connect to display
        display = findViewById(R.id.textViewDisplay)

        // Number buttons
        val numberButtons = listOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3,
            R.id.button4, R.id.button5, R.id.button6, R.id.button7,
            R.id.button8, R.id.button9
        )

        for (id in numberButtons) {
            findViewById<Button>(id).setOnClickListener { view ->
                val number = (view as Button).text.toString()
                onNumberClick(number)
            }
        }

        // Operation buttons
        findViewById<Button>(R.id.buttonAdd).setOnClickListener { onOperationClick("+") }
        findViewById<Button>(R.id.buttonSubtract).setOnClickListener { onOperationClick("-") }
        findViewById<Button>(R.id.buttonMultiply).setOnClickListener { onOperationClick("×") }
        findViewById<Button>(R.id.buttonDivide).setOnClickListener { onOperationClick("÷") }

        // Special buttons
        findViewById<Button>(R.id.buttonEquals).setOnClickListener { onEqualsClick() }
        findViewById<Button>(R.id.buttonClear).setOnClickListener { onClearClick() }
        findViewById<Button>(R.id.buttonDecimal).setOnClickListener { onDecimalClick() }
        findViewById<Button>(R.id.buttonPercent).setOnClickListener { onPercentClick() }
        findViewById<Button>(R.id.buttonPlusMinus).setOnClickListener { onPlusMinusClick() }
    }

    // Handle number button clicks
    private fun onNumberClick(number: String) {
        if (isNewOperation) {
            currentNumber = number
            isNewOperation = false
        } else {
            // Limit display length
            if (currentNumber.length < 15) {
                currentNumber += number
            }
        }
        updateDisplay()
    }

    // Handle operation buttons (+, -, ×, ÷)
    private fun onOperationClick(op: String) {
        if (currentNumber.isNotEmpty()) {
            // If there's a previous operation, calculate it first
            if (previousNumber.isNotEmpty() && !isNewOperation) {
                calculate()
            }
            previousNumber = currentNumber
            operation = op
            isNewOperation = true
        }
    }

    // Handle equals button
    private fun onEqualsClick() {
        if (previousNumber.isNotEmpty() && currentNumber.isNotEmpty() && operation.isNotEmpty()) {
            calculate()
            operation = ""
            previousNumber = ""
            isNewOperation = true
        }
    }

    // Perform the actual calculation
    private fun calculate() {
        val num1 = previousNumber.toDoubleOrNull() ?: return
        val num2 = currentNumber.toDoubleOrNull() ?: return

        val result = when (operation) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "×" -> num1 * num2
            "÷" -> {
                if (num2 == 0.0) {
                    display.text = "Error"
                    currentNumber = ""
                    return
                }
                num1 / num2
            }
            else -> return
        }

        currentNumber = result.toString()
        updateDisplay()
    }

    // Handle clear button
    private fun onClearClick() {
        currentNumber = ""
        previousNumber = ""
        operation = ""
        isNewOperation = true
        display.text = "0"
    }

    // Handle decimal point
    private fun onDecimalClick() {
        if (isNewOperation) {
            currentNumber = "0."
            isNewOperation = false
        } else if (!currentNumber.contains(".")) {
            currentNumber += "."
        }
        updateDisplay()
    }

    // Handle percent button
    private fun onPercentClick() {
        if (currentNumber.isNotEmpty()) {
            val number = currentNumber.toDoubleOrNull() ?: return
            currentNumber = (number / 100).toString()
            updateDisplay()
        }
    }

    // Handle +/- button (toggle sign)
    private fun onPlusMinusClick() {
        if (currentNumber.isNotEmpty() && currentNumber != "0") {
            currentNumber = if (currentNumber.startsWith("-")) {
                currentNumber.substring(1)  // Remove minus sign
            } else {
                "-$currentNumber"  // Add minus sign
            }
            updateDisplay()
        }
    }

    // Update display with current number
    private fun updateDisplay() {
        if (currentNumber.isEmpty()) {
            display.text = "0"
        } else {
            // Try to format with commas
            val number = currentNumber.toDoubleOrNull()
            display.text = if (number != null && !currentNumber.contains(".") && !currentNumber.endsWith(".")) {
                decimalFormat.format(number)
            } else {
                currentNumber
            }
        }
    }
}
