package com.example.bt16  // Your package name

data class Employee(
    val id: String,
    val name: String,
    val type: String  // "FullTime" or "PartTime"
) {
    // Calculate salary based on type
    fun getSalary(): Double {
        return when (type) {
            "FullTime" -> 500.0
            "PartTime" -> 150.0
            else -> 0.0
        }
    }

    // Format display string
    override fun toString(): String {
        return "$id - $name -->$type=${getSalary()}"
    }
}
