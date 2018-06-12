package com.park9eon.micro.auth.controller

import com.park9eon.micro.auth.domain.User
import com.park9eon.micro.auth.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
open class UserController {

    @Autowired
    lateinit var userService: UserService

    @GetMapping
    fun index(@RequestParam(required = false, defaultValue = "0") offset: Int,
              @RequestParam(required = false, defaultValue = "10") size: Int): Page<User> {
        return this.userService.getAll(offset, size)
    }

    @GetMapping("/{id}")
    fun show(@PathVariable id: Long): User? {
        return this.userService.getOne(id)
    }

    @PostMapping
    fun save(@RequestBody user: User): ResponseEntity<User> {
        return ResponseEntity(this.userService.save(user), HttpStatus.CREATED)
    }

    @PutMapping
    fun update(@RequestBody user: User): User {
        return this.userService.save(user)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Unit> {
        return ResponseEntity(this.userService.delete(id), HttpStatus.OK)
    }
}
