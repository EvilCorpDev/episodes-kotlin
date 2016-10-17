package com.nomaddev.server.repository

import com.nomaddev.server.manga.entity.EpisodesUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Repository

@Repository
open class UserOperations @Autowired constructor(val mongoOps: MongoTemplate) {

    fun getUser(username: String): EpisodesUser {
        return EpisodesUser()
    }

    fun createUser(user: EpisodesUser) {

    }
}