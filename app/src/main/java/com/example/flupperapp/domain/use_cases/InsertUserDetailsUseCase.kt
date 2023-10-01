package com.example.flupperapp.domain.use_cases

import com.example.flupperapp.constants.AppConstants
import com.example.flupperapp.constants.AppConstants.USER_DETAILS_SAVED_SUCCESSFULLY
import com.example.flupperapp.data.entities.UserEntity
import com.example.flupperapp.domain.repository.UserRepository
import com.example.flupperapp.util.CommonResponse
import com.example.flupperapp.util.InvalidInputFieldException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * This class is used to insert the User Details from the Room DB
 */
class InsertUserDetailsUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    @Throws(InvalidInputFieldException::class)
    operator fun invoke(userEntity: UserEntity): Flow<CommonResponse<String>> = flow {
        try {
            if (userEntity.name.isEmpty()) {
                throw InvalidInputFieldException("Name must not be empty!")
            }
            if (userEntity.emailId.isEmpty()) {
                throw InvalidInputFieldException("Email Id must not be empty!")
            }
            if (userEntity.password.isEmpty()) {
                throw InvalidInputFieldException("Password must not be empty!")
            }
            if (userEntity.phoneNumber.isEmpty()) {
                throw InvalidInputFieldException("Phone Number must not be empty!")
            }
            userRepository.insertUserDetails(userEntity)
            emit(CommonResponse.Success(data = USER_DETAILS_SAVED_SUCCESSFULLY))
        } catch (invalidInputFieldException: InvalidInputFieldException) {
            emit(
                CommonResponse.Error(
                    message = invalidInputFieldException.message
                        ?: AppConstants.SOMETHING_WENT_WRONG
                )
            )
        } catch (e: Exception) {
            emit(CommonResponse.Error(message = AppConstants.SOMETHING_WENT_WRONG))
        }
    }
}