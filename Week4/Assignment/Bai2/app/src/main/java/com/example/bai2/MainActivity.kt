package com.example.bai2

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.DatePicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    
    private lateinit var editTextUsername: EditText
    private lateinit var editTextDate: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextIDCard: EditText
    private lateinit var editTextPhone: EditText
    private lateinit var btnUpdate: Button
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        // Initialize views
        editTextUsername = findViewById(R.id.editTextUsername)
        editTextDate = findViewById(R.id.editTextDate)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextIDCard = findViewById(R.id.editTextIDCard)
        editTextPhone = findViewById(R.id.editTextPhone)
        btnUpdate = findViewById(R.id.btnUpdate)
        
        // Setup input filters
        setupInputFilters()
        
        // Setup DatePicker for date field
        setupDatePicker()
        
        // Setup text watchers for real-time validation
        setupTextWatchers()
        
        // Initially disable button
        btnUpdate.isEnabled = false
        
        // Setup button click listener
        btnUpdate.setOnClickListener {
            // Handle update action here
            android.widget.Toast.makeText(this, "Cập nhật thành công!", android.widget.Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun setupInputFilters() {
        // Phone field: max 10 digits, numeric only
        val phoneFilters = arrayOf(
            InputFilter.LengthFilter(10),
            InputFilter { source, _, _, _, _, _ ->
                if (source.isEmpty()) source
                else source.filter { it.isDigit() }
            }
        )
        editTextPhone.filters = phoneFilters
        
        // ID Card field: max 12 digits, numeric only
        val idCardFilters = arrayOf(
            InputFilter.LengthFilter(12),
            InputFilter { source, _, _, _, _, _ ->
                if (source.isEmpty()) source
                else source.filter { it.isDigit() }
            }
        )
        editTextIDCard.filters = idCardFilters
    }
    
    private fun setupDatePicker() {
        editTextDate.setOnClickListener {
            showDatePickerDialog()
        }
        // Make it read-only
        editTextDate.isFocusable = false
        editTextDate.isClickable = true
    }
    
    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        
        // Set default date to 1990-01-01
        calendar.set(1990, Calendar.JANUARY, 1)
        
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        
        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                // Format date as DD/MM/YYYY
                val formattedDate = String.format(
                    "%02d/%02d/%04d",
                    selectedDay,
                    selectedMonth + 1,
                    selectedYear
                )
                editTextDate.setText(formattedDate)
                updateButtonState()
            },
            year,
            month,
            day
        )
        
        datePickerDialog.show()
    }
    
    private fun setupTextWatchers() {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            
            override fun afterTextChanged(s: Editable?) {
                validateFields()
                updateButtonState()
            }
        }
        
        // Add watchers to all fields
        editTextUsername.addTextChangedListener(textWatcher)
        editTextDate.addTextChangedListener(textWatcher)
        editTextEmail.addTextChangedListener(textWatcher)
        editTextIDCard.addTextChangedListener(textWatcher)
        editTextPhone.addTextChangedListener(textWatcher)
    }
    
    private fun validateFields() {
        // Validate email
        val email = editTextEmail.text.toString().trim()
        if (email.isNotEmpty() && !isValidEmail(email)) {
            editTextEmail.setError("Email không hợp lệ", null)
        } else {
            editTextEmail.error = null
        }
        
        // Validate phone
        val phone = editTextPhone.text.toString().trim()
        if (phone.isNotEmpty() && !isValidPhone(phone)) {
            editTextPhone.setError("Số điện thoại không hợp lệ", null)
        } else {
            editTextPhone.error = null
        }
        
        // Validate ID Card
        val idCard = editTextIDCard.text.toString().trim()
        if (idCard.isNotEmpty() && !isValidIDCard(idCard)) {
            editTextIDCard.setError("Số CMT không hợp lệ", null)
        } else {
            editTextIDCard.error = null
        }
    }
    
    private fun isValidEmail(email: String): Boolean {
        // Simple email validation: must contain @
        return email.contains("@") && email.indexOf("@") > 0 && email.indexOf("@") < email.length - 1
    }
    
    private fun isValidPhone(phone: String): Boolean {
        // Phone must be 10 digits maximum and all numeric
        return phone.isNotEmpty() && phone.length <= 10 && phone.all { it.isDigit() }
    }
    
    private fun isValidIDCard(idCard: String): Boolean {
        // ID Card must be 12 digits maximum and all numeric
        return idCard.isNotEmpty() && idCard.length <= 12 && idCard.all { it.isDigit() }
    }
    
    private fun isFormComplete(): Boolean {
        val username = editTextUsername.text.toString().trim()
        val date = editTextDate.text.toString().trim()
        val email = editTextEmail.text.toString().trim()
        val idCard = editTextIDCard.text.toString().trim()
        val phone = editTextPhone.text.toString().trim()
        
        // All fields must be filled
        if (username.isEmpty() || date.isEmpty() || email.isEmpty() || 
            idCard.isEmpty() || phone.isEmpty()) {
            return false
        }
        
        // Validate email format
        if (!isValidEmail(email)) {
            return false
        }
        
        // Validate phone
        if (!isValidPhone(phone)) {
            return false
        }
        
        // Validate ID Card
        if (!isValidIDCard(idCard)) {
            return false
        }
        
        // Check for any errors
        if (editTextEmail.error != null || editTextPhone.error != null || editTextIDCard.error != null) {
            return false
        }
        
        return true
    }
    
    private fun updateButtonState() {
        btnUpdate.isEnabled = isFormComplete()
    }
}