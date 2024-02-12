package com.example.bookReader.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.setValue

/**
 * A class representing the state of a text field with support for validation and error handling.
 *
 * @param validator A function that checks the validity of the text input.
 * @param errorFor A function that provides an error message for invalid text input.
 */
open class TextFieldState(
    private val validator: (String) -> Boolean = { true },
    private val errorFor: (String) -> String = { "" }
) {
    var text: String by mutableStateOf("")

    // was the TextField ever focused
    var isFocusedDirty: Boolean by mutableStateOf(false)
    var isFocused: Boolean by mutableStateOf(false)
    private var displayErrors: Boolean by mutableStateOf(false)

    open val isValid: Boolean
        get() = validator(text)

    fun onFocusChange(focused: Boolean) {
        isFocused = focused
        if (focused) isFocusedDirty = true
    }

    fun enableShowErrors() {
        // only show errors if the text was at least once focused
        if (isFocusedDirty) {
            displayErrors = true
        }
    }

    fun disableErrors() {
        // hide errors if the textfield is empty
        if (isFocusedDirty) {
            displayErrors = false
        }
    }

    fun showErrors() = !isValid && displayErrors

    open fun getError(): String? {
        return if (showErrors()) {
            errorFor(text)
        } else {
            null
        }
    }
}

/**
 * A function for saving and restoring the state of a [TextFieldState].
 *
 * @param state The [TextFieldState] instance to be saved or restored.
 */
fun textFieldStateSaver(state: TextFieldState) =
    listSaver<TextFieldState, Any>(save = { listOf(it.text, it.isFocusedDirty) }, restore = {
        state.apply {
            text = it[0] as String
            isFocusedDirty = it[1] as Boolean
        }
    })

