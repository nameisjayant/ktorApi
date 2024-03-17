package com.nameisjayant.features.user.domain.modal

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId


data class User(
    @BsonId
    val id: String? = ObjectId().toString(),
    val email:String? = null,
    val password:String? = null
)