package com.example.week7assignment

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week7assignment.adapter.ScheduleAdapter
import com.example.week7assignment.data.Schedule
import com.example.week7assignment.data.ScheduleDatabase
import com.example.week7assignment.databinding.ActivityMainBinding
import com.example.week7assignment.dialog.AddScheduleDialog
import com.example.week7assignment.dialog.DeleteConfirmDialog
import com.example.week7assignment.repository.ScheduleRepository
import com.example.week7assignment.util.NotificationUtil
import com.example.week7assignment.viewmodel.ScheduleViewModel
import com.example.week7assignment.viewmodel.ScheduleViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ScheduleViewModel
    private lateinit var adapter: ScheduleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupDatabase()
        setupViewModel()
        setupRecyclerView()
        setupButtons()
        observeData()
    }

    private fun setupDatabase() {
        val database = ScheduleDatabase.getInstance(this)
        val scheduleDao = database.scheduleDao()
        val userDao = database.userDao()
        val repository = ScheduleRepository(scheduleDao, userDao)

        val factory = ScheduleViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(ScheduleViewModel::class.java)
    }

    private fun setupViewModel() {
        // Initialize database with sample user if needed
        viewModel.currentUser.observe(this) { user ->
            if (user == null) {
                // Initialize with a sample user
                val sampleUser = com.example.week7assignment.data.User(
                    hoTen = "Your Name",
                    email = "your.email@example.com",
                    linkProfile = "https://example.com/profile"
                )
                viewModel.addUser(sampleUser)
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = ScheduleAdapter(
            onScheduleClicked = { schedule ->
                showScheduleOptions(schedule)
            },
            onUrlClicked = { url ->
                Toast.makeText(this, "URL: $url", Toast.LENGTH_SHORT).show()
            }
        )
        binding.recyclerViewSchedules.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    private fun setupButtons() {
        binding.btnAddSchedule.setOnClickListener {
            showAddScheduleDialog()
        }
    }

    private fun observeData() {
        viewModel.schedules.observe(this) { schedules ->
            adapter.submitList(schedules)
            binding.tvEmptyState.visibility = if (schedules.isEmpty()) {
                android.view.View.VISIBLE
            } else {
                android.view.View.GONE
            }
        }

        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) {
                android.view.View.VISIBLE
            } else {
                android.view.View.GONE
            }
        }

        viewModel.error.observe(this) { error ->
            if (error != null) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showAddScheduleDialog() {
        AddScheduleDialog(
            onSave = { schedule ->
                viewModel.addSchedule(schedule)
                NotificationUtil.scheduleNotification(this, schedule)
                Toast.makeText(this, "Schedule added", Toast.LENGTH_SHORT).show()
            }
        ).show(supportFragmentManager, "AddScheduleDialog")
    }

    private fun showScheduleOptions(schedule: Schedule) {
        DeleteConfirmDialog(
            title = schedule.tieuDe,
            message = "Delete this schedule?",
            onConfirm = {
                viewModel.deleteSchedule(schedule)
                NotificationUtil.cancelNotification(this, schedule.id)
                Toast.makeText(this, "Schedule deleted", Toast.LENGTH_SHORT).show()
            },
            onEdit = {
                // Edit functionality
                AddScheduleDialog(
                    initialSchedule = schedule,
                    onSave = { editedSchedule ->
                        val updated = editedSchedule.copy(id = schedule.id)
                        viewModel.updateSchedule(updated)
                        Toast.makeText(this, "Schedule updated", Toast.LENGTH_SHORT).show()
                    }
                ).show(supportFragmentManager, "EditScheduleDialog")
            }
        ).show(supportFragmentManager, "DeleteConfirmDialog")
    }
}

