package com.collect.invest.validation

class PasswordValidator : Validator() {
    override fun validate(input: String): Boolean {
        return !containsSpecialCharacters(input) && input.length >= 8
    }
}