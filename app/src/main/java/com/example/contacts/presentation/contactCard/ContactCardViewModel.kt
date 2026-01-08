package com.example.contacts.presentation.contactCard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.contacts.ContactsApp
import com.example.contacts.data.ContactsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

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

    fun getContact(id: Int): StateFlow<ContactCardState> {
        return repository.getContact(id).map { contact ->
            ContactCardState(
                id = contact.id,
                name = contact.name,
                surname = contact.surname,
                phone = contact.phone,
                email = contact.email,
                isFavourite = contact.isFavourite
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Companion.WhileSubscribed(),
            initialValue = ContactCardState()
        )
    }

    fun switchFavourite(item: ContactCardState) {
        viewModelScope.launch(Dispatchers.IO){
            repository.switchFavourite(item.id)
        }
    }

}

data class ContactCardState(
    val id: Int = -1,
    val name: String = "",
    val surname: String = "",
    val phone: String = "",
    val email: String? = null,
    val isFavourite: Boolean = false
) {
    val initials: String
        get() = (name.take(1) + surname.take(1)).uppercase()
    val fullName: String
        get() = "$name $surname"
}