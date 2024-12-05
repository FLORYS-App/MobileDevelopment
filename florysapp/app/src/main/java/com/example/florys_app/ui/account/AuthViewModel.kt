package com.example.florys_app.ui.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {
    private val _email = MutableLiveData<String>()
    private val _password = MutableLiveData<String>()
    private val _name = MutableLiveData<String>()

    val email: LiveData<String> = _email
    val password: LiveData<String> = _password
    val name: LiveData<String> = _name
}