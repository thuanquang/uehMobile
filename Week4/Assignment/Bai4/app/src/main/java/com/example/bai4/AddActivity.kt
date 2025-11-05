package com.example.bai4

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bai4.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    private var nextId: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        nextId = intent.getIntExtra("nextId", 1)

        binding.btnAdd.setOnClickListener {
            val title = binding.editTitle.text.toString()
            val description = binding.editDescription.text.toString()

            if (title.isNotBlank()) {
                val newTask = TaskModel(nextId, title, description)
                val resultIntent = Intent()
                resultIntent.putExtra("task", newTask)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }

        binding.btnCancel.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}

