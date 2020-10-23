package com.belyakov.loginform.ui.code

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.belyakov.loginform.R
import com.belyakov.loginform.utils.observe
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
            viewModel.checkCode(11)
        }
    }

    private fun observeViewModel() {
        observe(viewModel.confirmationResult) {
            when (it) {
                ConfirmationResult.NOT_REGISTERED -> findNavController().navigate(R.id.action_codeFragment_to_registrationFragment)
                ConfirmationResult.CONFIRMED -> findNavController().navigate(R.id.action_codeFragment_to_profileFragment)
                else -> showConfirmationError()
            }
        }
    }

    private fun showConfirmationError() {

    }
}