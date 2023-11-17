package com.collect.invest.controllers

import com.collect.invest.entities.Account
import com.collect.invest.entities.AuthenticatedUser
import com.collect.invest.utils.HttpClientFactory
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.client.statement.HttpResponse
import java.lang.Exception

class LogInController {
    suspend fun accountExists(account: Account, url: String): AuthenticatedUser {
        HttpClientFactory.createHttpClient().use { client ->
            val response: HttpResponse = client.get("$url/userService/user/authenticateUser/${account.email}/${account.password}")
            when (response.status) {
                HttpStatusCode.OK -> {
                    return response.body<AuthenticatedUser>()
                }

                else -> {
                    throw Exception("Incorrect password or email")
                }
            }
        }
    }
}