package com.juagosin.readingAPP.presentation.screens.list

import com.juagosin.readingAPP.data.local.entity.Book

data class ListState(
    val books: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
) {
}