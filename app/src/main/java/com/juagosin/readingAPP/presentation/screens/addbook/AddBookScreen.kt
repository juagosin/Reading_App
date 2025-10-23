package com.juagosin.readingAPP.presentation.screens.addbook

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBookScreen(
    viewModel: AddBookViewModel = hiltViewModel(),
    onBookSaved: () -> Unit
) {

    val state = viewModel.state
    if (state.isSuccess) {
        onBookSaved()
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
                viewModel.onEvent(AddBookEvent.OnTitleChange(it))
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
                viewModel.onEvent(AddBookEvent.OnAuthorChange(it))
            },
            label = {
                Text(text = stringResource(R.string.txt_book_author))
            }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.imageUrl,
            onValueChange = {
                viewModel.onEvent(AddBookEvent.OnImageUrlChange(it))
            },
            label = {
                Text(text = stringResource(R.string.txt_book_imageurl))
            }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.description,
            onValueChange = {
                viewModel.onEvent(AddBookEvent.OnDescriptionChange(it))
            },
            minLines = 5,
            label = {
                Text(text = stringResource(R.string.txt_book_notes))
            }
        )
        DatePickerTextField(
            value = state.startDate,
            onValueChange = {
                viewModel.onEvent(AddBookEvent.OnDateStartChange(it))
            },
            label = stringResource(R.string.txt_book_datestart),
            modifier = Modifier.fillMaxWidth()
        )
        DatePickerTextField(
            value = state.endDate,
            onValueChange = {
                viewModel.onEvent(AddBookEvent.OnDateEndChange(it))
            },
            label = stringResource(R.string.txt_book_dateend),
            modifier = Modifier.fillMaxWidth()
        )
        DropdownExample()
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            onClick = {
                viewModel.onEvent(AddBookEvent.SaveBook)
            },
            enabled = !state.isSaving

        ) {
            if (state.isSaving) {
                CircularProgressIndicator()
            } else {
                Text(text = stringResource(R.string.btn_save))
            }
        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownExample(viewModel: AddBookViewModel = hiltViewModel()) {

    var expanded by remember { mutableStateOf(false) }

    val options = BookStatus.entries


    var selectedOption by remember { mutableStateOf(options[0]) }


    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {

        OutlinedTextField(
            value = stringResource(selectedOption.descriptionRes) ,

            onValueChange = {
                viewModel.onEvent(AddBookEvent.OnStatusChange(selectedOption.code))


            },
            readOnly = true,
            label = {
                Text(text = stringResource(R.string.txt_book_dropdown) )
                    },
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
                        viewModel.onEvent(AddBookEvent.OnStatusChange(selectedOption.code))

                    }
                )
            }
        }
    }
}