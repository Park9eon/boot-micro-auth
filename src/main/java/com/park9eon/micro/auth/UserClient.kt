package com.park9eon.micro.auth

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.GET

@FeignClient("user")
interface UserClient : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        return this.findByUsername(username)
    }

    @RequestMapping(method = [GET], name = "/{username}")
    fun findByUsername(@PathVariable username: String): User

}