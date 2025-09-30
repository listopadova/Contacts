package com.example.contacts

import android.app.Application

class ContactsApp : Application() {
    val repository: ContactsRepository = ContactsRepository(LocalDataSource())
}