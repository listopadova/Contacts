package com.example.contacts.data

import androidx.core.util.ObjectsCompat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take

class ContactsRepository(
    private val contactDao: ContactDao
) {
    val flow: Flow<List<Contact>> =
        contactDao.getAll().distinctUntilChanged().map { contactEntities ->
            contactEntities.map { contact ->
                Contact(
                    id = contact.id,
                    name = contact.name,
                    surname = contact.surname,
                    phone = contact.phone,
                    email = contact.email,
                    isFavourite = contact.isFavourite
                )
            }
        }

    val favouritesFlow: Flow<List<Contact>> =
        contactDao.getFavourites().distinctUntilChanged().map { contactEntities ->
            contactEntities.map { contact ->
                Contact(
                    id = contact.id,
                    name = contact.name,
                    surname = contact.surname,
                    phone = contact.phone,
                    email = contact.email,
                    isFavourite = contact.isFavourite
                )
            }
        }


    fun addContact(contact: Contact) {
        contactDao.insertContact(
            ContactEntity(
                id = ObjectsCompat.hashCode(contact.name + contact.surname + contact.phone),
                name = contact.name,
                surname = contact.surname,
                phone = contact.phone,
                email = contact.email,
                isFavourite = contact.isFavourite
            )
        )
    }

    fun deleteContact(id: Int) {
        contactDao.deleteContactById(id)
    }

    suspend fun switchFavourite(id: Int) {
        contactDao.getContact(id).take(1).collect { contactEntity ->
            val updatedContact = contactEntity.copy(isFavourite = !contactEntity.isFavourite)
            contactDao.updateContact(updatedContact)
        }
    }

    fun getContact(id: Int): Flow<ContactEntity> {
        return contactDao.getContact(id)
    }
}

data class Contact(
    val id: Int = -1,
    val name: String,
    val surname: String,
    val phone: String,
    val email: String? = null,
    val isFavourite: Boolean = false
)