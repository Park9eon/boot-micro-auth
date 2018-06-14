package com.park9eon.micro.auth.config

import com.park9eon.micro.auth.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore

@Configuration
@EnableAuthorizationServer
open class AuthorizationServerConfig : AuthorizationServerConfigurerAdapter() {

    @Autowired
    private lateinit var userService: UserService
    @Autowired
    @Qualifier("authenticationManagerBean")
    private lateinit var authenticationManager: AuthenticationManager

    @Bean
    open fun tokenStore(): TokenStore {
        return RedisTokenStore(connectionFactory())
    }

    @Bean
    open fun connectionFactory(): LettuceConnectionFactory {
        return LettuceConnectionFactory()
    }

    override fun configure(oauthServer: AuthorizationServerSecurityConfigurer) {
        oauthServer.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        endpoints.tokenStore(tokenStore())
                .authenticationManager(this.authenticationManager)
                .userDetailsService(this.userService)
    }

    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients.inMemory()
                .withClient("client")
                .secret("123123")
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("read", "write")
                .authorities("ROLE_CLIENT")
    }

}
