package com.juagosin.readingAPP.presentation.screens.edit

import com.juagosin.readingAPP.data.local.entity.Book

data class EditState (
    val book: Book? =null,
    val errorMessage: String? = null,
    val id: Int = 0,
    val title: String = "",
    val author: String ="",
    val imageUrl: String = "",
    val dateAd: Long = System.currentTimeMillis(),
    val startDate: Long? = null,
    val endDate: Long? = null,
    val status: Int = 1,
    val description: String = "",
    val isSuccess: Boolean = false,
    val isEditing: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,

    //Validaciones
    val titleError: String? = null,
    val authorError: String? = null,

)