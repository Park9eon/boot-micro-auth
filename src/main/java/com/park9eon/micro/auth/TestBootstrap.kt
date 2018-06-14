package com.park9eon.micro.auth

import com.park9eon.micro.auth.domain.User
import com.park9eon.micro.auth.model.UserDto
import com.park9eon.micro.auth.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("test")
open class TestBootstrap : CommandLineRunner {

    private val logger = LoggerFactory.getLogger(Bootstrap::class.java)

    @Autowired
    lateinit var userService: UserService

    override fun run(vararg args: String?) {
        val testUsername = "user@test.com"
        val testPassword = "123123"
        val user: User? = this.userService.getOneByUsername(testUsername)
        if (user == null) {
            this.userService.create(UserDto(null, testUsername, testPassword))
            logger.debug("[$user] is created!")
        } else {
            logger.debug("[$user] is existed!")
        }
    }
}