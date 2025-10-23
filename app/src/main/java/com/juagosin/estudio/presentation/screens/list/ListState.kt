package com.juagosin.estudio.presentation.screens.list

import com.juagosin.estudio.data.local.entity.Book

data class ListState(
    val books: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
) {
}