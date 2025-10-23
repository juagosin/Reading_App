package com.juagosin.readingAPP.presentation.screens.detail

sealed class DetailEvent {
    data class LoadBook(val id:Int): DetailEvent()
    data class DeleteBook(val id:Int): DetailEvent()
}