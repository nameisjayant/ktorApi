package com.nameisjayant.data.repository

import com.nameisjayant.db.DatabaseConnection
import com.nameisjayant.features.user.domain.modal.User
import com.nameisjayant.features.user.domain.repository.UserRepository
import com.nameisjayant.utils.ApiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.bson.Document

class UserRepositoryImp(
    private val db: DatabaseConnection
) : UserRepository {
    override suspend fun createUser(user: User?): Flow<ApiState<User>> = flow {
        try {
            val isInserted = db.userCollection.insertOne(user ?: User()).wasAcknowledged()
            if (isInserted)
                ApiState.Success(user)
            else
                ApiState.Error("User cannot created , Try requesting again")
        } catch (e: Exception) {
            ApiState.Error("${e.message}")
        }
    }

    override suspend fun loginUser(user: User?): User? {
        try {
            val query = Document("email", user?.email)
            return db.userCollection.find(query).first()
        } catch (_: Exception) {
            return null
        }
    }

    override suspend fun deleteUser(id: String?): Boolean {
        return db.userCollection.deleteOneById(id ?: "").deletedCount == 1L
    }

    override suspend fun getAllEmail(): List<String> {
        return db.userCollection.find().toList().map { it.email ?: "" }
    }

    override suspend fun updateUser(id: String?, user: User?): Boolean {
        val query = Document("_id", id)
        val update = Document(
            "\$set",
            Document("email", user?.email)
                .append("password", user?.password)
        )
        val result = db.userCollection.updateOne(query, update)
        return result.modifiedCount == 1L
    }

    override fun release() = db.client.close()
}