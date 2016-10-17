package com.nomaddev.config.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore

@Configuration
@EnableAuthorizationServer
open class AuthServerOAuth2Config
@Autowired constructor(val tokenStore: TokenStore,
                       val userApprovalHandler: UserApprovalHandler?) : AuthorizationServerConfigurerAdapter() {

    @Autowired
    @Qualifier("authenticationManagerBean")
    lateinit var authenticationManager: AuthenticationManager

    private val REALM="EPISODES_REALM"

    override fun configure(security: AuthorizationServerSecurityConfigurer?) {
        security?.realm(REALM+"/client")
    }

    override fun configure(clients: ClientDetailsServiceConfigurer?) {
        if(clients == null) return
        clients.inMemory()
                .withClient("episodes-client")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
                .scopes("read", "write", "trust")
                .secret("secret")
                .accessTokenValiditySeconds(120)
                .refreshTokenValiditySeconds(600)
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer?) {
        endpoints?.tokenStore(tokenStore)?.userApprovalHandler(userApprovalHandler)
                ?.authenticationManager(authenticationManager)
    }
}