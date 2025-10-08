package com.example.contacts

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.contacts.ui.theme.ContactsTheme

private val borderWidth = 1.dp
private val cardPadding = 8.dp

@Composable
fun ContactsList(
    modifier: Modifier = Modifier,
    viewModel: ContactsListViewModel = viewModel(factory = ContactsListViewModel.Factory),
    onNavigateToContactCard: (contact: Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(borderWidth, MaterialTheme.colorScheme.primary),
        modifier = modifier.padding(cardPadding)
    ) {
        when(val state = uiState) {
            is ContactsListState.Empty -> Image(
                painter = painterResource(R.drawable.contacts_empty_state),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )
            is ContactsListState.Items -> {
                LazyColumn {
                    items(state.contactItems) { contact ->
                        ContactsListItem(
                            modifier = Modifier.clickable { onNavigateToContactCard(contact.id) },
                            uiState = contact
                        ) {
                            viewModel.delete(contact)
                        }
                    }
                }
            }
        }
    }
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
