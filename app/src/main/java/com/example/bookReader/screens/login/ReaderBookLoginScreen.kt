package com.example.bookReader.screens.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bookReader.R
import com.example.bookReader.component.AppButton
import com.example.bookReader.component.AppTextField
import com.example.bookReader.component.PasswordTextField
import com.example.bookReader.component.ReaderLogo
import com.example.bookReader.component.TextFieldState
import com.example.bookReader.navigation.ReaderScreen
import com.example.bookReader.resources.ThemePreviews
import com.example.bookReader.ui.theme.BookReaderTheme


@Composable
fun LoginScreen(
    navController: NavController?,
    loginScreenViewModel: LoginScreenViewModel = LoginScreenViewModel()
) {


    val showLoginForm = remember { mutableStateOf(true) }

    val emailState by rememberSaveable(stateSaver = emailStateSaver) {
        mutableStateOf(EmailState())
    }

    val passwordState by rememberSaveable(stateSaver = passwordStateSaver) {
        mutableStateOf(PasswordState())
    }

    val onSubmit = {
        if (emailState.isValid && passwordState.isValid) {
            if (showLoginForm.value) {
                loginScreenViewModel.signInWithEmailAndPassword(emailState.text,passwordState.text){
                    navController?.navigate(ReaderScreen.ReaderHomeScreen.name)
                }
            } else {
                loginScreenViewModel.createUserWithEmailAndPassword(emailState.text,passwordState.text){
                    navController?.navigate(ReaderScreen.ReaderHomeScreen.name)
                }
            }
        }
    }


    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            ReaderLogo()
            Spacer(modifier = Modifier.height(50.dp))
            if (!showLoginForm.value) Text(text = stringResource(id = R.string.account_create_info))
            UserEmail(modifier = Modifier.fillMaxWidth(), emailState)
            Spacer(modifier = Modifier.height(16.dp))
            UserPassword(modifier = Modifier.fillMaxWidth(), passwordState, onSubmit)
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                Modifier.padding(15.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val text = if (showLoginForm.value) "SignUp" else "Login"
                Text(text = "New User?")
                Text(text = text,
                    modifier = Modifier
                        .clickable { showLoginForm.value = !showLoginForm.value }
                        .padding(5.dp),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary

                )

            }
            Spacer(modifier = Modifier.height(26.dp))
            NextButton(
                modifier = Modifier.fillMaxWidth(),
                showLoginForm = showLoginForm,
                enabled = emailState.isValid && passwordState.isValid,
                onSubmit = onSubmit
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
    modifier: Modifier = Modifier,
    enabled: Boolean,
    showLoginForm: MutableState<Boolean>,
    onSubmit: () -> Unit
) {

    AppButton(
        onClick = onSubmit,
        modifier = modifier.fillMaxWidth(),
        label = if (showLoginForm.value) stringResource(id = R.string.login) else stringResource(id = R.string.create),
        enabled = enabled
    )
}

@ThemePreviews
@Composable
fun DefaultPreview() {
    BookReaderTheme {
        LoginScreen(navController = null)
    }
}

