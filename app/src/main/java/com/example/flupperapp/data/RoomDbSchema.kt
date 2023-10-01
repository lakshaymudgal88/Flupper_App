package com.example.flupperapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.flupperapp.data.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class RoomDbSchema : RoomDatabase() {

    abstract fun getRoomDao(): RoomDbDao
}