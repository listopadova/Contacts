package com.example.contacts.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.contacts.R
import com.example.contacts.ui.theme.ContactsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseScreen(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    ContactsTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Contacts") },
                    navigationIcon = {
                        if (navController.previousBackStackEntry != null) {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Back button"
                                )
                            }
                        }
                    },
                )
            },
            bottomBar = {
                NavigationBar {
                    NavigationBarItem(
                        selected = true,
                        onClick = {},
                        icon = {
                            Icon(
                                painter = painterResource(R.drawable.person_24),
                                tint = MaterialTheme.colorScheme.primary,
                                contentDescription = ""
                            )
                        },
                        label = { Text("Contacts") }
                    )
                    NavigationBarItem(
                        selected = false,
                        onClick = {},
                        icon = {
                            Icon(
                                painter = painterResource(R.drawable.favourite_24),
                                tint = MaterialTheme.colorScheme.primary,
                                contentDescription = ""
                            )
                        },
                        label = { Text("Favourites") }
                    )
                }
            },
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { navController.navigateToAddContactScreen() }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.add_24),
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = ""
                    )
                }
            },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = ContactsList,
                modifier = modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                contactsListScreen(onNavigateToContactCard = { contactId ->
                    navController.navigateToContactCard(contactId)
                })
                contactCardScreen()
                addContactScreen()
            }
        }
    }
}
