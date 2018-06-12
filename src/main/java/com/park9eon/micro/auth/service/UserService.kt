package com.park9eon.micro.auth.service

import com.park9eon.micro.auth.domain.User
import com.park9eon.micro.auth.dto.RoleRepository
import com.park9eon.micro.auth.dto.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
open class UserService : UserDetailsService {

    @Autowired
    private lateinit var userRepository: UserRepository
    @Autowired
    private lateinit var roleRepository: RoleRepository

    override fun loadUserByUsername(username: String): UserDetails {
        return this.userRepository.findByUsername(username) ?: throw UsernameNotFoundException(username)
    }

    fun getAll(offset: Int = 0, size: Int = 10): Page<User> {
        return this.userRepository.findAll(PageRequest.of(offset, size))
    }

    fun getOne(id: Long): User? {
        return this.userRepository.getOne(id)
    }

    fun save(user: User): User {
        return this.userRepository.save(user)
    }

    fun delete(id: Long) {
        this.userRepository.deleteById(id)
    }
}
