package com.juagosin.readingAPP.data.remote.dto
import com.juagosin.readingAPP.domain.model.BookSearchResult

fun BookDto.toBookSearchResult() = BookSearchResult(
    title = title,
    author = authorName?.firstOrNull() ?: "Autor desconocido",
    coverUrl = coverId?.let { "https://covers.openlibrary.org/b/id/$it-M.jpg" }
)