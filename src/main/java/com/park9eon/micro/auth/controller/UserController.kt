package com.park9eon.micro.auth.controller

import com.park9eon.micro.auth.domain.User
import com.park9eon.micro.auth.model.UserCommand
import com.park9eon.micro.auth.model.UserDto
import com.park9eon.micro.auth.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/user")
open class UserController {

    @Autowired
    private lateinit var userService: UserService

    @GetMapping
    fun index(@RequestParam(required = false, defaultValue = "0") offset: Int,
              @RequestParam(required = false, defaultValue = "10") size: Int): Page<User> {
        return this.userService.getAll(offset, size)
    }

    @GetMapping
    fun me(@AuthenticationPrincipal user: User): User {
        return user
    }

    @GetMapping("/{id}")
    fun show(@PathVariable id: Long): User? {
        return this.userService.getOne(id)
    }

    @PostMapping
    fun create(@Valid @RequestBody userCommand: UserCommand, errors: Errors): ResponseEntity<Any> {
        val errorResult = this.validUserCommand(userCommand, errors)
        return if (errorResult == null) {
            ResponseEntity(this.userService.create(UserDto(userCommand)), HttpStatus.CREATED)
        } else {
            ResponseEntity.badRequest()
                    .body(errorResult)
        }
    }

    @PutMapping
    fun update(@Valid @RequestBody userCommand: UserCommand, errors: Errors): ResponseEntity<Any> {
        val errorResult = this.validUserCommand(userCommand, errors)
        return if (errorResult == null) {
            ResponseEntity(this.userService.update(UserDto(userCommand)), HttpStatus.CREATED)
        } else {
            ResponseEntity.badRequest()
                    .body(errorResult)
        }
    }

    private fun validUserCommand(userCommand: UserCommand, errors: Errors): Errors? {
        return if ((userCommand.username != null && errors.hasFieldErrors("username")) ||
                (userCommand.password != null && errors.hasFieldErrors("password"))) {
            errors
        } else {
            null
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Unit> {
        return ResponseEntity.ok(this.userService.delete(id))
    }
}
