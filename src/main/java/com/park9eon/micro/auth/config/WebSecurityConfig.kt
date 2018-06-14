package com.park9eon.micro.auth.config

import com.park9eon.micro.auth.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.csrf.CookieCsrfTokenRepository

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
open class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var userService: UserService

    @Bean
    open fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    override fun configure(http: HttpSecurity) {
        // @formatter:off
        http.requestMatchers()
                .antMatchers("/", "/login**")
                .and()
            .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
            .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
            .logout()
                .logoutUrl("/logout")
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
        // @formatter:on
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(this.userService)
                .passwordEncoder(bCryptPasswordEncoder())
    }
}
