package com.belyakov.loginform.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.belyakov.loginform.R
import com.belyakov.loginform.utils.observe
import kotlinx.android.synthetic.main.f_profile.*


class ProfileFragment : Fragment(R.layout.f_profile) {

    private val viewModel: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleView()
        observeViewModel()
    }

    private fun handleView() {
        exitButton.setOnClickListener {
            viewModel.logout()
            findNavController().navigate(R.id.action_profileFragment_to_phoneFragment)
        }
    }

    private fun observeViewModel() {
        observe(viewModel.currentUser) { profile ->
            fioText.text = getString(
                R.string.fio_template,
                profile.secondName,
                profile.name,
                profile.thirdName
            )
        }
    }
}