package com.example.contacts.presentation.contactsList

import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.contacts.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteContactsScreen(
    modifier: Modifier = Modifier,
    onNavigateToContactCard: (contact: Int) -> Unit
) {
    val activity = LocalActivity.current as? ComponentActivity
    if (activity != null) {
        val extras = MutableCreationExtras(activity.defaultViewModelCreationExtras).apply {
            set(ContactsListViewModel.LIST_TYPE_KEY, ListType.FAVOURITES)
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(R.string.favourites__title)) }
                )
            },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            ContactsList(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                viewModel = viewModel(
                    factory = ContactsListViewModel.Factory,
                    extras = extras
                ),
                onNavigateToContactCard = onNavigateToContactCard
            )
        }
    }
}
