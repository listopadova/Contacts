package com.example.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ContactsListViewModel(private val repository: ContactsRepository): ViewModel() {
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val repository = (this[APPLICATION_KEY] as ContactsApp).repository
                ContactsListViewModel(repository)
            }
        }
    }

    val uiState: StateFlow<ContactsListState> = repository.stateFlow.map { repositoryContactList ->
        val contactItems: List<ContactsListItemState> = repositoryContactList.asViewModelContacts()
        ContactsListState(contactItems)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ContactsListState()
    )

    private fun List<Contact>.asViewModelContacts(): List<ContactsListItemState> {
        return map { repoContact ->
            ContactsListItemState(
                id = repoContact.id,
                name = repoContact.name,
                surname = repoContact.surname,
                phone = repoContact.phone
            ) }
    }

    fun delete(item: ContactsListItemState) {
        repository.deleteContact(item.id)
    }
}

data class ContactsListState(
    val contactItems: List<ContactsListItemState> = listOf()
)

data class ContactsListItemState(
    val id: Int = -1,
    val name: String = "",
    val surname: String = "",
    val phone: String = ""
) {
    val initials: String
        get() = (name.take(1) + surname.take(1)).uppercase()
    val fullName: String
        get() = "$name $surname"
}