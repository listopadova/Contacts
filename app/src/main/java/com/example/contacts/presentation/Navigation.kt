package com.example.contacts.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.contacts.presentation.addNewContact.AddNewContact
import com.example.contacts.presentation.contactCard.ContactCardScreen
import com.example.contacts.presentation.contactsList.ContactsScreen
import com.example.contacts.presentation.contactsList.FavouriteContactsScreen
import kotlinx.serialization.Serializable

@Serializable
object ContactsList

@Serializable
object FavouriteContactsList

@Serializable
data class ContactDetails(val contactId: Int)

@Serializable
object AddContact

@Serializable
data class EditContact(val contactId: Int)

@Serializable
object ContactsTab

@Serializable
object FavouritesTab

fun NavGraphBuilder.contactsTab() {
    composable<ContactsTab> {
        val contactsNavController = rememberNavController()
        NavHost(
            navController = contactsNavController,
            startDestination = ContactsList
        ) {
            contactsListScreen(
                onNavigateToContactCard = { contactId ->
                    contactsNavController.navigateToContactCard(contactId)
                },
                onAddContactClick = {
                    contactsNavController.navigateToAddContactScreen()
                }
            )
            contactCardScreen(
                onBackButtonClick = { contactsNavController.popBackStack() }
            )
            addContactScreen(
                onBackButtonClick = { contactsNavController.popBackStack() },
                onAddContactClick = { contactId ->
                    contactsNavController.popBackStack()
                    contactsNavController.navigateToContactCard(contactId)
                }
            )
            editContactScreen()
        }
    }
}

fun NavGraphBuilder.favouritesTab() {
    composable<FavouritesTab> {
        val favouritesNavController = rememberNavController()
        NavHost(
            navController = favouritesNavController,
            startDestination = FavouriteContactsList
        ) {
            favouritesContactsListScreen(onNavigateToContactCard = { contactId ->
                favouritesNavController.navigateToContactCard(contactId)
            })
            contactCardScreen(
                onBackButtonClick = { favouritesNavController.popBackStack() }
            )
            editContactScreen()
        }
    }
}

fun NavGraphBuilder.contactsListScreen(
    onNavigateToContactCard: (contact: Int) -> Unit,
    onAddContactClick: () -> Unit
) {
    composable<ContactsList> {
        ContactsScreen(
            onNavigateToContactCard = onNavigateToContactCard,
            onAddContactClick = onAddContactClick
        )
    }
}

fun NavGraphBuilder.favouritesContactsListScreen(onNavigateToContactCard: (contact: Int) -> Unit) {
    composable<FavouriteContactsList> {
        FavouriteContactsScreen(
            onNavigateToContactCard = onNavigateToContactCard
        )
    }
}

fun NavGraphBuilder.contactCardScreen(
    onBackButtonClick: () -> Unit
) {
    composable<ContactDetails> { backStackEntry ->
        val contact: ContactDetails = backStackEntry.toRoute()
        ContactCardScreen(
            contact.contactId,
            onBackButtonClick = onBackButtonClick
        )
    }
}

fun NavGraphBuilder.addContactScreen(
    onBackButtonClick: () -> Unit,
    onAddContactClick: (Int) -> Unit
) {
    composable<AddContact> {
        AddNewContact(
            onBackButtonClick = onBackButtonClick,
            onAddContactClick = onAddContactClick
        )
    }
}

fun NavGraphBuilder.editContactScreen() {
    composable<EditContact> { backStackEntry ->
        val contact: EditContact = backStackEntry.toRoute()
        // TODO: add edit screen
    }
}

fun NavController.navigateToContactCard(id: Int) {
    navigate(route = ContactDetails(id))
}

fun NavController.navigateToAddContactScreen() {
    navigate(route = AddContact) {
        launchSingleTop = true
    }
}

fun NavController.navigateToEditContact(id: Int) {
    navigate(route = EditContact(id))
}