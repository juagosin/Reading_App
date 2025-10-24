package com.juagosin.readingAPP.presentation.screens.addbook

import com.juagosin.readingAPP.domain.model.BookStatus

data class AddBookState(
    val title: String = "",
    val author: String = "",
    val imageUrl: String = "",
    val description: String = "",
    val status: BookStatus = BookStatus.PENDING,
    val startDate: Long? = null,
    val endDate: Long? = null,
    val isSaving: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null,

    //Validaciones
    val titleError: String? = null,
    val authorError: String? = null,


    )