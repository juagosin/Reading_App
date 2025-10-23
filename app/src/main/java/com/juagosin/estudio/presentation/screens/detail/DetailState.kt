package com.juagosin.estudio.presentation.screens.detail

import com.juagosin.estudio.data.local.entity.Book

data class DetailState(
    val isLoading: Boolean = false,
    val book: Book? =null,
    val isDeleting: Boolean = false,
    val errorMessage: String? = null
)