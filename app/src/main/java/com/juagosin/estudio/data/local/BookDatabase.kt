package com.juagosin.estudio.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.juagosin.estudio.data.local.dao.BookDao
import com.juagosin.estudio.data.local.entity.Book

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