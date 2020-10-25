package com.belyakov.loginform.data

data class Profile(
    val surname: String,
    val name: String,
    val patronymic: String,
    val dateOfBirth: Long,
    val sex: Boolean,
    val email: String
)