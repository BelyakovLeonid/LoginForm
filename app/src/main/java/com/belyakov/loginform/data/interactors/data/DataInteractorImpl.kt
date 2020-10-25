package com.belyakov.loginform.data.interactors.data

import com.belyakov.loginform.data.Profile
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import org.koin.core.KoinComponent
import org.koin.core.inject

const val PROFILE_DB_NAME = "profiles"

class DataInteractorImpl : DataInteractor, KoinComponent {

    private val dataBase: DatabaseReference by inject()
    private var currentProfile: Profile? = null

    override fun checkUserIsRegistered(uid: String, callback: ValueEventListener) {
        dataBase.child(PROFILE_DB_NAME).child(uid).addListenerForSingleValueEvent(callback)
    }

    override fun postProfileSnapshot(snapshot: DataSnapshot) {
        val valueMap = snapshot.value as? Map<*, *> ?: return
        currentProfile = Profile(
            valueMap["name"] as String,
            valueMap["secondName"] as String,
            valueMap["thirdName"] as String,
            valueMap["dateOfBirth"] as Long,
        )
    }

    override fun getCurrentProfile() = currentProfile
}