package com.example.week7assignment.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedules")
data class Schedule(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val hoTen: String = "",           // Full name
    val tieuDe: String = "",           // Title
    val noiDung: String = "",          // Content
    val fromTime: String = "",         // Start time (HH:mm)
    val toTime: String = "",           // End time (HH:mm)
    val linkHuu: String = "",          // URL link
    val createdDate: Long = System.currentTimeMillis()
)

