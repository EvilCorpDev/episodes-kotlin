package com.nomaddev.config.security

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler

@Configuration
@EnableResourceServer
open class ResourceServerConfiguration: ResourceServerConfigurerAdapter() {

    private val RESOURCE_ID = "episodes_api"

    override fun configure(resources: ResourceServerSecurityConfigurer) {
        resources.resourceId(RESOURCE_ID).stateless(false)
    }

    override fun configure(http: HttpSecurity) {
        http.anonymous().disable().requestMatchers().antMatchers("/profile/**", "/episodes/**").and()
                .authorizeRequests().antMatchers("/users/**", "/episodes/**").access("hasRole('ADMIN')")
                .and().exceptionHandling().accessDeniedHandler(OAuth2AccessDeniedHandler())
    }
}