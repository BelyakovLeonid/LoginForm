package com.belyakov.loginform.ui.registration

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.belyakov.loginform.R
import com.belyakov.loginform.utils.observe
import com.belyakov.loginform.utils.toDateString
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.f_registration.*
import java.util.*


class RegistrationFragment : Fragment(R.layout.f_registration) {

    private val viewModel: RegistrationViewModel by viewModels()
    private var fieldsForValidation: List<ValidatedField>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleView()
        observeViewModel()
    }

    private fun handleView() {
        setupDropDownSexMenu()
        setupFieldsForValidation()
        registrateButton.setOnClickListener {
            validateFields()
        }
        backButton.setOnClickListener {
            findNavController().navigateUp()
        }
        birthInputText.setOnClickListener {
            showDateDialog()
        }
    }

    private fun setupDropDownSexMenu() {
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.v_dropdown_item,
            listOf(getString(R.string.sex_m), getString(R.string.sex_f))
        )
        sexInputText.setAdapter(adapter)
    }

    private fun setupFieldsForValidation() {
        fieldsForValidation = listOf(
            ValidatedField(fioInput, getString(R.string.fio_error)),
            ValidatedField(birthInput, getString(R.string.birth_error)),
            ValidatedField(sexInput, getString(R.string.sex_error)),
            ValidatedField(emailInput, getString(R.string.email_error))
        )
    }

    private fun validateFields() {
        fieldsForValidation?.forEach { it.input.error = null }
        val notValidFields = fieldsForValidation?.filter { it.input.editText?.text.isNullOrEmpty() }
        if (notValidFields.isNullOrEmpty()) {
            registerUser()
        } else {
            notValidFields.forEach { it.input.error = it.errorMessage }
        }
    }

    private fun registerUser() {
        viewModel.onRegisterClicked(
            fioInputText.text.toString(),
            sexInputText.text.toString(),
            emailInputText.text.toString()
        )
    }

    private fun showDateDialog() {
        Calendar.getInstance().let {
            val dateListener = DatePickerDialog.OnDateSetListener { _, y, m, d ->
                it.set(Calendar.YEAR, y)
                it.set(Calendar.MONTH, m)
                it.set(Calendar.DAY_OF_MONTH, d)
                birthInputText.setText(it.toDateString())
                viewModel.onBirthDatePicked(it.timeInMillis)
            }

            DatePickerDialog(
                requireContext(),
                dateListener,
                it.get(Calendar.YEAR),
                it.get(Calendar.MONTH),
                it.get(Calendar.DAY_OF_MONTH)
            ).apply {
                window?.setFlags(
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                )
                show()
            }
        }
    }

    private fun observeViewModel() {
        observe(viewModel.isUserRegistered) {
            findNavController().navigate(R.id.action_registrationFragment_to_profileFragment)
        }
    }

    private data class ValidatedField(
        val input: TextInputLayout,
        val errorMessage: String
    )
}