package com.juagosin.readingAPP.presentation.screens.edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.juagosin.readingAPP.R
import com.juagosin.readingAPP.domain.model.BookStatus
import com.juagosin.readingAPP.presentation.common.DatePickerTextField
import com.juagosin.readingAPP.presentation.screens.addbook.BookStatusDropdown


@Composable
fun EditScreen(
    viewModel: EditViewModel = hiltViewModel(),
    onBookEdited: () -> Unit,
    bookId: Int
) {
    val state = viewModel.state
    if (state.isSuccess) {
        onBookEdited()
    }
    LaunchedEffect(bookId) {
        viewModel.onEvent(EditEvent.LoadBook(bookId))
    }
    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
    ) {

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.title,
            isError = state.titleError != null,
            supportingText = {
                if (state.titleError != null) {
                    Text(state.titleError, color = colorScheme.error)
                }

            },
            onValueChange = {

                viewModel.onEvent(EditEvent.OnTitleChange(it))
            },
            label = {
                Text(text = stringResource(R.string.txt_book_title))
            }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.author,
            isError = state.authorError != null,
            supportingText = {
                if (state.authorError != null) {
                    Text(state.authorError, color = colorScheme.error)
                }

            },
            onValueChange = {
                viewModel.onEvent(EditEvent.OnAuthorChange(it))
            },
            label = {
                Text(text = stringResource(R.string.txt_book_author))
            }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.imageUrl,
            onValueChange = {
                viewModel.onEvent(EditEvent.OnImageUrlChange(it))
            },
            label = {
                Text(text = stringResource(R.string.txt_book_imageurl))
            }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.description,
            onValueChange = {
                viewModel.onEvent(EditEvent.OnDescriptionChange(it))
            },
            minLines = 5,
            label = {
                Text(text = stringResource(R.string.txt_book_notes))
            }
        )
        DatePickerTextField(
            value = state.startDate,
            onValueChange = {
                viewModel.onEvent(EditEvent.OnDateStartChange(it))
            },
            label = stringResource(R.string.txt_book_datestart),
            modifier = Modifier.fillMaxWidth()
        )
        DatePickerTextField(
            value = state.endDate,
            onValueChange = {
                viewModel.onEvent(EditEvent.OnDateEndChange(it))
            },
            label = stringResource(R.string.txt_book_dateend),
            modifier = Modifier.fillMaxWidth()
        )
        BookStatusDropdown(
            selectedStatus = state.status,
            onStatusChange = { viewModel.onEvent(EditEvent.OnStatusChange(it)) },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            onClick = {
                viewModel.onEvent(EditEvent.EditBook)
            },
            enabled = !state.isEditing

        ) {
            if (state.isEditing) {
                CircularProgressIndicator()
            } else {
                Text(text = stringResource(R.string.btn_edit))
            }

        }
    }
}

