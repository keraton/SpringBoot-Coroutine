package com.example.coroutinedemo

data class Company(
        val company: String,
        val url: String,
        val text: String
)

data class ListUsers(
        val page: Int,
        val data: List<User>
)

data class User(
        val id: Int,
        val email: String
)

data class SingleUser(
        val ad : Company
)