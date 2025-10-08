package com.example.contacts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.contacts.ui.theme.ContactsTheme

@Composable
fun ContactCardScreen(contactId: Int) {
    ContactsTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            ContactCard(
                modifier = Modifier.padding(innerPadding),
                contactId = contactId
            )
        }
    }
}