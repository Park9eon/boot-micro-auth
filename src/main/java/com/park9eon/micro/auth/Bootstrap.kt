package com.park9eon.micro.auth

import com.park9eon.micro.auth.dto.RoleRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
open class Bootstrap(private val roleRepository: RoleRepository) : CommandLineRunner {
    override fun run(vararg args: String) {
    }
}