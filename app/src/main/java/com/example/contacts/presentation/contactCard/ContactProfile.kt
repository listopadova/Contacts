package com.example.contacts.presentation.contactCard

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private val contactVerticalPadding = 50.dp
private val contactIconPadding = 12.dp
private val contactIconSize = 100.dp

@Composable
fun ContactProfile(initials: String, fullName: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondaryContainer),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            shape = CircleShape,
            color = Color.Blue,
            contentColor = Color.White,
            modifier = Modifier
                .padding(top = contactVerticalPadding, bottom = contactIconPadding)
                .size(contactIconSize)
                .align(Alignment.CenterHorizontally)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(initials)
            }
        }
        Text(
            text = fullName,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = contactVerticalPadding)
        )
    }
}

@Preview(showBackground = true)
@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun ContactProfilePreview() {
    ContactProfile("KK", "Kdfdf Krwr")
}
