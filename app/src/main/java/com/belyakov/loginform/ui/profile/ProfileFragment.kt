package com.belyakov.loginform.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.belyakov.loginform.R
import kotlinx.android.synthetic.main.f_profile.*


class ProfileFragment : Fragment(R.layout.f_profile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleView()
    }

    private fun handleView() {
        exitButton.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_phoneFragment)
        }
    }
}