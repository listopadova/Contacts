package com.example.contacts.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
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
    var selectedDestinationIndex: Int by remember {
        mutableIntStateOf(0)
    }

    ContactsTheme {
        Scaffold(
            bottomBar = {
                NavigationBar {
                    tabs.forEachIndexed { index, destination ->
                        NavigationBarItem(
                            selected = selectedDestinationIndex == index,
                            onClick = {
                                navController.navigate(route = destination.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
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
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = ContactsTab,
                modifier = modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                contactsTab()
                favouritesTab()
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
            route = ContactsTab
        ),
        TabDestination(
            icon = painterResource(R.drawable.favourite_24),
            title = stringResource(R.string.favourites__tab),
            route = FavouritesTab
        )
    )
}

private data class TabDestination(
    val icon: Painter,
    val title: String,
    val route: Any
)

