package com.juagosin.estudio.presentation.screens.detail

import com.juagosin.estudio.presentation.screens.addbook.AddBookEvent

sealed class DetailEvent {
    data class LoadBook(val id:Int): DetailEvent()
    data class DeleteBook(val id:Int): DetailEvent()
}