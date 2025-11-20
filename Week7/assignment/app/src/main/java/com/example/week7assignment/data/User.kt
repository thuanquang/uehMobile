package com.example.week7assignment.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val hoTen: String = "",            // Full name
    val email: String = "",            // Email
    val linkProfile: String = "",      // Profile link URL
    val avatarUrl: String = "",        // Avatar URL from database
    val createdDate: Long = System.currentTimeMillis()
)

