package com.nameisjayant.data.repository

import com.nameisjayant.db.DatabaseConnection
import com.nameisjayant.features.user.domain.modal.User
import com.nameisjayant.features.user.domain.repository.UserRepository
import org.bson.Document

class UserRepositoryImp(
    private val db: DatabaseConnection
) : UserRepository {
    override suspend fun createUser(user: User?): User? {
        try {
            val isInserted = db.userCollection.insertOne(user ?: User()).wasAcknowledged()
            if (isInserted)
                return user
        } catch (_: Exception) {
            return null
        }
        return null
    }

    override suspend fun loginUser(user: User?): User? {
        try {
            val query = Document("email", user?.email)
            return db.userCollection.find(query).first()
        } catch (_: Exception) {
            return null
        }
    }

    override suspend fun deleteUser(id: String?): Long? {
        return 0
    }
}