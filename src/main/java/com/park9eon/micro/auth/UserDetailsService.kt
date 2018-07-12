package com.park9eon.micro.auth

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsService: UserDetailsService {

    @Autowired
    lateinit var userClient: UserClient

    override fun loadUserByUsername(username: String): UserDetails {
        return this.userClient.findByUsername(username)
    }
}