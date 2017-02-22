package com.nomaddev.server.services.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

class EpisodeUserDetails
constructor(username: String, password: String, authorities: MutableCollection<out GrantedAuthority?>):
        User(username, password, authorities)