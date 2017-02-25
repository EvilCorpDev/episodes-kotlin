package com.nomaddev.server.controller

import com.nomaddev.server.manga.entity.EpisodesUser
import com.nomaddev.server.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class UserController
@Autowired constructor(val userService: UserService){

    @RequestMapping(value = "/users/profile/{username}", method = arrayOf(RequestMethod.GET))
    fun getUserInfo(@PathVariable("username") username: String) {
        userService.getUser(username)
    }

    @RequestMapping(value = "/users", method = arrayOf(RequestMethod.POST))
    fun createUser(@RequestBody user: EpisodesUser) {
        userService.createUser(user)
    }

    @RequestMapping(value = "/users/profile", method = arrayOf(RequestMethod.PUT))
    fun updateUser(@RequestBody user: EpisodesUser) {
    }
}