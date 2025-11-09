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

fun NavGraphBuilder.contactCardScreen() {
    composable<ContactDetails> { backStackEntry ->
        val contact: ContactDetails = backStackEntry.toRoute()
        ContactCardScreen(contact.contactId)
    }
}

fun NavGraphBuilder.addContactScreen() {
    composable<AddContact> { backStackEntry ->
        AddNewContact()
    }
}

fun NavController.navigateToContactCard(id: Int) {
    navigate(route = ContactDetails(id))
}

fun NavController.navigateToAddContactScreen() {
    navigate(route = AddContact)
}