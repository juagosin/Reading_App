package com.juagosin.estudio.presentation.screens.addbook

sealed class AddBookEvent {
    data class OnTitleChange(val value:String): AddBookEvent()
    data class OnAuthorChange(val value:String): AddBookEvent()
    data class OnImageUrlChange(val value:String): AddBookEvent()
    data class OnDescriptionChange(val value:String): AddBookEvent()
    data class OnStatusChange(val value:Int): AddBookEvent()
    data class OnDateStartChange(val value:Long): AddBookEvent()
    data class OnDateEndChange(val value:Long): AddBookEvent()

    object SaveBook: AddBookEvent()

}