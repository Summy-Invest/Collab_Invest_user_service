package com.collect.invest.controllers

import com.collect.invest.entities.AuthenticatedUser
import com.collect.invest.utils.HttpClientSingleton
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
     * Checks if an account exists by authenticating the user with the given email, password, and URL.
     *
     * @param email The email of the user.
     * @param password The password of the user.
     * @param url The URL for the authentication service.
     * @return An [AuthenticatedUser] object representing the authenticated user.
     * @throws Exception if the authentication fails or the response status is not [HttpStatusCode.OK].
     */
    suspend fun accountExists(email: String, password: String, url: String): AuthenticatedUser {
    val client = HttpClientSingleton.client
        val response: HttpResponse = client.get("$url/userService/user/authenticateUser/${email}/${password}")
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