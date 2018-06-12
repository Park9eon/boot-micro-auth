package com.park9eon.micro.auth.config

import com.park9eon.micro.auth.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore

@Configuration
@EnableAuthorizationServer
open class AuthorizationServerConfig(private val userService: UserService,
                                private val authenticationManager: AuthenticationManager) : AuthorizationServerConfigurerAdapter() {

    @Bean
    open fun redisTokenStore(): TokenStore {
        return RedisTokenStore(connectionFactory())
    }

    @Bean
    open fun connectionFactory(): LettuceConnectionFactory {
        return LettuceConnectionFactory()
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer?) {
        endpoints!!.tokenStore(redisTokenStore())
                .authenticationManager(this.authenticationManager)
                .userDetailsService(this.userService)
    }
}
