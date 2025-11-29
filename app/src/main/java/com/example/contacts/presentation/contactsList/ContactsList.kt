package com.example.contacts.presentation.contactsList

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.contacts.R
import com.example.contacts.ui.theme.ContactsTheme

private val headerPadding = 16.dp

@Composable
fun ContactsList(
    modifier: Modifier = Modifier,
    viewModel: ContactsListViewModel = viewModel(factory = ContactsListViewModel.Companion.Factory),
    onNavigateToContactCard: (contact: Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        when(val state = uiState) {
            is ContactsListState.Empty -> Image(
                painter = painterResource(R.drawable.contacts_empty_state),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )
            is ContactsListState.Items -> {
                LazyColumn {
                    state.contactItems.forEach { initial, contacts ->
                        stickyHeader { Header(initial) }

                        items(contacts) { contact ->
                            ContactsListItem(
                                modifier = Modifier.clickable { onNavigateToContactCard(contact.id) },
                                uiState = contact
                            ) {
                                viewModel.switchFavourite(contact)
                            }
                        }
                    }
                }
            }
        }

}

@Composable
fun Header(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(headerPadding)
    )
}

@Preview(showBackground = true)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ContactsListPreview() {
    ContactsTheme {
        ContactsList(
            onNavigateToContactCard = { }
        )
    }
}
