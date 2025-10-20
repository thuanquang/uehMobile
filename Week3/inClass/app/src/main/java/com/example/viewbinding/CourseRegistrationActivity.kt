package com.example.viewbinding

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.viewbinding.databinding.ActivityCourseRegistrationBinding

class CourseRegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCourseRegistrationBinding
    private val selectedCourses = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the binding
        binding = ActivityCourseRegistrationBinding.inflate(layoutInflater)

        // Set content view with root view from binding
        setContentView(binding.root)

        // Get username from Intent
        val username = intent.getStringExtra("username") ?: "tung"
        binding.textViewWelcome.text = "Xin chào $username\nHãy đăng ký môn học"

        // Handle Register button click
        binding.buttonRegister.setOnClickListener {
            selectedCourses.clear()

            // Check which checkboxes are selected
            if (binding.checkBoxCPlusPlus.isChecked) {
                selectedCourses.add("Lập trình C++")
            }
            if (binding.checkBoxMAS.isChecked) {
                selectedCourses.add("MAS")
            }
            if (binding.checkBoxWebDev.isChecked) {
                selectedCourses.add("Xử lý Ứng dụng")
            }
            if (binding.checkBoxAI.isChecked) {
                selectedCourses.add("Đồ Án Thực Tế")
            }

            // Display selected courses
            if (selectedCourses.isEmpty()) {
                binding.textViewSelectedCourses.text = "Chưa chọn môn nào"
                Toast.makeText(this, "Vui lòng chọn ít nhất một môn học", Toast.LENGTH_SHORT).show()
            } else {
                val coursesText = selectedCourses.joinToString("\n")
                binding.textViewSelectedCourses.text = coursesText
            }
        }

        // Handle Confirm button click
        binding.buttonConfirm.setOnClickListener {
            if (selectedCourses.isEmpty()) {
                Toast.makeText(this, "Vui lòng đăng ký môn học trước", Toast.LENGTH_SHORT).show()
            } else {
                // Navigate to Payment Activity
                val intent = Intent(this, PaymentActivity::class.java)
                intent.putStringArrayListExtra("courses", ArrayList(selectedCourses))
                intent.putExtra("username", username)
                startActivity(intent)
            }
        }
    }
}
