package com.juagosin.estudio.domain.use_case

import com.juagosin.estudio.domain.repository.BookRepository

class DeleteBookUseCase(
    private val repository: BookRepository

) {
    suspend operator fun invoke(id: Int?) {
        repository.deleteBook(id)
    }

}