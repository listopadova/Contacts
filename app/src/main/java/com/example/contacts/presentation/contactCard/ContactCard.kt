package com.example.contacts.presentation.contactCard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

private val spacer = 20.dp
@Composable
fun ContactCard(
    modifier: Modifier = Modifier,
    viewModel: ContactCardViewModel = viewModel(factory = ContactCardViewModel.Companion.Factory),
    contactId: Int
) {
    val contact = viewModel.getContact(contactId).collectAsStateWithLifecycle()
    Column(modifier = modifier) {
        ContactProfile(
            initials = contact.value.initials,
            fullName = contact.value.fullName
        )
        Spacer(modifier = Modifier.height(spacer))
        ContactActions(isEmailAvailable = contact.value.email != null)
        Spacer(modifier = Modifier.height(spacer/2))
        ContactInfo(phone = contact.value.phone, email = contact.value.email)
    }
}
