package com.example.bookReader.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bookReader.R
import com.example.bookReader.component.AppButton
import com.example.bookReader.component.AppTextField
import com.example.bookReader.component.PasswordTextField
import com.example.bookReader.component.ReaderLogo
import com.example.bookReader.component.TextFieldState
import com.example.bookReader.ui.theme.BookReaderTheme


@Composable
fun LoginScreen(navController: NavController?) {

    var buttonState by remember { mutableStateOf(false) }

    val emailState by rememberSaveable(stateSaver = emailStateSaver) {
        mutableStateOf(EmailState())
    }

    val passwordState by rememberSaveable(stateSaver = passwordStateSaver) {
        mutableStateOf(EmailState())
    }

    val onSubmit = {
        if (emailState.isValid && passwordState.isValid) {

        }
    }


    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            ReaderLogo()
            Spacer(modifier = Modifier.height(50.dp))
            UserEmail(modifier = Modifier.fillMaxWidth(), emailState)
            Spacer(modifier = Modifier.height(16.dp))
            UserPassword(modifier = Modifier.fillMaxWidth(), passwordState, onSubmit)
            NextButton(
                modifier = Modifier.weight(1f),
                enabled = emailState.isValid && passwordState.isValid,
                onSubmit
            )


        }

    }
}


@Composable
fun UserPassword(modifier: Modifier, passwordState: TextFieldState, onSubmit: () -> Unit) {
    Column {
        PasswordTextField(
            modifier = modifier,
            label = stringResource(R.string.password),
            passwordState = passwordState,
            imeAction = ImeAction.Done,
            onImeAction = onSubmit
        )
    }

}


@Composable
fun UserEmail(modifier: Modifier, emailState: TextFieldState) {
    Column {
        AppTextField(
            modifier = modifier,
            label = stringResource(R.string.email),
            textFieldState = emailState,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
            imeAction = ImeAction.Next
        )
    }

}

@Composable
fun NextButton(
    modifier: Modifier = Modifier, enabled: Boolean, onSubmit: () -> Unit
) {

    AppButton(
        onClick = onSubmit,
        modifier = modifier
            .fillMaxWidth(),
        label = stringResource(id = R.string.login),
        enabled = enabled
    )
}

@Preview
@Composable
fun DefaultPreview() {
    BookReaderTheme {
        LoginScreen(navController = null)
    }
}

