package com.example.contacts.presentation.contactCard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ContactCardScreen(contactId: Int) {
    ContactCard(
        modifier = Modifier.fillMaxSize(),
        contactId = contactId
    )
}