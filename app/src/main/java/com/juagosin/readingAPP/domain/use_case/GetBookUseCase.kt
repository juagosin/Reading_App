package com.juagosin.readingAPP.domain.use_case


import com.juagosin.readingAPP.domain.model.Book
import com.juagosin.readingAPP.domain.repository.BookRepository

class GetBookUseCase(private val repository: BookRepository) {
    suspend operator fun invoke(id: Int): Book? {
        return repository.getBookById(id)
    }

}