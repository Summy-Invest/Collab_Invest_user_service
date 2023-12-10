package com.collect.invest.controllers

import com.collect.invest.entities.Account
import com.collect.invest.entities.AuthenticatedUser
import com.collect.invest.utils.HttpClientFactory
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.client.statement.HttpResponse
import java.lang.Exception

/**
 * Controller class for handling login functionality.
 */
class LogInController {
    /**
     * Checks if an account exists by sending a request to the specified URL with the provided account credentials.
     *
     * @param account The account object containing the email and password.
     * @param url The URL to send the request to.
     * @return An AuthenticatedUser object if the account exists and the credentials are correct.
     * @throws Exception if the response status is not [HttpStatusCode.OK].
     */
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