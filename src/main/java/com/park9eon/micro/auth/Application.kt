package com.park9eon.micro.auth

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@ComponentScan
@SpringBootApplication
open class Application

fun main(args: Array<String>) {
    System.setProperty("spring.devtools.restart.enabled", "false")
    SpringApplication.run(Application::class.java, *args)
}