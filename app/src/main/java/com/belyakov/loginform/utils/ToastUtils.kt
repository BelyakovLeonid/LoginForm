package com.belyakov.loginform.utils

import android.content.Context
import android.widget.Toast


val Toast?.isShowing
    get() = this?.view?.isShown == true

fun Context.showToast(string: String): Toast =
    Toast.makeText(this, string, Toast.LENGTH_LONG).also {
        it.show()
    }