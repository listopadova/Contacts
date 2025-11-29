package com.example.contacts.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Query("SELECT * FROM contacts")
    fun getAll(): Flow<List<ContactEntity>>
    @Query("SELECT * FROM contacts WHERE isFavourite = 1")
    fun getFavourites(): Flow<List<ContactEntity>>
    @Query("SELECT * FROM contacts WHERE id = :contactId")
    fun getContact(contactId: Int): Flow<ContactEntity>
    @Insert
    fun insertContact(contact: ContactEntity)
    @Query("DELETE FROM contacts WHERE id = :contactId")
    fun deleteContactById(contactId: Int)
    @Update
    fun updateContact(contact: ContactEntity)
}