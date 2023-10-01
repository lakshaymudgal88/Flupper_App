package com.example.flupperapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("user")
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int? = 1,
    val name: String = "",
    val emailId: String = "",
    val password: String = "",
    val phoneNumber: String = "",
)