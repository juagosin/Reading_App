package com.juagosin.readingAPP.presentation.screens.edit

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juagosin.readingAPP.domain.model.Book
import com.juagosin.readingAPP.domain.use_case.BooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    private val booksUseCase: BooksUseCase
) : ViewModel() {
    var state by mutableStateOf(EditState())

    fun onEvent(event: EditEvent) {
        when (event) {
            is EditEvent.LoadBook -> {
                loadBook(event.id)
            }

            is EditEvent.EditBook -> {
                validateFields()
            }

            is EditEvent.OnTitleChange -> {
                state = state.copy(title = event.value)
            }

            is EditEvent.OnAuthorChange -> {
                state = state.copy(author = event.value)
            }

            is EditEvent.OnDescriptionChange -> {
                state = state.copy(description = event.value)
            }

            is EditEvent.OnImageUrlChange -> {
                state = state.copy(imageUrl = event.value)
            }
            is EditEvent.OnStatusChange -> {
                state = state.copy(status = event.value)
            }

            is EditEvent.OnDateEndChange -> {
                state = state.copy(endDate = event.value)
            }
            is EditEvent.OnDateStartChange -> {
                state = state.copy(startDate = event.value)
            }
        }
    }

    private fun loadBook(id: Int) {

        viewModelScope.launch {
            state = state.copy(isLoading = true)
            try {
                val book = booksUseCase.getBookUseCase(id)
                state = state.copy(
                    id = book?.id ?: 0,
                    book = book,
                    title = book?.title ?: "",
                    author = book?.author ?: "",
                    description = book?.description ?: "",
                    imageUrl = book?.imageUrl ?: "",
                    endDate = book?.dateFinished,
                    startDate = book?.dateStarted,
                    dateAd = book?.dateAdded!!,
                    status = book.status,
                    isLoading = false
                )
                state = state.copy(isLoading = false)

            } catch (e: Exception) {
                state = state.copy(isLoading = false)
                state = state.copy(errorMessage = e.message)
            }

        }
    }
    private fun validateFields(){
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
        editBook()
    }
    private fun editBook() {
        Log.d("EditViewModel", "editBook: $state")
        state = state.copy(isEditing = true)
        try{
            viewModelScope.launch {
                booksUseCase.addBookUseCase(
                    Book(
                        id = state.id,
                        title = state.title,
                        author = state.author,
                        imageUrl = state.imageUrl,
                        description = state.description,
                        status = state.status,
                        dateStarted = state.startDate,
                        dateFinished = state.endDate,
                        dateAdded = state.dateAd
                    )
                )
            }
            state = state.copy(isEditing = false)
            state = state.copy(isSuccess = true)

        }catch (e: Exception){
            state = state.copy(isEditing = false)
            state = state.copy(error = e.message)
            state = state.copy(isSuccess = false)
        }

    }

}