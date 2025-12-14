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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.contacts.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabRootScreen(
    navController: NavHostController,
    title: String,
    content: @Composable (Modifier) -> Unit
) {
    var isMainScreen by remember { mutableStateOf(false) }
    navController.addOnDestinationChangedListener { controller, destination, arguments ->
        isMainScreen = destination.parent?.startDestinationRoute == destination.route
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    if (!isMainScreen) {
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
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            AnimatedVisibility(
                visible = isMainScreen,
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
        content(Modifier
            .padding(innerPadding)
            .fillMaxSize()
        )
    }

}
