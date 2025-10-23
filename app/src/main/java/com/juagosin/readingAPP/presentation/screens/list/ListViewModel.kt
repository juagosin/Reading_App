package com.juagosin.readingAPP.presentation.screens.list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juagosin.readingAPP.domain.use_case.BooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val booksUseCase: BooksUseCase
) : ViewModel() {

    private val _state  = mutableStateOf(ListState())
    val state: MutableState<ListState> = _state

    init {
        loadBooks()
    }

    private fun loadBooks() {
        booksUseCase.getBooksUseCase()
            .onEach { books ->
                _state.value = state.value.copy(
                    books = books
                )

            }
            .launchIn(viewModelScope)

    }



}