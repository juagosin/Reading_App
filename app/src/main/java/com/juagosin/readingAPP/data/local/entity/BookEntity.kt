package com.juagosin.readingAPP.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book")
data class BookEntity(
    val title: String,
    val author: String,
    val imageUrl: String,
    val description: String,
    val dateAd: Long,
    val dateStart: Long?,
    val dateEnd: Long?,
    val status: Int,
    @PrimaryKey(autoGenerate = true) val id: Int? = null

)

class InvalidBookException(message: String): Exception(message)