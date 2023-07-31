package com.clientforgit.test_task.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    var name: String,
    var age: Int,
    var isStudent: Boolean = false,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
