package com.juagosin.readingAPP.presentation.screens.list

import com.juagosin.readingAPP.domain.model.Book


data class ListState(
    val books: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
) {
}