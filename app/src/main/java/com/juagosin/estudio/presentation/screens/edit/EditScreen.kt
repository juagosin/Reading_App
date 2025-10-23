package com.juagosin.estudio.presentation.screens.edit

import android.R.attr.author
import android.R.attr.label
import android.util.Log
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.juagosin.estudio.R
import com.juagosin.estudio.domain.model.BookStatus
import com.juagosin.estudio.presentation.common.DatePickerTextField
import com.juagosin.estudio.presentation.screens.addbook.AddBookEvent
import com.juagosin.estudio.presentation.screens.addbook.AddBookViewModel
import com.juagosin.estudio.presentation.screens.addbook.DropdownExample
import com.juagosin.estudio.presentation.screens.detail.DetailEvent


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
        EditDropdown()
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditDropdown(viewModel: EditViewModel = hiltViewModel()) {

    var expanded by remember { mutableStateOf(false) }

    val options = BookStatus.entries


    var selectedOption = options.find { it.code == viewModel.state.status } ?: options.first()


    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {

        OutlinedTextField(
            value = stringResource( selectedOption.descriptionRes),

            onValueChange = {
                viewModel.onEvent(EditEvent.OnStatusChange(selectedOption.code))


            },
            readOnly = true,
            label = { Text(stringResource(R.string.txt_book_dropdown)) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        // Menú desplegable
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(stringResource(id =option.descriptionRes)) },
                    onClick = {
                        selectedOption = option
                        expanded = false
                        viewModel.onEvent(EditEvent.OnStatusChange(selectedOption.code))

                    }
                )
            }
        }
    }
}