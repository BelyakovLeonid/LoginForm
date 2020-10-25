package com.belyakov.loginform.ui.registration

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.belyakov.loginform.R
import com.belyakov.loginform.utils.observe
import kotlinx.android.synthetic.main.f_registration.*


class RegistrationFragment : Fragment(R.layout.f_registration) {

    private val viewModel: RegistrationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleView()
        observeViewModel()
    }

    private fun handleView() {
        registrateButton.setOnClickListener {
            registerUser()
        }
        backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun observeViewModel() {
        observe(viewModel.isUserRegistered) {
            findNavController().navigate(R.id.action_registrationFragment_to_profileFragment)
        }
    }

    private fun registerUser() {
        viewModel.onRegisterClicked(
            fioInputText.text.toString(),
            birthInputText.text.toString(),
            sexInputText.text.toString(),
            emailInputText.text.toString()
        )
    }
}