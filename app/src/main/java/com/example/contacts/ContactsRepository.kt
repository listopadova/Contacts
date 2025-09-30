package com.example.contacts

import androidx.core.util.ObjectsCompat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ContactsRepository(
    private val localDataSource: LocalDataSource
) {
    private val mutableStateFlow: MutableStateFlow<List<Contact>> = MutableStateFlow(emptyList())
    val stateFlow: StateFlow<List<Contact>> = mutableStateFlow.asStateFlow()

    fun addContact(name: String, surname: String, phone: String) {
        val contact = Contact(
            id = ObjectsCompat.hashCode(name + surname + phone),
            name = name,
            surname = surname,
            phone = phone
        )
        localDataSource.addContact(contact)
        mutableStateFlow.update {
            localDataSource.list.toList()
        }
    }

    fun deleteContact(id: Int) {
        val contact = localDataSource.getContact(id)
        if (contact != null) {
            localDataSource.deleteContact(contact)
            mutableStateFlow.update {
                localDataSource.list.toList()
            }
        }
    }
}

class LocalDataSource {
    val list: MutableList<Contact> = mutableListOf()

    fun addContact(contact: Contact) {
        list.add(contact)
    }

    fun getContact(id: Int): Contact? {
        return list.find { it.id == id }
    }

    fun updateContact(contact: Contact) {
    }

    fun deleteContact(contact: Contact) {
        list.remove(contact)
    }
}

data class Contact (
    val id: Int,
    val name: String,
    val surname: String,
    val phone: String
)