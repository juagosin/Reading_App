package com.juagosin.readingAPP.data.repository

import com.juagosin.readingAPP.data.local.dao.BookDao
import com.juagosin.readingAPP.data.local.entity.Book
import com.juagosin.readingAPP.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow

class BookRepositoryImpl(
    private val bookDao: BookDao
) : BookRepository {
    override fun getBooksOrderByDateAd(): Flow<List<Book>> {
        return bookDao.getBooksOrderByDateAd()
    }

    override fun getFollowBooksOrderByDateAs(): Flow<List<Book>> {
        return bookDao.getFollowBooksOrderByDateAs()
    }

    override suspend fun getBookById(id: Int): Book? {
        return bookDao.getBookById(id)
    }

    override suspend fun getBookReading(): Book? {
        return bookDao.getBookReading()
    }

    override suspend fun getLastBookRead(): Book? {
        return bookDao.getLastBookRead()
    }

    override suspend fun deleteBook(id: Int?) {
        bookDao.deleteBook(id)
    }

    override suspend fun insertBook(book: Book) {
        bookDao.insertBook(book)
    }

    override suspend fun getPercentFinished(): Float {
        return bookDao.getPercentFinished()
    }
}