package com.example.tp1singleviewapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tp1singleviewapp.model.User
import kotlin.text.isEmpty

class UserViewModel : ViewModel() {

    // LiveData for form inputs
    val email = MutableLiveData("")

    val name = MutableLiveData("")
    val surname = MutableLiveData("")
    val phone = MutableLiveData("")
    val birthday = MutableLiveData("")

    fun validateInputs(): Boolean {
        if (email.value.isNullOrEmpty()) return false
        if (name.value.isNullOrEmpty()) return false
        if (surname.value.isNullOrEmpty()) return false
        if (phone.value.isNullOrEmpty()) return false
        if (birthday.value.isNullOrEmpty()) return false
        return true
    }

    fun toUser(): User {
        return User(
            name = name.value.orEmpty(),
            surname = surname.value.orEmpty(),
            email = email.value.orEmpty(),
            number = phone.value.orEmpty(),
            country = "",
            birthday = birthday.value.orEmpty()
        )
    }
}