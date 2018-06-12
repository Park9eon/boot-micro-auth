package com.park9eon.micro.auth.dto

import com.park9eon.micro.auth.domain.User
import io.reactivex.Flowable
import io.reactivex.Maybe
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.reactive.RxJava2CrudRepository

interface UserRepository : RxJava2CrudRepository<User, Long> {

    fun findByUsername(username: String): Maybe<User>

    fun findAll(pageable: Pageable): Flowable<User>

}
