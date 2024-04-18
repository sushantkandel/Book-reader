package com.example.bookReader.screens.login

import com.example.bookReader.component.TextFieldState
import com.example.bookReader.component.textFieldStateSaver
import java.util.regex.Pattern

private const val EMAIL_REGEX = "^[\\w\\-\\.]+@([\\w-]+\\.)+[\\w-]{2,}\$"

class EmailState(email: String? = null) :
    TextFieldState(validator = ::isEmailValid, errorFor = ::emailValidationError) {
    init {
        email?.let {
            text = it
        }
    }
}

/**
 * Returns an error to be displayed or null if no error was found
 */
private fun emailValidationError(email: String): String {
    return if (email.isEmpty()) {
        "Email should not be empty"
    } else {
        "Please enter valid email"
    }

}

private fun isEmailValid(email: String): Boolean {
    return Pattern.matches(EMAIL_REGEX, email)
}

val emailStateSaver = textFieldStateSaver(EmailState())


class PasswordState(password: String? = null) :
    TextFieldState(validator = ::isPasswordValid, errorFor = ::passwordValidationError) {
    init {
        password?.let {
            text = it
        }
    }
}

private fun passwordValidationError(password: String): String {
    return if (password.isEmpty()) {
        "Password should not be empty"
    } else {
        ""
    }
}

private fun isPasswordValid(password: String): Boolean {
    return password.isNotEmpty()
}

val passwordStateSaver = textFieldStateSaver(PasswordState())