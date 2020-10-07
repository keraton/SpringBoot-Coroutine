package com.example.coroutinedemo

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitExchange

@RestController
class CoroutineSampleController(val webclient: WebClient) {

    @GetMapping("/v2/getData")
    suspend fun getData(): List<Company> = coroutineScope {
        println("https://reqres.in/api/users?page")
        val listUsers =
                (1..2)
                        .map {
                            async {
                                println("https://reqres.in/api/users?page=$it")
                                webclient.get().uri("https://reqres.in/api/users?page=$it")
                                        .accept(APPLICATION_JSON)
                                        .awaitExchange()
                                        .awaitBody<ListUsers>()
                            }
                        }.map { it.await() }
                        .map { println(it); it }
                        .flatMap { it.data }

        listUsers
                .map {
                    async {
                        println("https://reqres.in/api/users/${it.id}")
                        webclient.get().uri("https://reqres.in/api/users/${it.id}")
                                .accept(APPLICATION_JSON)
                                .awaitExchange()
                                .awaitBody<SingleUser>()
                    }
                }.map { it.await() }
                .map { it.ad }
    }
}