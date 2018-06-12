package com.park9eon.micro.auth.dto;

import com.park9eon.micro.auth.domain.User;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.RxJava2CrudRepository;

public interface UserRepository extends RxJava2CrudRepository<User, Long> {

    Maybe<User> findByUsername(String username);

    Flowable<User> findAll(Pageable pageable);

}
