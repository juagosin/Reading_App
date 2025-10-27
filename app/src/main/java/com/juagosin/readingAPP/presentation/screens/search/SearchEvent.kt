package com.juagosin.readingAPP.presentation.screens.search

sealed class SearchEvent {
    data class OnTitleChange(val value:String): SearchEvent()
    object OnSearch: SearchEvent()
}


