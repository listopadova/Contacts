package com.example.contacts.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
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

fun NavGraphBuilder.contactsListScreen(onNavigateToContactCard: (contact: Int) -> Unit) {
    composable<ContactsList> {
        ContactsScreen(onNavigateToContactCard = onNavigateToContactCard)
    }
}

fun NavGraphBuilder.favouritesContactsListScreen(onNavigateToContactCard: (contact: Int) -> Unit) {
    composable<FavouriteContactsList> {
        // TODO: replace with favorites
        ContactsScreen(onNavigateToContactCard = onNavigateToContactCard)
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
    composable<AddContact> { backStackEntry ->
        val contact: EditContact = backStackEntry.toRoute()
        // TODO: replace with edit screen
        AddNewContact()
    }
}

fun NavController.navigateToContactCard(id: Int) {
    navigate(route = ContactDetails(id))
}

fun NavController.navigateToAddContactScreen() {
    navigate(route = AddContact)
}

fun NavController.navigateToEditContact(id: Int) {
    navigate(route = EditContact(id))
}