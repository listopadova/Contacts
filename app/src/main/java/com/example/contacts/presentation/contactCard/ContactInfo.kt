package com.example.contacts.presentation.contactCard

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private val cardPadding = 16.dp
private val cardTitlePadding = 16.dp
private val iconSize = 20.dp
private val spacerHeight = 8.dp
private val horizontalPadding = 12.dp

@Composable
fun ContactInfo(phone: String, email: String?) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(cardPadding)
    ) {
        Text(
            text = "Contact info",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(start = horizontalPadding, top = cardTitlePadding)
        )
        Spacer(modifier = Modifier.height(spacerHeight))
        ContactInfoItem(
            title = "Phone",
            subtitle = phone,
            icon = Icons.Outlined.Call
        )
        if (email != null) {
            ContactInfoItem(
                title = "Email",
                subtitle = email,
                icon = Icons.Outlined.Email
            )
        }
        Spacer(modifier = Modifier.height(spacerHeight))
    }
}

@Composable
private fun ContactInfoItem(title: String, subtitle: String, icon: ImageVector) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding, vertical = spacerHeight)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            modifier = Modifier
                .padding(end = horizontalPadding)
                .size(iconSize)
        )
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun ContactInfoPreview() {
    ContactInfo("1231757657", "dscdsmcmd")
}
