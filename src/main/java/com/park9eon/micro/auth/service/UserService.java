package com.park9eon.micro.auth.service;

import com.park9eon.micro.auth.domain.User;
import com.park9eon.micro.auth.dto.RoleRepository;
import com.park9eon.micro.auth.dto.UserRepository;
import com.park9eon.micro.auth.dto.UserRoleRepository;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       UserRoleRepository userRoleRepository,
                       RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .blockingGet();
    }

    public Flowable<User> getAll(int offset) {
        return this.getAll(offset, 10);
    }

    public Flowable<User> getAll(int offset, int size) {
        return this.userRepository.findAll(PageRequest.of(offset, size));
    }

    public Maybe<User> getOne(String username) {
        return this.userRepository.findByUsername(username);
    }

    public Maybe<User> getOne(Long id) {
        return this.userRepository.findById(id);
    }
    
    public Single<User> save(User user) {
        return this.userRepository.save(user);
    }

    public Completable delete(Long id) {
        return this.userRepository.deleteById(id);
    }
}
