package com.park9eon.micro.auth

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@ComponentScan
@SpringBootApplication
@EnableAutoConfiguration(exclude = [WebMvcAutoConfiguration::class])
open class Application

fun main(args: Array<String>) {
    System.setProperty("spring.devtools.restart.enabled", "false")
    runApplication<Application>(*args)
}