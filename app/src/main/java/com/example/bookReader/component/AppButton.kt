package com.example.bookReader.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bookReader.R
import com.example.bookReader.component.AppButtonDefaults.ButtonCornerRadius
import com.example.bookReader.resources.ThemePreviews
import com.example.bookReader.ui.theme.BookReaderTheme


/**
 * filled button with generic content slot. Wraps Material 3 [Button].
 *
 * @param onClick Will be called when the user clicks the button.
 * @param modifier Modifier to be applied to the button.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param contentPadding The spacing values to apply internally between the container and the
 * content.
 * @param content The button content.
 */


@Composable
fun AppButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
) {

    var lastClickTime = remember { mutableLongStateOf(0L) }
    Button(
        onClick = {
            /*Here the time is taken for the delay and avoid the multiple click invoke*/
            val currentTime = System.currentTimeMillis()
            if ((currentTime - lastClickTime.value) >= 700) {
                lastClickTime.value = currentTime
                onClick.invoke()
            }

        },
        modifier = modifier.height(50.dp),
        enabled = enabled,
        shape = RoundedCornerShape(ButtonCornerRadius),
        contentPadding = contentPadding,
        content = {
            Text(label)
        },
    )
}


@Composable
fun AppOutlinedButtonWithLeadingIcon(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    leadingIcon: Painter? = null,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(50.dp),
        enabled = enabled,
        shape = RoundedCornerShape(ButtonCornerRadius),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colorScheme.onBackground,
        ),
        border = BorderStroke(
            width = AppButtonDefaults.OutlinedButtonBorderWidth,
            color = if (enabled) {
                MaterialTheme.colorScheme.outline
            } else {
                MaterialTheme.colorScheme.onSurface.copy(
                    alpha = AppButtonDefaults.DisabledOutlinedButtonBorderAlpha,
                )
            },
        ),
        contentPadding = contentPadding,
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (leadingIcon != null) {
                    Image(
                        painter = leadingIcon,
                        contentDescription = null,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }

                Text(
                    text = label, style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )

            }
        }
    )
}

@Composable
fun AppButtonWithLeadingIcon(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    leadingIcon: Painter? = null,
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(50.dp),
        enabled = enabled,
        shape = RoundedCornerShape(ButtonCornerRadius),
        contentPadding = contentPadding,
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (leadingIcon != null) {
                    Image(
                        painter = leadingIcon,
                        contentDescription = null,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }

                Text(
                    text = label, style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )

            }
        }
    )
}


/**
 * app outlined button with generic content slot. Wraps Material 3 [OutlinedButton].
 *
 * @param onClick Will be called when the user clicks the button.
 * @param modifier Modifier to be applied to the button.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param contentPadding The spacing values to apply internally between the container and the
 * content.
 * @param content The button content.
 */
@Composable
fun AppOutlinedButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(50.dp),
        enabled = enabled,
        shape = RoundedCornerShape(ButtonCornerRadius),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colorScheme.onBackground,
        ),
        border = BorderStroke(
            width = AppButtonDefaults.OutlinedButtonBorderWidth,
            color = if (enabled) {
                MaterialTheme.colorScheme.outline
            } else {
                MaterialTheme.colorScheme.onSurface.copy(
                    alpha = AppButtonDefaults.DisabledOutlinedButtonBorderAlpha,
                )
            },
        ),
        contentPadding = contentPadding,
        content = {
            Text(label)
        },
    )
}


/**
 * app text button with generic content slot. Wraps Material 3 [TextButton].
 *
 * @param onClick Will be called when the user clicks the button.
 * @param modifier Modifier to be applied to the button.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param content The button content.
 */
@Composable
fun AppTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit,
) {
    TextButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.textButtonColors(
            contentColor = MaterialTheme.colorScheme.onBackground,
        ),
        content = content,
    )
}


/**
 * Internal app button content layout for arranging the text label and leading icon.
 *
 * @param text The button text label content.
 * @param leadingIcon The button leading icon content. Default is `null` for no leading icon.Ï
 */
@Composable
private fun AppButtonContent(
    label: String,
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    if (leadingIcon != null) {
        Box(Modifier.sizeIn(maxHeight = ButtonDefaults.IconSize)) {
            leadingIcon()
        }
    }
    Box(
        Modifier.padding(
            start = if (leadingIcon != null) {
                ButtonDefaults.IconSpacing
            } else {
                0.dp
            },
        ),
    ) {
        Text(label)
    }
}

@ThemePreviews
@Composable
fun AppButtonPreview(modifier: Modifier = Modifier) {
    BookReaderTheme {

        AppButton(label = stringResource(id = R.string.next), modifier = modifier, onClick = {})
    }
}

@ThemePreviews
@Composable
fun AppOutlinedButtonPreview(modifier: Modifier = Modifier) {
    AppOutlinedButton(
        label = stringResource(id = R.string.bnt_submit),
        modifier = modifier,
        onClick = {})
}


/**
 * App button default values.
 */
object AppButtonDefaults {

    const val DisabledOutlinedButtonBorderAlpha = 0.12f

    val OutlinedButtonBorderWidth = 1.dp

    val ButtonCornerRadius = 14.dp
}
