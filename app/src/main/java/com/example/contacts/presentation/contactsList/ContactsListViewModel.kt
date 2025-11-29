package com.example.contacts.presentation.contactsList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.contacts.ContactsApp
import com.example.contacts.data.Contact
import com.example.contacts.data.ContactsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ContactsListViewModel(private val repository: ContactsRepository): ViewModel() {
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val repository = (this[APPLICATION_KEY] as ContactsApp).repository
                ContactsListViewModel(repository)
            }
        }
    }

    val uiState: StateFlow<ContactsListState> = repository.flow.map { repositoryContactList ->
        val contactItems: List<ContactsListItemState> = repositoryContactList.asViewModelContacts()
        if (contactItems.isEmpty()) {
            ContactsListState.Empty
        } else {
            val groupedContacts: Map<String, List<ContactsListItemState>> = contactItems
                .sortedBy { it.surname }
                .groupBy { it.surname[0].toString().uppercase() }
            ContactsListState.Items(groupedContacts)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ContactsListState.Empty
    )

    private fun List<Contact>.asViewModelContacts(): List<ContactsListItemState> {
        return map { repoContact ->
            ContactsListItemState(
                id = repoContact.id,
                name = repoContact.name,
                surname = repoContact.surname,
                phone = repoContact.phone,
                isFavourite = repoContact.isFavourite
            ) }
    }

    fun delete(item: ContactsListItemState) {
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteContact(item.id)
        }
    }

    fun switchFavourite(item: ContactsListItemState) {
        viewModelScope.launch(Dispatchers.IO){
            repository.switchFavourite(item.id)
        }
    }
}
sealed class ContactsListState {
    data object Empty: ContactsListState()
    data class Items(val contactItems: Map<String, List<ContactsListItemState>>): ContactsListState()
}

data class ContactsListItemState(
    val id: Int = -1,
    val name: String = "",
    val surname: String = "",
    val phone: String = "",
    val isFavourite: Boolean = false
) {
    val initials: String
        get() = (name.take(1) + surname.take(1)).uppercase()
    val fullName: String
        get() = "$name $surname"
}