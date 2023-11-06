package com.collect.invest.entities

import kotlinx.serialization.Serializable

@Serializable
data class Account(
    val email: String,
    val password: String
)
