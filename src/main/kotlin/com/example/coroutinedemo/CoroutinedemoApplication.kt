package com.example.coroutinedemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CoroutinedemoApplication

fun main(args: Array<String>) {
	runApplication<CoroutinedemoApplication>(*args)
}
