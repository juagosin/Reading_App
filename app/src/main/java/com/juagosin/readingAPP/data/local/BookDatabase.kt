package com.juagosin.readingAPP.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.juagosin.readingAPP.data.local.dao.BookDao
import com.juagosin.readingAPP.data.local.entity.BookEntity

@Database(
    entities = [BookEntity::class],
    version = 3
)
abstract class BookDatabase: RoomDatabase() {
    abstract val bookDao: BookDao
    companion object {
        const val DATABASE_NAME = "book_db"
    }


}