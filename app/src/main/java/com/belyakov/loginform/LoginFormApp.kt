package com.belyakov.loginform

import android.app.Application
import com.belyakov.loginform.data.interactors.auth.AuthInteractor
import com.belyakov.loginform.di.firebaseModule
import com.belyakov.loginform.di.interactorsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.inject

class LoginFormApp : Application(), KoinComponent {

    private val authInteractor: AuthInteractor by inject()

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(applicationContext)
            modules(interactorsModule, firebaseModule)
        }
    }
}