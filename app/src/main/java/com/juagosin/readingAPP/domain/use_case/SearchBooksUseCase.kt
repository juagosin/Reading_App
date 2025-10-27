package com.juagosin.readingAPP.domain.use_case

import com.juagosin.readingAPP.domain.model.BookSearchResult
import com.juagosin.readingAPP.domain.repository.BookRepository

class SearchBooksUseCase(
    private val repository: BookRepository
) {
    suspend operator fun invoke(title: String): Result<List<BookSearchResult>> {
        if (title.isBlank()) {
            return Result.failure(IllegalArgumentException("El título no puede estar vacío"))
        }
        return repository.searchBooksByTitle(title)
    }
}