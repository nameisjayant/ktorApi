package com.nameisjayant.db

import com.nameisjayant.features.user.domain.modal.User
import com.nameisjayant.utils.Constant
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

class DatabaseConnection {

    private val client = KMongo.createClient().coroutine
    private val database = client.getDatabase(System.getenv(Constant.DATABASE_NAME))
    val userCollection = database.getCollection<User>()
}