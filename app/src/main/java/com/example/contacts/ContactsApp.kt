package com.example.contacts

import android.app.Application
import androidx.room.Room
import com.example.contacts.data.ContactsDatabase

class ContactsApp : Application() {

    val database by lazy {
        Room.databaseBuilder(
            context = applicationContext,
            klass = ContactsDatabase::class.java,
            name = "contacts-database"
        ).build()
    }
    val repository: ContactsRepository by lazy {
        ContactsRepository(database.contactDao())
    }
}