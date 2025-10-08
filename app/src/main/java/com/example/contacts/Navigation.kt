package com.example.contacts

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
object ContactsList
@Serializable
data class ContactDetails(val contactId: Int)

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

fun NavController.navigateToContactCard(id: Int) {
    navigate(route = ContactDetails(id))
}