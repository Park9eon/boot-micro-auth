package com.park9eon.micro.auth.service

import com.park9eon.micro.auth.domain.User
import com.park9eon.micro.auth.dto.RoleRepository
import com.park9eon.micro.auth.dto.UserRepository
import com.park9eon.micro.auth.dto.UserRoleRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
open class UserService(private val userRepository: UserRepository,
            private val userRoleRepository: UserRoleRepository,
            private val roleRepository: RoleRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        return this.userRepository.findByUsername(username)
                .blockingGet()
    }

    fun getAll(offset: Int): Flowable<User> {
        return this.getAll(offset, 10)
    }

    fun getAll(offset: Int, size: Int): Flowable<User> {
        return this.userRepository.findAll(PageRequest.of(offset, size))
    }

    fun getOne(username: String): Maybe<User> {
        return this.userRepository.findByUsername(username)
    }

    fun getOne(id: Long?): Maybe<User> {
        return this.userRepository.findById(id!!)
    }

    fun save(user: User): Single<User> {
        return this.userRepository.save(user)
    }

    fun delete(id: Long?): Completable {
        return this.userRepository.deleteById(id!!)
    }
}
