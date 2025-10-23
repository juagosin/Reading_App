package com.juagosin.readingAPP.domain.repository

import com.juagosin.readingAPP.data.local.entity.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {

    fun getBooksOrderByDateAd(): Flow<List<Book>>
    fun getFollowBooksOrderByDateAs(): Flow<List<Book>>
    suspend fun getBookById(id: Int): Book?
    suspend fun getBookReading(): Book?
    suspend fun getLastBookRead(): Book?
    suspend fun deleteBook(id: Int?)
    suspend fun insertBook(book: Book)
    suspend fun getPercentFinished():Float
}