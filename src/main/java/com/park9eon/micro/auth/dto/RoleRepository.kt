package com.park9eon.micro.auth.dto

import com.park9eon.micro.auth.domain.Role
import org.springframework.data.repository.reactive.RxJava2CrudRepository

interface RoleRepository : RxJava2CrudRepository<Role, Long>
