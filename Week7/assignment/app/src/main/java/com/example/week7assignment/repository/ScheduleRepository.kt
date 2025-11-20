package com.example.week7assignment.repository

import androidx.lifecycle.LiveData
import com.example.week7assignment.data.Schedule
import com.example.week7assignment.data.ScheduleDao
import com.example.week7assignment.data.User
import com.example.week7assignment.data.UserDao

class ScheduleRepository(
    private val scheduleDao: ScheduleDao,
    private val userDao: UserDao
) {
    // Schedule operations
    fun getAllSchedules(): LiveData<List<Schedule>> = scheduleDao.getAllSchedules()

    suspend fun getScheduleById(id: Int): Schedule? = scheduleDao.getScheduleById(id)

    suspend fun insertSchedule(schedule: Schedule): Long = scheduleDao.insertSchedule(schedule)

    suspend fun updateSchedule(schedule: Schedule) = scheduleDao.updateSchedule(schedule)

    suspend fun deleteSchedule(schedule: Schedule) = scheduleDao.deleteSchedule(schedule)

    // User operations
    fun getCurrentUser(): LiveData<User?> = userDao.getCurrentUser()

    suspend fun insertUser(user: User): Long = userDao.insertUser(user)

    suspend fun updateUser(user: User) = userDao.updateUser(user)
}

