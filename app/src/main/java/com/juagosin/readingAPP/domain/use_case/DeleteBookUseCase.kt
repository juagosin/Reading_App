package com.juagosin.readingAPP.domain.use_case

import com.juagosin.readingAPP.domain.repository.BookRepository

class DeleteBookUseCase(
    private val repository: BookRepository

) {
    suspend operator fun invoke(id: Int?) {
        repository.deleteBook(id)
    }

}