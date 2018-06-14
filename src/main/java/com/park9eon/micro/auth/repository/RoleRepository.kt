package com.park9eon.micro.auth.repository

import com.park9eon.micro.auth.domain.Role
import org.springframework.data.repository.CrudRepository

interface RoleRepository : CrudRepository<Role, Long> {
    fun findByAuthority(authority: String): Role?
}
