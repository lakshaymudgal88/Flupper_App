package com.example.flupperapp.di

import android.content.Context
import androidx.room.Room
import com.example.flupperapp.constants.AppConstants.ROOM_DB_NAME
import com.example.flupperapp.data.RoomDbDao
import com.example.flupperapp.data.RoomDbSchema
import com.example.flupperapp.data.repository.UserRepositoryImpl
import com.example.flupperapp.domain.repository.UserRepository
import com.example.flupperapp.domain.use_case_states.UserStates
import com.example.flupperapp.domain.use_cases.GetUserDetailsUseCase
import com.example.flupperapp.domain.use_cases.InsertUserDetailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRoomDbDao(@ApplicationContext context: Context): RoomDbDao {
        return Room.databaseBuilder(
            context,
            RoomDbSchema::class.java,
            ROOM_DB_NAME
        ).build().getRoomDao()
    }

    @Provides
    @Singleton
    fun provideUserRepository(roomDbDao: RoomDbDao): UserRepository {
        return UserRepositoryImpl(roomDbDao)
    }

    @Provides
    @Singleton
    fun provideUserStates(userRepository: UserRepository): UserStates {
        return UserStates(
            getUserDetailsUseCase = GetUserDetailsUseCase(userRepository),
            insertUserDetailsUseCase = InsertUserDetailsUseCase(userRepository),
        )
    }
}