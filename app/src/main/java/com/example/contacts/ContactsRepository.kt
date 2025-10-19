package com.example.contacts

import androidx.core.util.ObjectsCompat
import com.example.contacts.data.ContactDao
import com.example.contacts.data.ContactEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class ContactsRepository(
    private val contactDao: ContactDao
) {
    val flow: Flow<List<Contact>> = contactDao.getAll().distinctUntilChanged().map { contactEntities ->
        contactEntities.map { contact ->
            Contact(
                id = contact.id,
                name = contact.name,
                surname = contact.surname,
                phone = contact.phone
            )
        }
}

    fun addContact(contact: Contact) {
        contactDao.insertContact(ContactEntity(
            id = ObjectsCompat.hashCode(contact.name + contact.surname + contact.phone),
            name = contact.name,
            surname = contact.surname,
            phone = contact.phone
        ))
    }

    fun deleteContact(id: Int) {
        contactDao.deleteContactById(id)
    }

    fun getContact(id: Int): Flow<ContactEntity> {
        return contactDao.getContact(id)
    }
}

data class Contact (
    val id: Int = -1,
    val name: String,
    val surname: String,
    val phone: String
)