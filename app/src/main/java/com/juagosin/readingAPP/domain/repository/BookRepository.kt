package com.juagosin.readingAPP.domain.repository

import com.juagosin.readingAPP.domain.model.Book
import com.juagosin.readingAPP.domain.model.BookSearchResult
import kotlinx.coroutines.flow.Flow

interface BookRepository {

    fun getBooksOrderByDateAd(): Flow<List<Book>>
    fun getLastsBooksOrderByDateAd(): Flow<List<Book>>
    fun getFollowBooksOrderByDateAs(): Flow<List<Book>>
    suspend fun getBookById(id: Int): Book?
    suspend fun getBookReading(): Book?
    suspend fun getLastBookRead(): Book?
    suspend fun deleteBook(id: Int?)
    suspend fun insertBook(book: Book)
    suspend fun getPercentFinished():Float

    suspend fun searchBooksByTitle(title: String): Result<List<BookSearchResult>>
}