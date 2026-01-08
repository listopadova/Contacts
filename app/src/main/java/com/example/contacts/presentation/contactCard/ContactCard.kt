package com.example.contacts.presentation.contactCard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.contacts.R

private val spacer = 20.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactCard(
    modifier: Modifier = Modifier,
    viewModel: ContactCardViewModel = viewModel(factory = ContactCardViewModel.Factory),
    contactId: Int,
    onBackButtonClick: () -> Unit
) {
    val contact = viewModel.getContact(contactId).collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.contact__title)) },
                navigationIcon = {
                    IconButton(onClick = onBackButtonClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back button"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.switchFavourite(contact.value) }) {
                        Icon(
                            imageVector = Icons.Outlined.FavoriteBorder,
                            tint = if (contact.value.isFavourite) MaterialTheme.colorScheme.error
                            else MaterialTheme.colorScheme.primary,
                            contentDescription = "Favourite"
                        )
                    }
                }
            )
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Column(modifier = modifier.padding(innerPadding)) {
            ContactProfile(
                initials = contact.value.initials,
                fullName = contact.value.fullName
            )
            Spacer(modifier = Modifier.height(spacer))
            ContactActions(isEmailAvailable = contact.value.email != null)
            Spacer(modifier = Modifier.height(spacer / 2))
            ContactInfo(phone = contact.value.phone, email = contact.value.email)
        }
    }
}
