package com.nomaddev.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore

open class AuthServerOAuth2Config @Autowired constructor(val authenticationManager: AuthenticationManager,
                                                         val redisFactory: RedisConnectionFactory): AuthorizationServerConfigurerAdapter() {

    override fun configure(security: AuthorizationServerSecurityConfigurer?) {
        security?.tokenKeyAccess("permitAll()")?.checkTokenAccess("isAuthenticated()")
    }

    override fun configure(clients: ClientDetailsServiceConfigurer?) {
        clients?.inMemory()
                ?.withClient("sampleClientId")
                ?.authorizedGrantTypes("implicit")
                ?.scopes("read")
                ?.autoApprove(true)
                ?.and()
                ?.withClient("clientIdPassword")
                ?.secret("secret")
                ?.authorizedGrantTypes(
                        "password", "authorization_code", "refresh_token")
                ?.scopes("read")
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer?) {
        endpoints?.tokenStore(tokenStore())?.authenticationManager(authenticationManager)
    }

    @Bean
    fun tokenStore() = RedisTokenStore(redisFactory)
}