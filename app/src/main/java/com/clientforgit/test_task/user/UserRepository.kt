package com.clientforgit.test_task.user

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    val allUsers: LiveData<List<User>> = userDao.getAllUsers()

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }
}