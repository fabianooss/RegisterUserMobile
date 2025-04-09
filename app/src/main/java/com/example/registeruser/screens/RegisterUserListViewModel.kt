package com.example.registeruser.screens

import androidx.lifecycle.ViewModel
import com.example.registeruser.dao.UserDao
import com.example.registeruser.entity.User
import kotlinx.coroutines.flow.Flow

class RegisterUserListViewModel (
    private val userDao: UserDao
): ViewModel() {

    val users: Flow<List<User>>  = userDao.findAll()

}