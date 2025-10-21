package com.example.contacts.components

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.contacts.ui.theme.ContactsTheme

private val padding = 6.dp
private val cornerRadius = 10.dp
private val TextFieldAnimationDuration = 150

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    label: String,
    text: String,
    supportingText: String? = null,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    updateValue: (String) -> Unit
) {
    Column {
        Text(
            text = label,
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(start = padding)
        )
        val interactionSource = remember { MutableInteractionSource() }
        val colors = TextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.onSecondaryContainer,
            unfocusedTextColor = MaterialTheme.colorScheme.onSecondaryContainer,
        )
        BasicTextField(
            value = text,
            onValueChange = { updateValue(it) },
            singleLine = true,
            keyboardOptions = keyboardOptions,
            textStyle = MaterialTheme.typography.bodyLarge,
            interactionSource = interactionSource,
            modifier = modifier
                .padding(padding)
                .fillMaxWidth(),
            decorationBox = { innerTextField ->

                TextFieldDefaults.DecorationBox(
                    value = text,
                    innerTextField = innerTextField,
                    supportingText = { supportingText?.let { Text(it) } },
                    enabled = true,
                    singleLine = true,
                    isError = isError,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = interactionSource,
                    colors = colors,
                    container = {
                        val containerColor =
                            animateColorAsState(
                                targetValue = containerColor(isError),
                                animationSpec = tween(durationMillis = TextFieldAnimationDuration),
                            )
                        Box(
                            modifier
                                .clip(RoundedCornerShape(cornerRadius))
                                .background(containerColor.value)
                        )
                    }
                )
            }
        )
    }
}

@Composable
private fun containerColor(isError: Boolean): Color {
    return if (!isError) {
        MaterialTheme.colorScheme.secondaryContainer
    } else {
        MaterialTheme.colorScheme.errorContainer
    }
}

@Preview(showBackground = true)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TextInputPreview() {
    ContactsTheme {
        TextInput(
            label = "label",
            text = "text",
            supportingText = "error",
            isError = false
        ) { }
    }
}