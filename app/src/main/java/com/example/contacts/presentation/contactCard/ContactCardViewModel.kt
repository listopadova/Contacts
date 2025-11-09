package com.example.contacts.presentation.contactCard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.contacts.ContactsApp
import com.example.contacts.data.ContactsRepository
import com.example.contacts.presentation.contactsList.ContactsListItemState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ContactCardViewModel(private val repository: ContactsRepository): ViewModel() {
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val repository =
                    (this[ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY] as ContactsApp).repository
                ContactCardViewModel(repository)
            }
        }
    }

    fun getContact(id: Int): StateFlow<ContactsListItemState> {
        return repository.getContact(id).map { contact ->
            ContactsListItemState(
                id = contact.id,
                name = contact.name,
                surname = contact.surname,
                phone = contact.phone
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Companion.WhileSubscribed(),
            initialValue = ContactsListItemState()
        )
    }

}