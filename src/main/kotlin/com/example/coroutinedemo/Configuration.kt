package com.example.coroutinedemo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class Configuration {

    @Bean
    fun webClient() : WebClient = WebClient.create()

    @Bean
    fun restTemplate(): RestTemplate = RestTemplate()
}