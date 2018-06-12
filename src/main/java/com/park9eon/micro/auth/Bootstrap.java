package com.park9eon.micro.auth;

import com.park9eon.micro.auth.domain.Role;
import com.park9eon.micro.auth.dto.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private RoleRepository roleRepository;

    @Autowired
    Bootstrap(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {
        Role roleUser = new Role();
    }
}
