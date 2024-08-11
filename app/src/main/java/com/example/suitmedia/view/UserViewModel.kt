package com.example.suitmedia.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.paging.PagingData
import com.example.suitmedia.data.api.DataItem
import com.example.suitmedia.data.repository.UserRepository

class UserViewModel (private val userRepository: UserRepository) : ViewModel() {

    fun getAllUsers(): LiveData<PagingData<DataItem>>{
       return userRepository.getAllUsers().asLiveData()
    }

}