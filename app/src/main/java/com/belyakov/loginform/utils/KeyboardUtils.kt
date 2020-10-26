package com.belyakov.loginform.utils

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment


fun Fragment.showKeyboard() {
    (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)
        ?.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun Fragment.hideKeyboard() {
    (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)
        ?.hideSoftInputFromWindow(view?.windowToken, 0)
}