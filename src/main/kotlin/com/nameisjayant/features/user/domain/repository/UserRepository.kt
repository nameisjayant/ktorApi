package com.nameisjayant.features.user.domain.repository

import com.nameisjayant.features.user.domain.modal.User

interface UserRepository {

    suspend fun createUser(user: User?): User?
    suspend fun loginUser(user: User?): User?

    suspend fun deleteUser(id: String?): Long?

}