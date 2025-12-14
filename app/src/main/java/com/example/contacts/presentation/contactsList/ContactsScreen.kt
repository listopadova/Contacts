package com.example.contacts.presentation.contactsList

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


// TODO: remove it
@Composable
fun ContactsScreen(
    modifier: Modifier = Modifier,
    onNavigateToContactCard: (contact: Int) -> Unit
) {
        ContactsList(
            modifier = modifier
                .fillMaxSize(),
            onNavigateToContactCard = onNavigateToContactCard
        )
    }
