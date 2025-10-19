package com.example.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ContactCardViewModel(private val repository: ContactsRepository): ViewModel() {
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val repository = (this[APPLICATION_KEY] as ContactsApp).repository
                ContactCardViewModel(repository)
            }
        }
    }

    fun getContact(id: Int): Flow<ContactsListItemState> {
        return repository.getContact(id).map { contact ->
            ContactsListItemState(
                id = contact.id,
                name = contact.name,
                surname = contact.surname,
                phone = contact.phone
            )
        }
    }

}