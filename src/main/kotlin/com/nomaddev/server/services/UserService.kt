package com.nomaddev.server.services

import com.nomaddev.server.manga.entity.EpisodesUser
import com.nomaddev.server.repository.UserOperations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService @Autowired constructor(val userOps: UserOperations){

    fun createUser(user: EpisodesUser) {
        userOps.createUser(user)
    }

    fun getUser(username: String) = userOps.getUser(username)
}