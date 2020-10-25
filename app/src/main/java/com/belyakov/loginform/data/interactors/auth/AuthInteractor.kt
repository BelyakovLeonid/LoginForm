package com.belyakov.loginform.data.interactors.auth

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks

const val VERIFY_TIMEOUT = 60L

interface AuthInteractor {
    fun verifyPhone(phone: String, callBack: OnVerificationStateChangedCallbacks)
    fun verifyCode(code: String, completeCallback: OnCompleteListener<AuthResult>)
    fun postCredentials(creds: PhoneAuthCredential)
    fun postVerificationId(id: String)
    fun logout()
}