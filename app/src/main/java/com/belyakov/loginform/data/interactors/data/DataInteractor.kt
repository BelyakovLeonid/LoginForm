package com.belyakov.loginform.data.interactors.data

import com.belyakov.loginform.data.Profile
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener

interface DataInteractor {
    fun checkUserIsRegistered(uid: String, callback: ValueEventListener)
    fun postProfileSnapshot(snapshot: DataSnapshot)
    fun getCurrentProfile(): Profile?
}
