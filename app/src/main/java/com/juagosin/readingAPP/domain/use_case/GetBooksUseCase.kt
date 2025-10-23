package com.juagosin.readingAPP.domain.use_case

import com.juagosin.readingAPP.data.local.entity.Book
import com.juagosin.readingAPP.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow

class GetBooksUseCase(
    private val repository: BookRepository
) {

    operator fun invoke(): Flow<List<Book>> {
        return repository.getBooksOrderByDateAd()
    }

}