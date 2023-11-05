package com.collect.invest.validation

class EmailValidator : Validator() {
    override fun validate(input: String): Boolean {
        val sterilizedString = input.replaceFirst("@", "").replaceFirst(".","")
        val emailPattern = "^.+@.+\\..+\$".toRegex(RegexOption.DOT_MATCHES_ALL)
        return !containsSpecialCharacters(sterilizedString) && emailPattern.containsMatchIn(input)
    }
}