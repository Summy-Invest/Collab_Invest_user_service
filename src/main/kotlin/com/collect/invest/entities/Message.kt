package com.collect.invest.entities

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val obj: String,
    val notation: String
)
