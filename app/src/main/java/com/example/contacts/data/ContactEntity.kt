package com.example.contacts.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class ContactEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "surname") val surname: String,
    @ColumnInfo(name = "phone") val phone: String,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "isFavourite") val isFavourite: Boolean
)