package com.nameisjayant.features.user.domain.repository

import com.nameisjayant.features.user.domain.modal.User
import com.nameisjayant.utils.ApiState
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun createUser(user: User?): Flow<ApiState<User>>?
    suspend fun loginUser(user: User?): User?

    suspend fun deleteUser(id: String?): Boolean?
    suspend fun getAllEmail():List<String>?

    suspend fun updateUser(id: String?,user: User?):Boolean?

    fun release()
}