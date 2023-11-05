package com.collect.invest.entities

import kotlinx.serialization.Serializable

@Serializable
data class User (
    val name: String,
    val email: String,
    val password: String
)