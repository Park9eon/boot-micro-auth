package com.park9eon.micro.auth

import com.park9eon.micro.auth.domain.Role
import com.park9eon.micro.auth.domain.User
import com.park9eon.micro.auth.dto.RoleRepository
import com.park9eon.micro.auth.service.UserService
import io.reactivex.Flowable
import io.reactivex.Observable
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.util.*

@Component
open class Bootstrap : CommandLineRunner {

    private val logger = LoggerFactory.getLogger(Bootstrap::class.java)

    @Autowired
    lateinit var userService: UserService
    @Autowired
    lateinit var roleRepository: RoleRepository

    override fun run(vararg args: String) {
        val roleUser = Role("ROLE_USER")
        val roleAdmin = Role("ROLE_ADMIN")
        val user = User(null, "user", "123123", setOf(roleUser, roleAdmin))
        this.roleRepository.save(roleUser)
        this.roleRepository.save(roleAdmin)
        this.userService.save(user)

        logger.debug("[$user] is created!")
    }
}