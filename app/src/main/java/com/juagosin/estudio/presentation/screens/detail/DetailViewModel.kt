package com.juagosin.estudio.presentation.screens.detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juagosin.estudio.domain.use_case.BooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val booksUseCase: BooksUseCase
) : ViewModel() {

    var state by mutableStateOf(DetailState())




    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.LoadBook -> {
                loadBook(event.id)
            }

            is DetailEvent.DeleteBook -> {
                deleteBook(event.id)

            }
        }

    }

    private fun deleteBook(id: Int) {
        Log.d("DetailViewModel", "deleteBook: $id")
        viewModelScope.launch {
            Log.d("DetailViewModel", "SCOPE deleteBook: $id")

                Log.d("DetailViewModel", "LET deleteBook: $id")
                state = state.copy(isDeleting = true)
                try {
                    Log.d("DetailViewModel", "INTENTO DE DELETE: $id")
                    booksUseCase.deleteBookUseCase(id)
                    state = state.copy(isDeleting = false)
                } catch (e: Exception) {
                    Log.d("DetailViewModel", "ERROR DELETE: $id")
                    state = state.copy(isLoading = false)
                    state = state.copy(errorMessage = e.message)
                }



        }
    }

    private fun loadBook(id: Int) {
        Log.d("DetailViewModel", "loadBook: $id")
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            try {
                val book = booksUseCase.getBookUseCase(id)
                state = state.copy(book = book)
                state = state.copy(isLoading = false)
            } catch (e: Exception) {
                state = state.copy(isLoading = false)
                state = state.copy(errorMessage = e.message)
            }

        }
    }

}

