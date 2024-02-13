
package com.example.bookReader.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.bookReader.R


/**
 * A composable function for displaying an OutlinedTextField with additional functionality for a wallet input.
 *
 * @param modifier The modifier for this composable.
 * @param label The label to be displayed above the text field.
 * @param textFieldState The state object for managing the text field's state.
 * @param imeAction The IME (Input Method Editor) action for the text field (default is ImeAction.Next).
 * @param onImeAction Callback function to be invoked when the IME action is performed (default is an empty function).
 * @param keyboardOptions The keyboard options for the text field (default is KeyboardOptions.Default with Text keyboardType).
 */



@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    label: String,
    textFieldState: TextFieldState,
    imeAction: ImeAction = ImeAction.Next,
    onImeAction: () -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),

    ) {

    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value = textFieldState.text,
        onValueChange = { value ->
            textFieldState.text = value
            if (textFieldState.text.isNotEmpty()) textFieldState.enableShowErrors() else textFieldState.disableErrors()
        },
        label = {
            Text(
                text = label, style = MaterialTheme.typography.bodySmall
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                textFieldState.onFocusChange(focusState.isFocused)
                if (!focusState.isFocused && textFieldState.text.isNotEmpty()) {
                    textFieldState.enableShowErrors()
                }
            },

        textStyle = MaterialTheme.typography.bodyLarge,

        isError = textFieldState.showErrors(),
        keyboardOptions = keyboardOptions.copy(imeAction = imeAction),
        keyboardActions = KeyboardActions(onDone = {
            if (textFieldState.isValid) focusManager.clearFocus()
            onImeAction.invoke()
        }),
        singleLine = true
    )

    textFieldState.getError()?.let { error -> TextFieldError(textError = error) }
}


/**
 * A composable function for displaying an OutlinedTextField  for password input.
 *
 * @param label The label to be displayed above the password field.
 * @param passwordState The state object for managing the password field's state.
 * @param modifier The modifier for this composable.
 * @param imeAction The IME (Input Method Editor) action for the password field (default is ImeAction.Done).
 * @param onImeAction Callback function to be invoked when the IME action is performed (default is an empty function).
 */

@Composable
fun PasswordTextField(
    label: String,
    passwordState: TextFieldState,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Done,
    onImeAction: () -> Unit = {},
) {
    val showPassword = remember { mutableStateOf(false) }
    OutlinedTextField(
        value = passwordState.text,
        onValueChange = {
            passwordState.text = it
            passwordState.enableShowErrors()
        },
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                passwordState.onFocusChange(focusState.isFocused)
                if (!focusState.isFocused) {
                    passwordState.enableShowErrors()
                }
            },
        textStyle = MaterialTheme.typography.bodyLarge,
        label = {
            Text(
                text = label, style = MaterialTheme.typography.bodySmall
            )
        },
        trailingIcon = {
            if (showPassword.value) {
                IconButton(onClick = { showPassword.value = false }) {
                    Icon(
                        imageVector = Icons.Filled.Visibility,
                        contentDescription = stringResource(id = R.string.hide_password)
                    )
                }
            } else {
                IconButton(onClick = { showPassword.value = true }) {
                    Icon(
                        imageVector = Icons.Filled.VisibilityOff,
                        contentDescription = stringResource(id = R.string.show_password)
                    )
                }
            }
        },
        visualTransformation = if (showPassword.value) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        isError = passwordState.showErrors(),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = imeAction),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
        })
    )

    passwordState.getError()?.let { error -> TextFieldError(textError = error) }
}





/**
 * A composable function for displaying an OutlinedTextField with a trailing icon for wallet input.
 *
 * @param modifier The modifier for this composable.
 * @param label The label to be displayed above the text field.
 * @param textFieldState The state object for managing the text field's state.
 * @param imeAction The IME (Input Method Editor) action for the text field (default is ImeAction.Next).
 * @param onImeAction Callback function to be invoked when the IME action is performed (default is an empty function).
 * @param keyboardOptions The keyboard options for the text field (default is KeyboardOptions.Default with Text keyboardType).
 * @param trailingIcon A composable function representing the trailing icon of the text field.
 */

@Composable
fun AppTextFieldWithTrailing(
    modifier: Modifier = Modifier,
    label: String,
    textFieldState: TextFieldState,
    imeAction: ImeAction = ImeAction.Next,
    onImeAction: () -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
    trailingIcon: @Composable (() -> Unit)? = null
    ) {
    OutlinedTextField(
        value = textFieldState.text,
        onValueChange = { value ->
            textFieldState.text = value
            if (textFieldState.text.isNotEmpty()) textFieldState.enableShowErrors() else textFieldState.disableErrors()
        },
        label = {
            Text(
                text = label, style = MaterialTheme.typography.bodySmall
            )
        },

        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                textFieldState.onFocusChange(focusState.isFocused)
                if (!focusState.isFocused) {
                    textFieldState.enableShowErrors()
                }
            },
        textStyle = MaterialTheme.typography.bodyLarge,
        trailingIcon = {
            trailingIcon?.invoke()
        },
        isError = textFieldState.showErrors(),
        keyboardOptions = keyboardOptions.copy(imeAction = imeAction),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
        }),
        singleLine = true)

    textFieldState.getError()?.let { error -> TextFieldError(textError = error) }
}