package com.belyakov.loginform.ui.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hadilq.liveevent.LiveEvent

class RegistrationViewModel: ViewModel() {

    private val _isUserRegistered = LiveEvent<Boolean>()
    val isUserRegistered: LiveData<Boolean> = _isUserRegistered

    fun onRegisterClicked(
        fio: String,
        birth: String,
        sex: String,
        email: String
    ) {
        _isUserRegistered.value = true
    }
}