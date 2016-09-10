package com.nomaddev.server.controller

import com.nomaddev.server.manga.entity.User
import org.springframework.web.bind.annotation.*

@RestController
class UserController {

    @RequestMapping(value = "/users/{username}", method = arrayOf(RequestMethod.GET))
    fun getUserInfo(@PathVariable("username") username: String) {

    }

    @RequestMapping(value = "/users", method = arrayOf(RequestMethod.POST))
    fun createUser(@RequestBody user: User) {

    }

    @RequestMapping(value = "/users", method = arrayOf(RequestMethod.PUT))
    fun updateUser(@RequestBody user: User) {

    }
}