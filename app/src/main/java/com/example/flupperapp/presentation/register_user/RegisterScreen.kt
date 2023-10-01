package com.example.flupperapp.presentation.register_user

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.flupperapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterScreen : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val registerScreenViewModel by viewModels<RegisterScreenViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleClicks()
        handleViewModelStates()
    }

    /**
     * This function contains the button clicks handling
     */
    private fun handleClicks() {
        binding.registerButton.setOnClickListener {
            checkValidFields()
            registerScreenViewModel.onEvent(RegisterScreenUIEvent.ClickOnSaveButton)
        }
        binding.viewButton.setOnClickListener {
            registerScreenViewModel.onEvent(RegisterScreenUIEvent.ClickOnViewDetailsButton)
        }
    }

    /**
     * This function is used to check if our any field is empty or not when we click on Save Button
     */
    private fun checkValidFields() {
        registerScreenViewModel.onEvent(RegisterScreenUIEvent.EnterName(binding.etName.text.toString()))
        registerScreenViewModel.onEvent(RegisterScreenUIEvent.EnterEmail(binding.etEmail.text.toString()))
        registerScreenViewModel.onEvent(RegisterScreenUIEvent.EnterPassword(binding.etPassword.text.toString()))
        registerScreenViewModel.onEvent(RegisterScreenUIEvent.EnterPhoneNumber(binding.etPhone.text.toString()))
    }

    /**
     * This function is used to handle the stateflow from the viewmodel in the screen
     * like fetching the data from database,
     * get the status if data is inserted or not
     */
    private fun handleViewModelStates() {
        lifecycleScope.launch {
            registerScreenViewModel.getUserDetailsState.collectLatest { result ->
                result.data?.let { userDetails ->
                    binding.etName.setText(userDetails.name)
                    binding.etEmail.setText(userDetails.emailId)
                    binding.etPassword.setText(userDetails.password)
                    binding.etPhone.setText(userDetails.phoneNumber)
                }
            }
        }

        lifecycleScope.launch {
            registerScreenViewModel.insertUserState.collectLatest { result ->
                result.message?.let {
                    if (it.isNotEmpty()) {
                        Snackbar.make(binding.registerButton, result.message, Snackbar.LENGTH_SHORT)
                            .show()
                    }
                }
                result.data?.let {
                    if (it.isNotEmpty()) {
                        Snackbar.make(binding.registerButton, result.data, Snackbar.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

}