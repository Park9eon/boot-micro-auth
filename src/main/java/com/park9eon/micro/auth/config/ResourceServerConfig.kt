package com.park9eon.micro.auth.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter

@Configuration
@EnableResourceServer
open class ResourceServerConfig : ResourceServerConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        // @formatter:off
        http.authorizeRequests()
                .antMatchers("/oauth/token")
                .permitAll()
        // @formatter:on
    }
}