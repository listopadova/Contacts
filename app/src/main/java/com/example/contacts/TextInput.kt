package com.example.contacts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private val padding = 16.dp

@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    label: String,
    text: String,
    supportingText: String? = null,
    isError: Boolean = false,
    updateValue: (String) -> Unit
) {
    TextField(
        value = text,
        onValueChange = { updateValue(it) },
        label = { Text(label) },
        singleLine = true,
        isError = isError,
        supportingText = { supportingText?.let { Text(it) } },
        modifier = modifier
            .padding(padding)
            .fillMaxWidth(),
    )
}