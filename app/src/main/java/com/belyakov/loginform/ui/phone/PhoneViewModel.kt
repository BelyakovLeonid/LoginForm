package com.belyakov.loginform.ui.phone

import androidx.lifecycle.ViewModel
import com.belyakov.loginform.data.interactors.auth.AuthInteractor
import com.belyakov.loginform.ui.phone.VerificationResult.*
import com.belyakov.loginform.utils.SingleLiveEvent
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import org.koin.core.KoinComponent
import org.koin.core.inject

class PhoneViewModel : ViewModel(), KoinComponent {

    private val authInteractor: AuthInteractor by inject()

    val verificationResult = SingleLiveEvent<VerificationResult>()

    private val verificationCallback: OnVerificationStateChangedCallbacks =
        object : OnVerificationStateChangedCallbacks() {

            /*
            Данный колбек сработает в тех редких случаях, когда для аутентификации нам не потребуется отсылать код из смс
            Например для телефона, указанного как тестовый в консоли Firebase
            По идее, можно сразу делать signIn. Но для того чтобы сохранить воркфлоу, описанный в ТЗ, так поступать не будем
            */
            override fun onVerificationCompleted(creds: PhoneAuthCredential) {
                authInteractor.postCredentials(creds)
                verificationResult.postValue(VerificationCompleted(creds))
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                verificationResult.postValue(VerificationFailed(p0))
            }

            override fun onCodeSent(verificationId: String, token: ForceResendingToken) {
                super.onCodeSent(verificationId, token)
                authInteractor.postVerificationId(verificationId)
                verificationResult.postValue(VerificationCodeSent(verificationId, token))
            }
        }

    fun verifyPhone(phone: String) {
        if (phone.isBlank()) {
            verificationResult.value = VerificationFailed(FirebaseException("Empty phone"))
        } else {
            authInteractor.verifyPhone(phone, verificationCallback)
        }
    }
}

sealed class VerificationResult {
    class VerificationCompleted(c: PhoneAuthCredential) : VerificationResult()
    class VerificationFailed(e: FirebaseException) : VerificationResult()
    class VerificationCodeSent(id: String, rt: ForceResendingToken) : VerificationResult()
}