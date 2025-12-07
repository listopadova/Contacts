package com.example.contacts.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.contacts.R
import com.example.contacts.ui.theme.ContactsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseScreen(
    modifier: Modifier = Modifier,
) {
    val tabs = getTabs()
    val navController: NavHostController = rememberNavController()
    var isMainScreen by remember { mutableStateOf(false) }
    var selectedDestinationIndex: Int by remember {
        mutableIntStateOf(0)
    }
    navController.addOnDestinationChangedListener { controller, destination, arguments ->
        isMainScreen = destination.parent?.startDestinationRoute != destination.route
    }

    ContactsTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Contacts") },
                    navigationIcon = {
                        if (isMainScreen) {
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
                    tabs.forEachIndexed { index, destination ->
                        NavigationBarItem(
                            selected = selectedDestinationIndex == index,
                            onClick = {
                                navController.navigate(route = destination.route) {
                                    launchSingleTop = true
                                    restoreState = true
                                }
                                selectedDestinationIndex = index
                            },
                            icon = {
                                Icon(
                                    painter = destination.icon,
                                    tint = MaterialTheme.colorScheme.primary,
                                    contentDescription = ""
                                )
                            },
                            label = { Text(destination.title) }
                        )
                    }
                }
            },
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                AnimatedVisibility(
                    visible = !isMainScreen,
                    enter = fadeIn()
                ) {
                    FloatingActionButton(
                        onClick = { navController.navigateToAddContactScreen() }
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
                favouritesContactsListScreen(onNavigateToContactCard = { contactId ->
                    navController.navigateToContactCard(contactId)
                })
                contactCardScreen()
                addContactScreen()
            }
        }
    }
}

@Composable
private fun getTabs(): List<TabDestination> {
    return listOf(
        TabDestination(
            icon = painterResource(R.drawable.person_24),
            title = stringResource(R.string.contacts__tab),
            route = ContactsList
        ),
        TabDestination(
            icon = painterResource(R.drawable.favourite_24),
            title = stringResource(R.string.favourites__tab),
            route = FavouriteContactsList
        )
    )
}

private data class TabDestination(
    val icon: Painter,
    val title: String,
    val route: Any
)

