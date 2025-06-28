package ru.fefu.android.ui.personal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PersonalViewModel : ViewModel() {

    private val _login = MutableLiveData<String>().apply {
        value = "111"
    }
    private val _nickname = MutableLiveData<String>().apply {
        value = "Eschkere"
    }
    val login: LiveData<String> = _login
    val nickname: LiveData<String> = _nickname
}