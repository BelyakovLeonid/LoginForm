package com.belyakov.loginform.data

import java.util.*

data class Profile(
    val name: String,
    val secondName: String,
    val thirdName: String,
    val dateOfBirth: Date,
    val sex: Boolean,
    val email: String
)