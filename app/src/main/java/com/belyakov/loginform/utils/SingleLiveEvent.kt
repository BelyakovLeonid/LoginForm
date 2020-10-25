package com.belyakov.loginform.utils

import androidx.lifecycle.LifecycleOwner

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean


class SingleLiveEvent<T> : MutableLiveData<T?>() {
    private val mPending: AtomicBoolean = AtomicBoolean(false)

    fun observeEvent(owner: LifecycleOwner, observer: Observer<T>) {
        super.observe(owner, {
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(it)
            }
        })
    }

    override fun setValue(value: T?) {
        mPending.set(true)
        super.setValue(value)
    }

    override fun postValue(value: T?) {
        mPending.set(true)
        super.postValue(value)
    }
}