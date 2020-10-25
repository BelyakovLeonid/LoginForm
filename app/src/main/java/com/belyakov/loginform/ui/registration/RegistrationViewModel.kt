package com.belyakov.loginform.ui.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.belyakov.loginform.data.Profile
import com.belyakov.loginform.data.interactors.data.DataInteractor
import com.hadilq.liveevent.LiveEvent
import org.koin.core.KoinComponent
import org.koin.core.inject

class RegistrationViewModel : ViewModel(), KoinComponent {

    private val dataInteractor: DataInteractor by inject()

    private val _isUserRegistered = LiveEvent<Boolean>()
    val isUserRegistered: LiveData<Boolean> = _isUserRegistered

    private var birthDate: Long? = null

    fun onRegisterClicked(
        fio: String,
        sex: Boolean,
        email: String
    ) {
        dataInteractor.createProfile(
            Profile(
                getPartFromFioString(fio, 0),
                getPartFromFioString(fio, 1),
                getPartFromFioString(fio, 2),
                birthDate ?: 0,
                sex,
                email
            )
        )
        _isUserRegistered.value = true
    }

    private fun getPartFromFioString(fio: String, partIndex: Int) =
        fio.split(" ").getOrNull(partIndex) ?: ""

    fun onBirthDatePicked(dateMillis: Long) {
        birthDate = dateMillis
    }
}