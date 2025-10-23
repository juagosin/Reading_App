package com.juagosin.estudio.presentation.screens.home

import com.juagosin.estudio.data.local.entity.Book

data class HomeState(
    val isLoadingFollow: Boolean = false,
    val errorLoadingFollow: String? = null,
    val isLoadingReading: Boolean = false,
    val errorLoadingReading: String? = null,
    val isLoadingLastBookRead: Boolean = false,
    val errorLoadingLastBook: String? = null,
    val booksFollowing: List<Book> = emptyList(),
    val bookReading: Book? = null,
    val lastBookRead: Book? = null,
    val isLoadingBooks: Boolean = false,
    val errorLoadingBooks: String? = null,
    val books: List<Book> = emptyList(),
    val percentBooksFinished: Float = 0f,

)