package com.example.tp1singleviewapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tp1singleviewapp.model.User

class SharedUserViewModel : ViewModel() {

    private var _user = MutableLiveData<User?>() // mutable inside viewmodel
    val user: LiveData<User?> get() = _user // immutable for observers

    fun setUser(newUser: User) {
        _user.value = newUser
    }

}