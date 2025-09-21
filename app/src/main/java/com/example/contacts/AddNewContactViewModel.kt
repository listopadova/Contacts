package com.example.contacts

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddNewContactViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(AddNewContactState())
    val uiState: StateFlow<AddNewContactState> = _uiState.asStateFlow()

    fun updateName(name: String) {
        _uiState.update { it.copy(name = name) }
    }

    fun updateSurname(surname: String) {
        _uiState.update { it.copy(surname = surname) }
    }

    fun updatePhone(phone: String) {
        _uiState.update { it.copy(phone = phone) }
    }
    fun addContact() {
        val value = _uiState.value
        val contact = Contact(
            name = value.name,
            surname = value.surname,
            phone = value.phone
        )
        // add contact to storage

        _uiState.update {
            it.copy(
                name = "",
                surname = "",
                phone = ""
            )
        }
    }

}

data class AddNewContactState (
    val name: String = "",
    val surname: String = "",
    val phone: String = ""
)

