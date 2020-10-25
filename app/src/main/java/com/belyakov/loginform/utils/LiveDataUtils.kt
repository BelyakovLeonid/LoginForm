package com.belyakov.loginform.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

inline fun <T> LifecycleOwner.observe(liveData: LiveData<T>?, crossinline block: (T) -> Unit) {
    liveData?.observe(this) { block.invoke(it) }
}

inline fun <T> LifecycleOwner.observeEvent(
    event: SingleLiveEvent<T>?,
    crossinline block: (T) -> Unit
) {
    event?.observeEvent(this) { block.invoke(it) }
}
