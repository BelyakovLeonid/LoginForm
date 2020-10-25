package com.belyakov.loginform.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.belyakov.loginform.data.Profile
import com.belyakov.loginform.data.interactors.auth.AuthInteractor
import com.belyakov.loginform.data.interactors.data.DataInteractor
import org.koin.core.KoinComponent
import org.koin.core.inject

class ProfileViewModel : ViewModel(), KoinComponent {

    private val authInteractor: AuthInteractor by inject()
    private val dataInteractor: DataInteractor by inject()

    private val _currentUser = MutableLiveData<Profile>()
    val currentUser: LiveData<Profile> = _currentUser

    init {
        _currentUser.value = dataInteractor.getCurrentProfile()
    }

    fun logout() {
        authInteractor.logout()
    }
}
