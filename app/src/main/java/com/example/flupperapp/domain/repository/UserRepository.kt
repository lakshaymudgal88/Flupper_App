package com.example.flupperapp.domain.repository

import com.example.flupperapp.data.entities.UserEntity

/**
 * This interface is implemented in UserRespositoryImpl class so that if we want to change the source
 * then we only need to change the ImplRepository.
 * This interface is very useful when we are dealing with test cases
 */
interface UserRepository {

    suspend fun insertUserDetails(userEntity: UserEntity)

    suspend fun getUserDetails(): UserEntity
}