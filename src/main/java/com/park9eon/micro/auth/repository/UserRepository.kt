package com.park9eon.micro.auth.repository

import com.park9eon.micro.auth.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): User?
}
