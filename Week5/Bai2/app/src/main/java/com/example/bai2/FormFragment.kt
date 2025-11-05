package com.example.bai2

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.util.Calendar

/**
 * Fragment for form validation with custom spinner and date picker
 */
class FormFragment : Fragment() {

    private lateinit var editUsername: EditText
    private lateinit var editEmail: EditText
    private lateinit var editPhone: EditText
    private lateinit var editIdCard: EditText
    private lateinit var editDate: EditText
    private lateinit var spinnerField: Spinner
    private lateinit var buttonUpdate: Button
    private lateinit var buttonClear: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views
        editUsername = view.findViewById(R.id.editUsername)
        editEmail = view.findViewById(R.id.editEmail)
        editPhone = view.findViewById(R.id.editPhone)
        editIdCard = view.findViewById(R.id.editIdCard)
        editDate = view.findViewById(R.id.editDate)
        spinnerField = view.findViewById(R.id.spinnerField)
        buttonUpdate = view.findViewById(R.id.buttonUpdate)
        buttonClear = view.findViewById(R.id.buttonClear)

        // Setup custom spinner adapter
        setupSpinner()

        // Setup listeners
        setupTextWatchers()
        setupDatePicker()
        setupButtonListeners()

        // Initially disable update button
        buttonUpdate.isEnabled = false
    }

    private fun setupSpinner() {
        val fieldList = listOf(
            FieldModel(1, "Username"),
            FieldModel(2, "Email"),
            FieldModel(3, "Phone"),
            FieldModel(4, "ID Card"),
            FieldModel(5, "Date")
        )
        
        val adapter = FieldSpinnerAdapter(requireContext(), fieldList)
        spinnerField.adapter = adapter
    }

    private fun setupTextWatchers() {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                updateButtonState()
            }
            override fun afterTextChanged(s: Editable?) {}
        }

        editUsername.addTextChangedListener(textWatcher)
        editEmail.addTextChangedListener(textWatcher)
        editPhone.addTextChangedListener(textWatcher)
        editIdCard.addTextChangedListener(textWatcher)
        editDate.addTextChangedListener(textWatcher)
    }

    private fun setupDatePicker() {
        editDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, selectedYear, selectedMonth, selectedDay ->
                    val formattedDate = String.format(
                        "%02d/%02d/%04d",
                        selectedDay,
                        selectedMonth + 1,
                        selectedYear
                    )
                    editDate.setText(formattedDate)
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }
    }

    private fun setupButtonListeners() {
        buttonUpdate.setOnClickListener {
            if (validateForm()) {
                showSuccessMessage()
            }
        }

        buttonClear.setOnClickListener {
            clearForm()
        }
    }

    private fun updateButtonState() {
        buttonUpdate.isEnabled = isFormComplete()
    }

    private fun isFormComplete(): Boolean {
        return editUsername.text.isNotEmpty() &&
                editEmail.text.isNotEmpty() &&
                editPhone.text.isNotEmpty() &&
                editIdCard.text.isNotEmpty() &&
                editDate.text.isNotEmpty()
    }

    private fun validateForm(): Boolean {
        val username = editUsername.text.toString().trim()
        val email = editEmail.text.toString().trim()
        val phone = editPhone.text.toString().trim()
        val idCard = editIdCard.text.toString().trim()

        if (username.isEmpty()) {
            showError(getString(R.string.error_username_empty))
            return false
        }

        if (!isValidEmail(email)) {
            showError(getString(R.string.error_email_invalid))
            return false
        }

        if (!isValidPhone(phone)) {
            showError(getString(R.string.error_phone_invalid))
            return false
        }

        if (!isValidIdCard(idCard)) {
            showError(getString(R.string.error_idcard_invalid))
            return false
        }

        return true
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPhone(phone: String): Boolean {
        return phone.length >= 10 && phone.all { it.isDigit() }
    }

    private fun isValidIdCard(idCard: String): Boolean {
        return idCard.length >= 9 && idCard.all { it.isDigit() || it == 'X' }
    }

    private fun showSuccessMessage() {
        Toast.makeText(
            requireContext(),
            "Form is valid! Username: ${editUsername.text}",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun clearForm() {
        editUsername.text.clear()
        editEmail.text.clear()
        editPhone.text.clear()
        editIdCard.text.clear()
        editDate.text.clear()
        buttonUpdate.isEnabled = false
    }
}
