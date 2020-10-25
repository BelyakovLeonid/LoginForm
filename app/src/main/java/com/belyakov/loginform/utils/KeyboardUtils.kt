package com.belyakov.loginform.utils

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment


fun Fragment.showKeyboard() {
    val imm: InputMethodManager? =
        requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}