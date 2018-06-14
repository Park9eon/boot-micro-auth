package com.park9eon.micro.auth.service

import com.park9eon.micro.auth.domain.Role
import com.park9eon.micro.auth.domain.User
import com.park9eon.micro.auth.model.UserDto
import com.park9eon.micro.auth.repository.RoleRepository
import com.park9eon.micro.auth.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
open class UserService : UserDetailsService {

    @Autowired
    private lateinit var userRepository: UserRepository
    @Autowired
    private lateinit var roleRepository: RoleRepository
    @Autowired
    private lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    override fun loadUserByUsername(username: String): UserDetails {
        return this.getOneByUsername(username) ?: throw UsernameNotFoundException(username)
    }

    fun getAll(offset: Int = 0, size: Int = 10): Page<User> {
        return this.userRepository.findAll(PageRequest.of(offset, size))
    }

    fun getOneByUsername(username: String): User? {
        return this.userRepository.findByUsername(username)
    }

    fun create(userDto: UserDto): User {
        val user = User()
        user.username = userDto.username
        user.password = this.bCryptPasswordEncoder.encode(userDto.password)
        user.roles = setOf(this.roleRepository.findByAuthority(Role.ROLE_USER))
        return this.userRepository.save(user)
    }

    fun update(userDto: UserDto): User {
        val user = userDto.username?.let { this.userRepository.findByUsername(it) }
                ?: throw UsernameNotFoundException("")
        if (userDto.password != null) {
            user.password = this.bCryptPasswordEncoder.encode(userDto.password)
        }
        return this.userRepository.save(user)
    }

    fun getOne(id: Long): User? {
        return this.userRepository.getOne(id)
    }

    fun delete(id: Long) {
        this.userRepository.deleteById(id)
    }
}
