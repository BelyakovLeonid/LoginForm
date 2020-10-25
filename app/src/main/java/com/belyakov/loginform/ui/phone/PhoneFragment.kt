package com.belyakov.loginform.ui.phone

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.belyakov.loginform.R
import com.belyakov.loginform.ui.phone.VerificationResult.*
import com.belyakov.loginform.utils.observeEvent
import com.belyakov.loginform.utils.showKeyboard
import kotlinx.android.synthetic.main.f_phone.*


class PhoneFragment : Fragment(R.layout.f_phone) {

    private val viewModel: PhoneViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleView()
        observeViewModel()
    }

    private fun handleView() {
        linkText.movementMethod = LinkMovementMethod.getInstance()
        phoneInputText.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                showKeyboard()
                phoneInputText.hint = getString(R.string.phone_start)
            } else {
                phoneInputText.hint = null
            }
        }
        nextButton.setOnClickListener {
            nextButton.isEnabled = false
            viewModel.verifyPhone(phoneInputText.text.toString())
        }
    }

    private fun observeViewModel() {
        observeEvent(viewModel.verificationResult, ::handleVerificationResult)
    }

    private fun handleVerificationResult(result: VerificationResult) {
        nextButton.isEnabled = true
        when (result) {
            is VerificationFailed -> phoneInput.error = getString(R.string.phone_error)
            is VerificationCompleted,
            is VerificationCodeSent -> {
                phoneInput.error = null
                findNavController().navigate(R.id.action_phoneFragment_to_codeFragment)
            }
        }
    }

}