package com.example.flupperapp.presentation.register_user

sealed class RegisterScreenUIEvent {
    data class EnterName(val name: String): RegisterScreenUIEvent()
    data class EnterEmail(val email: String): RegisterScreenUIEvent()
    data class EnterPassword(val password: String): RegisterScreenUIEvent()
    data class EnterPhoneNumber(val phoneNumber: String): RegisterScreenUIEvent()
    object ClickOnSaveButton : RegisterScreenUIEvent()
    object ClickOnViewDetailsButton : RegisterScreenUIEvent()
}
