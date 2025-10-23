package com.juagosin.readingAPP.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.juagosin.readingAPP.data.local.dao.BookDao
import com.juagosin.readingAPP.data.local.entity.Book

@Database(
    entities = [Book::class],
    version = 2
)
abstract class BookDatabase: RoomDatabase() {
    abstract val bookDao: BookDao
    companion object {
        const val DATABASE_NAME = "book_db"
    }


}