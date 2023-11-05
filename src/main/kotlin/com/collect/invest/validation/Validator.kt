package com.collect.invest.validation

abstract class Validator {
    fun containsSpecialCharacters(input: String): Boolean {
        val pattern = "[^A-Za-z0-9_]+".toRegex()
        return pattern.containsMatchIn(input)
    }

    abstract fun validate(input: String): Boolean
}