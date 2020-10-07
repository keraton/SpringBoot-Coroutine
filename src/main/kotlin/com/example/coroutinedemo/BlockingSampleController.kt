package com.example.coroutinedemo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
class BlockingSampleController(val restTemplate: RestTemplate) {

    @GetMapping("/v1/getData")
    fun getData(): List<Company> {
        println("https://reqres.in/api/users?page")
        val listUsers =
                (1..2)
                        .mapNotNull {
                            println("https://reqres.in/api/users?page=$it")
                            restTemplate.getForObject("https://reqres.in/api/users?page=$it", ListUsers::class.java)
                        }
                        .flatMap { it.data }

        return listUsers
                .mapNotNull {
                    println("https://reqres.in/api/users/${it.id}")
                    restTemplate.getForObject("https://reqres.in/api/users/${it.id}", SingleUser::class.java)
                }
                .map { it.ad }
    }
}