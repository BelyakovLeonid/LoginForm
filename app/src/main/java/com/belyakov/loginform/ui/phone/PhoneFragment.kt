package com.belyakov.loginform.ui.phone

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.belyakov.loginform.R
import kotlinx.android.synthetic.main.f_phone.*


class PhoneFragment : Fragment(R.layout.f_phone) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        linkText.movementMethod = LinkMovementMethod.getInstance()
        nextButton.setOnClickListener {
            findNavController().navigate(R.id.action_phoneFragment_to_codeFragment)
        }
    }
}