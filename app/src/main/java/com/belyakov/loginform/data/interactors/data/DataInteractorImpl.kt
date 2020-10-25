package com.belyakov.loginform.data.interactors.data

import com.belyakov.loginform.data.Profile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import org.koin.core.KoinComponent
import org.koin.core.inject

const val PROFILE_DB_NAME = "profiles"

class DataInteractorImpl : DataInteractor, KoinComponent {

    private val dataBase: DatabaseReference by inject()
    private val firebaseAuth: FirebaseAuth by inject()

    private var currentProfile: Profile? = null

    override fun checkUserIsRegistered(uid: String, callback: ValueEventListener) {
        dataBase.child(PROFILE_DB_NAME).child(uid).addListenerForSingleValueEvent(callback)
    }

    override fun postProfileSnapshot(snapshot: DataSnapshot) {
        val valueMap = snapshot.value as? Map<*, *> ?: return
        currentProfile = Profile(
            valueMap["surname"] as String,
            valueMap["name"] as String,
            valueMap["patronymic"] as String,
            valueMap["dateOfBirth"] as Long,
            valueMap["sex"] as Boolean,
            valueMap["email"] as String,
        )
    }

    override fun createProfile(profile: Profile) {
        currentProfile = profile
        firebaseAuth.uid?.let {
            dataBase.child(PROFILE_DB_NAME).child(it).setValue(profile)
        }
    }

    override fun getCurrentProfile() = currentProfile
}