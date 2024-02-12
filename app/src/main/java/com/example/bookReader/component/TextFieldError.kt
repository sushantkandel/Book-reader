package com.example.bookReader.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


/**
 * A composable function for displaying an error message below a text field.
 *
 * @param textError The error message to be displayed.
 */
@Composable
fun TextFieldError(textError: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = textError,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.error,
            //style = LocalTextStyle.current.copy(color = MaterialTheme.colorScheme.error)
        )
    }
}

@Preview
@Composable
fun errorTextPreview() {
    TextFieldError("This is text field error")
}