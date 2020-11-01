package com.belyakov.loginform.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.belyakov.loginform.R
import com.belyakov.loginform.utils.isShowing
import com.belyakov.loginform.utils.showToast

class MainActivity : AppCompatActivity() {

    private var toast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBackPressedListener()
    }

    private fun setupBackPressedListener() {
        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (toast.isShowing) {
                    finish()
                } else {
                    toast = showToast(getString(R.string.exit_hint))
                }
            }
        })
    }

    override fun onStart() {
        super.onStart()
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
}