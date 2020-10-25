package com.belyakov.loginform.data.interactors.auth

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.concurrent.TimeUnit

class AuthInteractorImpl : AuthInteractor, KoinComponent {

    private val firebaseAuth: FirebaseAuth by inject()
    private val phoneAuthProvider: PhoneAuthProvider by inject()

    private var verificationId: String = ""
    private var credentials: PhoneAuthCredential? = null

    init {
        firebaseAuth.setLanguageCode("ru")
    }

    override fun verifyPhone(
        phone: String,
        callBack: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    ) {
        phoneAuthProvider.verifyPhoneNumber(
            phone,
            VERIFY_TIMEOUT,
            TimeUnit.SECONDS,
            Dispatchers.IO.asExecutor(),
            callBack
        )
    }

    override fun verifyCode(code: String, completeCallback: OnCompleteListener<AuthResult>) {
        val creds = credentials ?: PhoneAuthProvider.getCredential(verificationId, code)
        firebaseAuth.signInWithCredential(creds)
            .addOnCompleteListener(Dispatchers.IO.asExecutor(), completeCallback)
    }

    override fun postVerificationId(id: String) {
        verificationId = id
    }

    override fun postCredentials(creds: PhoneAuthCredential) {
        credentials = creds
    }

    override fun logout() {
        firebaseAuth.signOut()
    }
}