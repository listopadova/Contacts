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
        TabRootScreen(
            navController = contactsNavController,
            title = "Contacts"
        ) { modifier ->
            NavHost(
                navController = contactsNavController,
                startDestination = ContactsList,
                modifier = modifier
            ) {
                contactsListScreen(
                    onNavigateToContactCard = { contactId ->
                        contactsNavController.navigateToContactCard(contactId)
                    }
                )
                contactCardScreen()
                addContactScreen()
                editContactScreen()
            }
        }
    }
}

fun NavGraphBuilder.favouritesTab() {
    composable< FavouritesTab> {
        val favouritesNavController = rememberNavController()
        TabRootScreen(
            navController = favouritesNavController,
            title = "Favourites"
        ) { modifier ->
            NavHost(
                navController = favouritesNavController,
                startDestination = FavouriteContactsList,
                modifier = modifier
            ) {
                favouritesContactsListScreen(onNavigateToContactCard = { contactId ->
                    favouritesNavController.navigateToContactCard(contactId)
                })
                contactCardScreen()
                addContactScreen()
                editContactScreen()
            }
        }
    }
}

fun NavGraphBuilder.contactsListScreen(
    onNavigateToContactCard: (contact: Int) -> Unit
) {
    composable<ContactsList> {
        ContactsScreen(
            onNavigateToContactCard = onNavigateToContactCard
        )
    }
}

fun NavGraphBuilder.favouritesContactsListScreen(onNavigateToContactCard: (contact: Int) -> Unit) {
    composable<FavouriteContactsList> {
        // TODO: add favourites
    }
}

fun NavGraphBuilder.contactCardScreen() {
    composable<ContactDetails> { backStackEntry ->
        val contact: ContactDetails = backStackEntry.toRoute()
        ContactCardScreen(contact.contactId)
    }
}

fun NavGraphBuilder.addContactScreen() {
    composable<AddContact> {
        AddNewContact()
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