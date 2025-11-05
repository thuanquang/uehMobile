package com.example.bai4

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bai4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TaskAdapter
    private val tasks = mutableListOf<TaskModel>()
    private var nextId = 1

    private val editLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val updatedTask = result.data?.getParcelableExtra<TaskModel>("task")
            val position = result.data?.getIntExtra("position", -1) ?: -1
            if (updatedTask != null && position >= 0) {
                adapter.updateTask(position, updatedTask)
            }
        }
    }

    private val addLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val newTask = result.data?.getParcelableExtra<TaskModel>("task")
            if (newTask != null) {
                adapter.addTask(newTask)
                nextId++
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize sample data
        tasks.add(TaskModel(nextId++, "Quản lý thư mục", "Tổng hợp tin tức thời sự"))
        tasks.add(TaskModel(nextId++, "Do It Your Self", "Sáo lóng kĩ gặp dễ cái hay"))
        tasks.add(TaskModel(nextId++, "Cẩm hùng sáng tạo", "Tổng hợp tin tức thời sự"))
        tasks.add(TaskModel(nextId++, "Tổng hợp tin tức thời sự", "Tổng hợp tin tức thời sự"))

        // Setup RecyclerView
        adapter = TaskAdapter(tasks) { task, position ->
            val intent = Intent(this, EditActivity::class.java)
            intent.putExtra("task", task)
            intent.putExtra("position", position)
            editLauncher.launch(intent)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // Add new task button
        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            intent.putExtra("nextId", nextId)
            addLauncher.launch(intent)
        }
    }
}
