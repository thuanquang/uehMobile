package com.example.week7assignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week7assignment.data.Schedule
import com.example.week7assignment.data.User
import com.example.week7assignment.repository.ScheduleRepository
import kotlinx.coroutines.launch

class ScheduleViewModel(private val repository: ScheduleRepository) : ViewModel() {

    val schedules: LiveData<List<Schedule>> = repository.getAllSchedules()
    val currentUser: LiveData<User?> = repository.getCurrentUser()

    // State for new schedule being added/edited
    private val _selectedSchedule = MutableLiveData<Schedule?>(null)
    val selectedSchedule: LiveData<Schedule?> = _selectedSchedule

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error

    fun addSchedule(schedule: Schedule) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                repository.insertSchedule(schedule)
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message ?: "Error adding schedule"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateSchedule(schedule: Schedule) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                repository.updateSchedule(schedule)
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message ?: "Error updating schedule"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteSchedule(schedule: Schedule) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                repository.deleteSchedule(schedule)
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message ?: "Error deleting schedule"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun selectSchedule(schedule: Schedule?) {
        _selectedSchedule.value = schedule
    }

    fun addUser(user: User) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                repository.insertUser(user)
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message ?: "Error adding user"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                repository.updateUser(user)
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message ?: "Error updating user"
            } finally {
                _isLoading.value = false
            }
        }
    }
}

