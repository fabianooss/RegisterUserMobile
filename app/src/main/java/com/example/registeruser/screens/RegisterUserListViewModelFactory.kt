package com.example.registeruser.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.registeruser.dao.UserDao

class RegisterUserListViewModelFactory(
    private val userDao: UserDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegisterUserListViewModel(userDao) as T
    }
}