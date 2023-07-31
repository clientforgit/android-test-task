package com.clientforgit.test_task.user

import android.util.Log
import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    val allUsers: LiveData<List<User>> = userDao.getAllUsers()

    init {
        Log.i("test", allUsers.value.toString())
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }
}