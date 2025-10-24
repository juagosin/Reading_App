package com.juagosin.readingAPP.domain.use_case

import com.juagosin.readingAPP.data.local.entity.BookEntity
import com.juagosin.readingAPP.data.local.entity.InvalidBookException
import com.juagosin.readingAPP.domain.model.Book
import com.juagosin.readingAPP.domain.repository.BookRepository

class AddBookUseCase (
    private val repository: BookRepository
){
    @Throws(InvalidBookException::class)
    suspend operator fun invoke(book: Book) {
        if(book.title.isBlank()) {
            throw InvalidBookException("El título de la tarea no puede estar vacía.")
        }
        if(book.author.isBlank()) {
            throw InvalidBookException("El autor de la tarea no puede estar vacío.")
        }
        repository.insertBook(book)
    }

}