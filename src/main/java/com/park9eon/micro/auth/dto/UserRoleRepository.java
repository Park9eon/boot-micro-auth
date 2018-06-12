package com.park9eon.micro.auth.dto;

import com.park9eon.micro.auth.domain.UserRole;
import org.springframework.data.repository.reactive.RxJava2CrudRepository;

public interface UserRoleRepository extends RxJava2CrudRepository<UserRole, Long> {
}
