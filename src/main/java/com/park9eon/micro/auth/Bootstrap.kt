package com.park9eon.micro.auth

import com.park9eon.micro.auth.domain.Role
import com.park9eon.micro.auth.domain.User
import com.park9eon.micro.auth.model.UserDto
import com.park9eon.micro.auth.repository.RoleRepository
import com.park9eon.micro.auth.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
open class Bootstrap : CommandLineRunner {

    private val logger = LoggerFactory.getLogger(Bootstrap::class.java)

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var roleRepository: RoleRepository

    override fun run(vararg args: String) {
        this.roleRepository.findByAuthority(Role.ROLE_USER)
                ?: this.roleRepository.save(Role(Role.ROLE_USER))
        this.roleRepository.findByAuthority(Role.ROLE_ADMIN)
                ?: this.roleRepository.save(Role(Role.ROLE_ADMIN))

        val testUsername = "user"
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