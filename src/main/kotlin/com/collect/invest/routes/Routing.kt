package com.collect.invest.routes

import com.collect.invest.controllers.LogInController
import com.collect.invest.controllers.SignUpController
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.collect.invest.entities.*
import com.collect.invest.validation.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun Application.configureRouting() {
    val emailValid = EmailValidator()
    val passwordValid = PasswordValidator()
    val logInController = LogInController()
    val signUpController = SignUpController()
    val url = "http://localhost:8080"

    routing {
        post("/signUp") {
            val user = call.receive<User>()
            if (!emailValid.validate(user.email)) {
                call.respondText(text = Json.encodeToString(Message("Incorrect email")),
                    contentType = ContentType.Application.Json,
                    status = HttpStatusCode.BadRequest
                )
            }
            else if (!passwordValid.validate(user.password)) {
                call.respondText(text = Json.encodeToString(Message("Incorrect password")),
                    contentType = ContentType.Application.Json,
                    status = HttpStatusCode.BadRequest
                )
            }
            else {
                try {
                    val response = signUpController.createAccount(url, user)
                    call.respondText(text = Json.encodeToString(response),
                        contentType = ContentType.Application.Json,
                        status = HttpStatusCode.OK
                    )
                }
                catch (e: Throwable){
                    call.respondText(text = Json.encodeToString(Message(e.message.toString())),
                        contentType = ContentType.Application.Json,
                        status = HttpStatusCode.Unauthorized)
                }
            }
        }
        get("/logIn/{email}/{password}") {
            val account = Account(email = call.parameters["email"]!!, password = call.parameters["password"]!!)
            if (!passwordValid.validate(account.password)) {
                call.respondText(text = Json.encodeToString(Message("Incorrect password")),
                    contentType = ContentType.Application.Json,
                    status = HttpStatusCode.Unauthorized)
            }
            else {
                try {
                    val response = logInController.accountExists(account, url)
                    call.respondText(text = Json.encodeToString(response),
                        contentType = ContentType.Application.Json,
                        status = HttpStatusCode.OK)
                }
                catch (e: Throwable) {
                    call.respondText(text = Json.encodeToString(Message(e.message.toString())),
                        contentType = ContentType.Application.Json,
                        status = HttpStatusCode.Unauthorized)
                }
            }
        }
    }
}
