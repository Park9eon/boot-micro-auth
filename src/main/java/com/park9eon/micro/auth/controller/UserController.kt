package com.park9eon.micro.auth.controller

import com.park9eon.micro.auth.service.UserService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
open class UserController(private val userService: UserService) {
}
