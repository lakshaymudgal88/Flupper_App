package com.example.flupperapp.data.repository

import com.example.flupperapp.data.RoomDbDao
import com.example.flupperapp.data.entities.UserEntity
import com.example.flupperapp.domain.repository.UserRepository
import javax.inject.Inject

/**
 * This class is directly deal with our local Room Database
 */
class UserRepositoryImpl @Inject constructor(
    private val roomDbDao: RoomDbDao
) : UserRepository {
    override suspend fun insertUserDetails(userEntity: UserEntity) {
        return roomDbDao.insertUserDetails(userEntity)
    }

    override suspend fun getUserDetails(): UserEntity {
        return roomDbDao.getUserDetails()
    }

}