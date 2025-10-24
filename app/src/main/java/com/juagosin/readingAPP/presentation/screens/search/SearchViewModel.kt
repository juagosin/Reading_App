package com.juagosin.readingAPP.presentation.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(

) : ViewModel() {
 var state by mutableStateOf(SearchState())

    fun onEvent(event: SearchEvent){
        when(event){
            SearchEvent.OnSearch -> TODO()
            is SearchEvent.OnTitleChange -> {
                state = state.copy(title = event.value, titleError = null)
            }
        }
    }
}