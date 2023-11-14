package com.collect.invest.controllers

import com.collect.invest.entities.Account
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.statement.HttpResponse

class logInConroller {
    private val client = HttpClient(CIO){
        install(ContentNegotiation) {
            json(contentType = ContentType.Any)
        }
    }
    suspend fun accountExists(account: Account, url: String){
        val response: HttpResponse = client.get(url + "/${account.email}/${account.password}")
    }
}