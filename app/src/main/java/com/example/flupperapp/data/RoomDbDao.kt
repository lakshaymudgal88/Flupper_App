package com.example.flupperapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.flupperapp.data.entities.UserEntity

@Dao
interface RoomDbDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserDetails(userEntity: UserEntity)

    @Query("select * from user where id =:id")
    suspend fun getUserDetails(id: Int = 1): UserEntity
}