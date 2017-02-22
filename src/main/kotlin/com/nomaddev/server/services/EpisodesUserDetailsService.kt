package com.nomaddev.server.services

import com.nomaddev.server.exception.UserNotFoundException
import com.nomaddev.server.repository.UserOperations
import com.nomaddev.server.services.entity.EpisodeUserDetails
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class EpisodesUserDetailsService
@Autowired constructor(val userOperations: UserOperations) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        if (username == null) throw UserNotFoundException("EpisodesUser with username $username not found")
        val user = userOperations.getUser(username)
        val authorities = arrayListOf<GrantedAuthority>()
        authorities.add(SimpleGrantedAuthority("ROLE_CLIENT"))
        return EpisodeUserDetails(username, user.passHash, authorities)
    }

}