package com.nomaddev.config

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter


open class ServerSecurityConfig: WebSecurityConfigurerAdapter() {

    override override fun configure(auth: AuthenticationManagerBuilder?) {
        super.configure(auth)
    }
}