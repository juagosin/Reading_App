package com.juagosin.estudio.presentation.screens.edit

import com.juagosin.estudio.presentation.screens.addbook.AddBookEvent
import com.juagosin.estudio.presentation.screens.detail.DetailEvent

sealed class EditEvent {
    data class LoadBook(val id:Int): EditEvent()
    object EditBook: EditEvent()
    data class OnTitleChange(val value:String): EditEvent()
    data class OnAuthorChange(val value:String): EditEvent()
    data class OnImageUrlChange(val value:String): EditEvent()
    data class OnDescriptionChange(val value:String): EditEvent()
    data class OnStatusChange(val value:Int): EditEvent()
    data class OnDateStartChange(val value:Long): EditEvent()
    data class OnDateEndChange(val value:Long): EditEvent()
}