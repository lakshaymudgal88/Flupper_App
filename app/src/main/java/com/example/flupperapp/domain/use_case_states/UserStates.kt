package com.example.flupperapp.domain.use_case_states

import com.example.flupperapp.domain.use_cases.GetUserDetailsUseCase
import com.example.flupperapp.domain.use_cases.InsertUserDetailsUseCase

/**
 * This class is used if we have more than one usecases for one screen
 * So that it is easy for us to integrate it in viewmodel
 */
data class UserStates(
    val getUserDetailsUseCase: GetUserDetailsUseCase,
    val insertUserDetailsUseCase: InsertUserDetailsUseCase,
)
