package com.juagosin.readingAPP.domain.use_case


import com.juagosin.readingAPP.domain.model.Book
import com.juagosin.readingAPP.domain.repository.BookRepository

class GetLastBookReadUseCase(private val repository: BookRepository) {
    suspend operator fun invoke(): Book? {
        return repository.getLastBookRead()
    }
}