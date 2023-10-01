package com.example.flupperapp.presentation.register_user

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flupperapp.constants.AppConstants.SOMETHING_WENT_WRONG
import com.example.flupperapp.data.entities.UserEntity
import com.example.flupperapp.domain.use_case_states.UserStates
import com.example.flupperapp.util.CommonResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterScreenViewModel @Inject constructor(
    private val userStates: UserStates,
) : ViewModel() {


    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    private val password = _password.asStateFlow()

    private val _phoneNumber = MutableStateFlow("")
    val phoneNumber = _phoneNumber.asStateFlow()

    private val _insertUserState = MutableSharedFlow<CommonResponse<String>>()
    val insertUserState = _insertUserState.asSharedFlow()

    private val _getUserDetailsState = MutableSharedFlow<CommonResponse<UserEntity>>()
    val getUserDetailsState = _getUserDetailsState.asSharedFlow()

    fun onEvent(registerScreenUIEvent: RegisterScreenUIEvent) {
        when (registerScreenUIEvent) {
            RegisterScreenUIEvent.ClickOnSaveButton -> {
                handleInsertUser()
            }

            RegisterScreenUIEvent.ClickOnViewDetailsButton -> {
                viewModelScope.launch {
                    handleGetUser()
                }
            }

            is RegisterScreenUIEvent.EnterEmail -> {
                _email.value = registerScreenUIEvent.email
            }

            is RegisterScreenUIEvent.EnterName -> {
                _name.value = registerScreenUIEvent.name
            }

            is RegisterScreenUIEvent.EnterPassword -> {
                _password.value = registerScreenUIEvent.password
            }

            is RegisterScreenUIEvent.EnterPhoneNumber -> {
                _phoneNumber.value = registerScreenUIEvent.phoneNumber
            }
        }
    }

    private suspend fun handleGetUser() {
        val userDetails = userStates.getUserDetailsUseCase()
        _getUserDetailsState.emit(CommonResponse.Success(data = userDetails))
    }

    private fun handleInsertUser() {
        userStates.insertUserDetailsUseCase(createUserDetails()).onEach { result ->
            when (result) {
                is CommonResponse.Error -> {
                    _insertUserState.emit(
                        CommonResponse.Error(
                            message = result.message ?: SOMETHING_WENT_WRONG
                        )
                    )
                }

                is CommonResponse.Success -> {
                    _insertUserState.emit(
                        CommonResponse.Success(result.data)
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun createUserDetails() = UserEntity(
        name = name.value,
        emailId = email.value,
        password = password.value,
        phoneNumber = phoneNumber.value
    )
}