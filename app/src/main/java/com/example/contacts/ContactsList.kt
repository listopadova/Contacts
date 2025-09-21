package com.example.contacts

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.contacts.ui.theme.ContactsTheme

private val borderWidth = 1.dp
private val cardPadding = 8.dp

@Composable
fun ContactsList(contacts: List<Contact>) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(borderWidth, MaterialTheme.colorScheme.primary),
        modifier = Modifier.padding(cardPadding)
    ) {
        for (contact in contacts) {
            Contact(contact)
        }
    }
}

data class Contact (
    val name: String,
    val surname: String,
    val phone: String
)
@Preview(showBackground = true)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ContactsListPreview() {
    ContactsTheme {
        ContactsList(
            listOf(
                Contact(name = "kkk", surname = "lll", phone = "123"),
                Contact(name = "mmm", surname = "oooo", phone = "123456")
            )
        )
    }
}
