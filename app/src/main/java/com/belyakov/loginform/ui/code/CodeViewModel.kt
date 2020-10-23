package com.belyakov.loginform.ui.code

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.belyakov.loginform.ui.code.ConfirmationResult.*

class CodeViewModel : ViewModel() {

    private val _confirmationResult = MutableLiveData<ConfirmationResult>()
    val confirmationResult: LiveData<ConfirmationResult> = _confirmationResult

    fun checkCode(code: Int) {
        _confirmationResult.value = NOT_REGISTERED
    }
}

enum class ConfirmationResult {
    NOT_CONFIRMED, CONFIRMED, NOT_REGISTERED
}