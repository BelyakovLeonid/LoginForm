package com.belyakov.loginform.ui.code

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.belyakov.loginform.R
import com.belyakov.loginform.ui.code.ConfirmationResult.*
import com.belyakov.loginform.utils.observeEvent
import kotlinx.android.synthetic.main.f_code.*

class CodeFragment : Fragment(R.layout.f_code) {

    private val viewModel: CodeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleView()
        observeViewModel()
    }

    private fun handleView() {
        backButton.setOnClickListener {
            findNavController().navigateUp()
        }
        nextButton.setOnClickListener {
            nextButton.isEnabled = false
            viewModel.checkCode(codeInputText.text.toString())
        }
    }

    private fun observeViewModel() {
        observeEvent(viewModel.confirmationResult, ::handleVerificationResult)
    }

    private fun handleVerificationResult(result: ConfirmationResult) {
        nextButton.isEnabled = true
        when (result) {
            NOT_REGISTERED -> findNavController().navigate(R.id.action_codeFragment_to_registrationFragment)
            NOT_CONFIRMED -> codeInput.error = getString(R.string.code_error)
            CONFIRMED -> findNavController().navigate(R.id.action_codeFragment_to_profileFragment)
        }
    }
}