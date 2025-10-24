package com.juagosin.readingAPP.data.repository

import com.juagosin.readingAPP.data.local.dao.BookDao
import com.juagosin.readingAPP.data.local.mapper.toDomain
import com.juagosin.readingAPP.data.local.mapper.toEntity
import com.juagosin.readingAPP.domain.model.Book

import com.juagosin.readingAPP.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookRepositoryImpl(
    private val bookDao: BookDao
) : BookRepository {
    override fun getBooksOrderByDateAd(): Flow<List<Book>> {
        return bookDao.getBooksOrderByDateAd().map { it.toDomain() }
    }

    override fun getFollowBooksOrderByDateAs(): Flow<List<Book>> {
        return bookDao.getFollowBooksOrderByDateAs().map { it.toDomain() }
    }

    override suspend fun getBookById(id: Int): Book? {
        return bookDao.getBookById(id)?.toDomain()
    }

    override suspend fun getBookReading(): Book? {
        return bookDao.getBookReading()?.toDomain()
    }

    override suspend fun getLastBookRead(): Book? {
        return bookDao.getLastBookRead()?.toDomain()
    }

    override suspend fun deleteBook(id: Int?) {
        bookDao.deleteBook(id)
    }

    override suspend fun insertBook(book: Book) {
        bookDao.insertBook(book.toEntity())
    }

    override suspend fun getPercentFinished(): Float {
        return bookDao.getPercentFinished()
    }
}