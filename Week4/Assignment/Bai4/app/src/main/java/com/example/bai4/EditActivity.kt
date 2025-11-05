package com.example.bai4

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bai4.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    private var task: TaskModel? = null
    private var position: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        task = intent.getParcelableExtra("task")
        position = intent.getIntExtra("position", -1)

        task?.let {
            binding.editTitle.setText(it.title)
            binding.editDescription.setText(it.description)
        }

        binding.btnSave.setOnClickListener {
            val updatedTask = TaskModel(
                task?.id ?: 0,
                binding.editTitle.text.toString(),
                binding.editDescription.text.toString()
            )
            val resultIntent = Intent()
            resultIntent.putExtra("task", updatedTask)
            resultIntent.putExtra("position", position)
            setResult(RESULT_OK, resultIntent)
            finish()
        }

        binding.btnCancel.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}

