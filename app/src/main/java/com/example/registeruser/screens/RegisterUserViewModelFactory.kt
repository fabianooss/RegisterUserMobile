package com.example.registeruser.screens

import androidx.lifecycle.ViewModel
import com.example.registeruser.dao.UserDao

class RegisterUserViewModelFactory(
        private val id: Int?,
        private val userDao: UserDao
    ): androidx.lifecycle.ViewModelProvider.Factory
  {
      override fun <T : ViewModel> create(modelClass: Class<T>): T {
          return RegisterUserViewModel(id, userDao) as T
      }
  }