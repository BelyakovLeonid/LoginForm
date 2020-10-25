package com.belyakov.loginform.di

import com.belyakov.loginform.data.interactors.auth.AuthInteractor
import com.belyakov.loginform.data.interactors.auth.AuthInteractorImpl
import com.belyakov.loginform.data.interactors.data.DataInteractor
import com.belyakov.loginform.data.interactors.data.DataInteractorImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.FirebaseDatabase
import org.koin.dsl.module

val interactorsModule = module {
    single<AuthInteractor> { AuthInteractorImpl() }
    single<DataInteractor> { DataInteractorImpl() }
}

val firebaseModule = module {
    single { FirebaseAuth.getInstance() }
    single { PhoneAuthProvider.getInstance() }
    single { FirebaseDatabase.getInstance().reference }
}