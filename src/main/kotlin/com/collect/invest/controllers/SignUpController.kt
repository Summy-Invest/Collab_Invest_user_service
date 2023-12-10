package com.collect.invest.controllers


import com.collect.invest.entities.AuthenticatedUser
import com.collect.invest.entities.User
import com.collect.invest.utils.HttpClientFactory
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import java.lang.Exception

/**
 * Controller class for handling user sign up functionality.
 */
class SignUpController {
    /**
     * Creates a new account by making a POST request to the specified URL with the given user data.
     *
     * @param url The URL to send the request to.
     * @param user The user data to be saved.
     * @return An [AuthenticatedUser] object representing the created account.
     * @throws Exception if the response status is not [HttpStatusCode.OK].
     */
    suspend fun createAccount(url: String, user: User): AuthenticatedUser{
        HttpClientFactory.createHttpClient().use { client ->
            val response: HttpResponse = client.post("$url/userService/user/saveUser"){
                contentType(ContentType.Application.Json)
                setBody(user)
            }
            when (response.status){
                HttpStatusCode.OK -> {
                    return response.body<AuthenticatedUser>()
                }

                else -> {
                    throw Exception("Account is already created")
                }
            }
        }
    }
}