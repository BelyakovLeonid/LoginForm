package com.belyakov.loginform.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.belyakov.loginform.R
import com.belyakov.loginform.data.Profile
import com.belyakov.loginform.utils.observe
import com.belyakov.loginform.utils.toDateString
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
            populateProfileInfo(profile)
        }
    }

    private fun populateProfileInfo(profile: Profile) {
        populateFio(profile.surname, profile.name, profile.patronymic)
        populateBirthDate(profile.dateOfBirth.toDateString())
        populateSex(profile.sex)
        populateEmail(profile.email)
    }

    private fun populateFio(surname: String, name: String, patronymic: String) {
        fioText.text = getString(R.string.fio_template, surname, name, patronymic)
    }

    private fun populateBirthDate(dateString: String) {
        birthText.text = getString(R.string.birth_template, dateString)
    }

    private fun populateSex(isMan: Boolean) {
        sexText.text = getString(
            R.string.sex_template,
            if (isMan) getString(R.string.sex_m) else getString(R.string.sex_f)
        )
    }

    private fun populateEmail(email: String) {
        emailText.text = getString(R.string.email_template, email)
    }
}
