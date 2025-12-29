package com.example.contacts.presentation.contactsList

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.contacts.R


// TODO: remove it
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactsScreen(
    modifier: Modifier = Modifier,
    onNavigateToContactCard: (contact: Int) -> Unit,
    onAddContactClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.contacts__title)) }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            AnimatedVisibility(
                visible = true,
                enter = fadeIn()
            ) {
                FloatingActionButton(
                    onClick = onAddContactClick
                ) {
                    Icon(
                        painter = painterResource(R.drawable.add_24),
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = ""
                    )
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        ContactsList(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            onNavigateToContactCard = onNavigateToContactCard
        )
    }
}
