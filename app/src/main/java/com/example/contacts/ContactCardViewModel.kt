package com.example.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

class ContactCardViewModel(private val repository: ContactsRepository): ViewModel() {
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val repository = (this[APPLICATION_KEY] as ContactsApp).repository
                ContactCardViewModel(repository)
            }
        }
    }

    fun getContact(id: Int): ContactsListItemState {
        val contact = repository.getContact(id)?.let { contact ->
            ContactsListItemState(
                id = contact.id,
                name = contact.name,
                surname = contact.surname,
                phone = contact.phone
            )
        }
        return contact ?: ContactsListItemState()
    }

}