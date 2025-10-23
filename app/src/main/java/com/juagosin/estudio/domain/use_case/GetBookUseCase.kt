package com.juagosin.estudio.domain.use_case

import com.juagosin.estudio.data.local.entity.Book
import com.juagosin.estudio.domain.repository.BookRepository

class GetBookUseCase(private val repository: BookRepository) {
    suspend operator fun invoke(id: Int): Book? {
        return repository.getBookById(id)
    }

}