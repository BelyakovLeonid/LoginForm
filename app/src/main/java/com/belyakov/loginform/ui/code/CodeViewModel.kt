package com.belyakov.loginform.ui.code

import androidx.lifecycle.ViewModel
import com.belyakov.loginform.data.interactors.auth.AuthInteractor
import com.belyakov.loginform.data.interactors.data.DataInteractor
import com.belyakov.loginform.ui.code.ConfirmationResult.*
import com.belyakov.loginform.utils.SingleLiveEvent
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import org.koin.core.KoinComponent
import org.koin.core.inject

class CodeViewModel : ViewModel(), KoinComponent {

    private val authInteractor: AuthInteractor by inject()
    private val dataInteractor: DataInteractor by inject()

    val confirmationResult = SingleLiveEvent<ConfirmationResult>()

    private val checkRegisteredCallback: ValueEventListener =
        object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.value == null) {
                    confirmationResult.postValue(NOT_REGISTERED)
                } else {
                    dataInteractor.postProfileSnapshot(snapshot)
                    confirmationResult.postValue(CONFIRMED)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                confirmationResult.postValue(NOT_REGISTERED)
            }
        }

    private val verificationCallback: OnCompleteListener<AuthResult> =
        OnCompleteListener<AuthResult> { task ->
            val uid = task.result?.user?.uid
            if (task.isSuccessful && uid != null) {
                dataInteractor.checkUserIsRegistered(uid, checkRegisteredCallback)
            } else {
                confirmationResult.postValue(NOT_CONFIRMED)
            }

        }

    fun checkCode(code: String) {
        authInteractor.verifyCode(code, verificationCallback)
    }
}

enum class ConfirmationResult {
    NOT_CONFIRMED, CONFIRMED, NOT_REGISTERED
}