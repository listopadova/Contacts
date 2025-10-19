package com.example.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddNewContactViewModel(private val repository: ContactsRepository): ViewModel() {
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val repository = (this[APPLICATION_KEY] as ContactsApp).repository
                AddNewContactViewModel(repository)
            }
        }
    }
    private val _uiState = MutableStateFlow(AddNewContactState())
    val uiState: StateFlow<AddNewContactState> = _uiState.asStateFlow()

    fun updateName(name: String) {
        _uiState.update {
            it.copy(
                name = name,
                isEmptyNameError = name.isEmpty()
            )
        }
    }

    fun updateSurname(surname: String) {
        _uiState.update {
            it.copy(
                surname = surname,
                isEmptySurnameError = surname.isEmpty()
            )
        }
    }

    fun updatePhone(phone: String) {
        _uiState.update {
            it.copy(
                phone = phone,
                isEmptyPhoneError = phone.isEmpty()
            )
        }
    }
    fun addContact() {
        _uiState.update {
            it.copy(
                isEmptyNameError = it.name.isEmpty(),
                isEmptySurnameError = it.surname.isEmpty(),
                isEmptyPhoneError = it.phone.isEmpty()
            )
        }

        if (!_uiState.value.isEmptyNameError &&
            !_uiState.value.isEmptySurnameError &&
            !_uiState.value.isEmptyPhoneError) {
            val contact = Contact (
                name = _uiState.value.name,
                surname = _uiState.value.surname,
                phone = _uiState.value.phone
            )
            viewModelScope.launch(Dispatchers.IO) {
                repository.addContact(contact)
            }

            _uiState.update {
                it.copy(
                    name = "",
                    surname = "",
                    phone = ""
                )
            }
        }
    }

}

data class AddNewContactState(
    val name: String = "",
    val surname: String = "",
    val phone: String = "",
    val isEmptyNameError: Boolean = false,
    val isEmptySurnameError: Boolean = false,
    val isEmptyPhoneError: Boolean = false
)

