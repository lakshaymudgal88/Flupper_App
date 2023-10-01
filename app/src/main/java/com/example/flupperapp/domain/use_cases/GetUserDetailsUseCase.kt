package com.example.flupperapp.domain.use_cases

import com.example.flupperapp.domain.repository.UserRepository
import javax.inject.Inject

/**
 * This class is used to fetch the user details from the Room Db
 */
class GetUserDetailsUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke() =
        userRepository.getUserDetails()
}