package com.juagosin.readingAPP.presentation.screens.detail

import com.juagosin.readingAPP.domain.model.Book


data class DetailState(
    val isLoading: Boolean = false,
    val book: Book? =null,
    val isDeleting: Boolean = false,
    val errorMessage: String? = null
)