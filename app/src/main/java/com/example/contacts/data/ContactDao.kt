package com.example.contacts.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ContactDao {
    @Query("SELECT * FROM contacts")
    fun getAll(): List<ContactEntity>
    @Query("SELECT * FROM contacts WHERE id = :contactId")
    fun getContact(contactId: Int): List<ContactEntity>
    @Insert
    fun insertContact(contact: ContactEntity)
    @Delete
    fun deleteContact(contact: ContactEntity)
    @Update
    fun updateContact(contact: ContactEntity)
}