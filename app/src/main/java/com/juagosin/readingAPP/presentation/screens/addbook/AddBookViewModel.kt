package com.juagosin.readingAPP.presentation.screens.addbook

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juagosin.readingAPP.data.local.entity.Book
import com.juagosin.readingAPP.domain.use_case.BooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddBookViewModel @Inject constructor(
    private val booksUseCase: BooksUseCase
) : ViewModel() {

    var state by mutableStateOf(AddBookState())

    fun onEvent(event: AddBookEvent) {
        when (event) {
            is AddBookEvent.OnTitleChange -> {
                state = state.copy(title = event.value, titleError = null)
            }

            is AddBookEvent.OnAuthorChange -> {
                state = state.copy(author = event.value, authorError = null)
            }

            is AddBookEvent.OnImageUrlChange -> {
                state = state.copy(imageUrl = event.value)
            }

            is AddBookEvent.OnDescriptionChange -> {
                state = state.copy(description = event.value)
            }

            is AddBookEvent.OnStatusChange -> {
                state = state.copy(status = event.value)
            }

            is AddBookEvent.SaveBook -> {
                validateFields()
            }

            is AddBookEvent.OnDateStartChange ->{
                state = state.copy(startDate = event.value)

            }
            is AddBookEvent.OnDateEndChange ->{
                state = state.copy(endDate = event.value)

            }
        }

    }
private fun validateFields() {
    var hasError = false
    if (state.title.isBlank()) {
        Log.d("TAG", "validateFields: ${state.title}")
        state = state.copy(titleError = "El título no puede estar vacío")
        hasError = true
    }
    if (state.author.isBlank()) {
        state = state.copy(authorError = "El autor no puede estar vacío")
        hasError = true
    }
    if (hasError) return  // No continuar si hay errores
    saveBook()

}
    private fun saveBook() {
        Log.d("TAG", "saveBook: ${state}")
        state = state.copy(isSaving = true)
        try {
            viewModelScope.launch {
                booksUseCase.addBookUseCase(
                    Book(
                        title = state.title,
                        author = state.author,
                        imageUrl = state.imageUrl,
                        description = state.description,
                        status = state.status,
                        dateAd = System.currentTimeMillis(),
                        dateStart = state.startDate,
                        dateEnd = state.endDate
                    )
                )
            }
            state = state.copy(isSaving = false)
            state = state.copy(isSuccess = true)

        } catch (e: Exception) {
            state = state.copy(isSaving = false)
            state = state.copy(error = e.message)
            state = state.copy(isSuccess = false)


        }

    }


}