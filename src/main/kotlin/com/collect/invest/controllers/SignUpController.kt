package com.collect.invest.controllers

import com.collect.invest.entities.Account
import com.collect.invest.entities.AuthenticatedUser
import com.collect.invest.utils.HttpClientFactory
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import java.lang.Exception

class SignUpController {
    suspend fun createAccount(url: String, account: Account): AuthenticatedUser{
        val client = HttpClientFactory.createHttpClient()
        val response: HttpResponse = client.post("$url/userService/user/saveUser"){
            contentType(ContentType.Application.Json)
            setBody(account)
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