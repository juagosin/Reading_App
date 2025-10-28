package com.juagosin.readingAPP.presentation.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juagosin.readingAPP.domain.use_case.BooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val booksUseCase: BooksUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    var state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        loadFollowingBooks()
        loadReadingBook()
        loadLastBookRead()
        loadBooks()
        getPercentBooksFinished()
    }

    private fun getPercentBooksFinished() {
        viewModelScope.launch {
            try {
                val percent = booksUseCase.getPercentFinished()
                _state.update { currentState ->
                    currentState.copy(percentBooksFinished = percent)
                }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error getting percent finished", e)
            }
        }

    }

    private fun loadBooks() {
        Log.d("loadBooks", "loadBooks")

        _state.update { it.copy(isLoadingBooks = true, errorLoadingBooks = null) }
        try {
            Log.d("loadBooks", "try")
            booksUseCase.getLastBooksUseCase()
                .onEach { books ->
                    Log.d("HomeViewModel", "Books loaded: ${books.size}")
                    _state.update { currentState ->
                        currentState.copy(
                            books = books,
                            isLoadingBooks = false,
                            errorLoadingBooks = null
                        )
                    }
                }
                .launchIn(viewModelScope)
        } catch (e: Exception) {
            Log.e("HomeViewModel", "Error loading books", e)
            _state.update { currentState ->
                currentState.copy(
                    isLoadingBooks = false,
                    errorLoadingBooks = e.message ?: "Error desconocido"
                )
            }
        }


    }

    private fun loadLastBookRead() {
        viewModelScope.launch {
            _state.update { it.copy(isLoadingLastBookRead = true, errorLoadingLastBook = null) }
            try {
                val lastBookRead = booksUseCase.getLastBookReadUseCase()
                _state.update { currentState ->
                    currentState.copy(
                        lastBookRead = lastBookRead,
                        isLoadingLastBookRead = false
                    )
                }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error loading last book read", e)
                _state.update { currentState ->
                    currentState.copy(
                        isLoadingLastBookRead = false,
                        errorLoadingLastBook = e.message ?: "Error desconocido")

                }
            }
        }
    }

    private fun loadReadingBook() {
        viewModelScope.launch {
            _state.update { it.copy(isLoadingReading = true, errorLoadingReading = null) }

            try {
                val bookReading = booksUseCase.getBookReadingUseCase()
                _state.update { currentState ->
                    currentState.copy(
                        bookReading = bookReading,
                        isLoadingReading = false
                    )
                }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error loading reading book", e)
                _state.update { currentState ->
                    currentState.copy(
                        isLoadingReading = false,
                        errorLoadingReading = e.message ?: "Error desconocido"
                    )
                }
            }
        }

    }

    private fun loadFollowingBooks() {
        booksUseCase.getBooksFollowingUseCase()
            .onEach { books ->
                _state.update { currentState ->
                    currentState.copy(booksFollowing = books)
                }
            }
            .catch { e ->
                Log.e("HomeViewModel", "Error loading following books", e)
            }
            .launchIn(viewModelScope)
    }

}