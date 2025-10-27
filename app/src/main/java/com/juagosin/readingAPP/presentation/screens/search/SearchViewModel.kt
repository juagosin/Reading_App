package com.juagosin.readingAPP.presentation.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juagosin.readingAPP.domain.model.BookSearchResult
import com.juagosin.readingAPP.domain.use_case.BooksUseCase
import com.juagosin.readingAPP.presentation.screens.addbook.AddBookState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val booksUseCase: BooksUseCase
) : ViewModel() {
    var state by mutableStateOf(AddBookState())
    private val _searchResults = MutableStateFlow<List<BookSearchResult>>(emptyList())
    val searchResults: StateFlow<List<BookSearchResult>> = _searchResults.asStateFlow()
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun onEvent(event: SearchEvent){
        when(event){
            SearchEvent.OnSearch -> {
                searchBooks()
            }

            is SearchEvent.OnTitleChange -> {
                state = state.copy(title = event.value, titleError = null)
            }
        }
    }
    fun searchBooks() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            booksUseCase.searchBooksUseCase(state.title)
                .onSuccess { results ->
                    _searchResults.value = results
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "Error desconocido"
                    _searchResults.value = emptyList()
                }

            _isLoading.value = false
        }
    }
}