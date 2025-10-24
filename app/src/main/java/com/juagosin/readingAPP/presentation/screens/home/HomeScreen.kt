package com.juagosin.readingAPP.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.juagosin.readingAPP.R
import com.juagosin.readingAPP.presentation.common.DateUtils.formatDate
import com.juagosin.readingAPP.presentation.common.MyBooksSection

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onItemClick: (bookId: Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    when {
        state.isLoadingBooks -> {
            LoadingScreen()
        }
        state.errorLoadingBooks != null -> {
            ErrorScreen(error = state.errorLoadingBooks!!)
        }
        state.books.isEmpty() -> {
            EmptyLibrary()
        }
        else -> {
            BookListContent(
                state = state,
                onItemClick = onItemClick
            )
        }
    }

}

@Composable
fun EmptyLibrary() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_booktrack_foreground),
                contentDescription = "Empty library",
                modifier = Modifier
                    .size(160.dp)
                    .padding(bottom = 24.dp)
            )

            // Texto principal
            Text(
                text = stringResource(R.string.txt_title_empty_library),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                ),
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Texto secundario
            Text(
                text = stringResource(R.string.txt_empty_library),
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f),
                textAlign = TextAlign.Center
            )
        }
    }
}


@Composable
private fun BookListContent(
    state: HomeState,
    onItemClick: (bookId: Int) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        state.bookReading?.let { bookReading ->
            val dateRange = bookReading.dateStarted?.let {
                formatDate(it)
            } ?: ""

            CurrentBookCard(
                title = bookReading.title,
                author = bookReading.author,
                dateRange = dateRange,
                imageUrl = bookReading.imageUrl
            )

            Spacer(modifier = Modifier.height(18.dp))
        }

        StatisticsSection(
            totalBooks = state.books.size,
            finishedPercent = state.percentBooksFinished
        )

        Spacer(modifier = Modifier.height(18.dp))

        MyBooksSection(state.books, onItemClick)
    }
}

@Composable
fun CurrentBookCard(
    title: String,
    author: String,
    dateRange: String,
    imageUrl: String,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(1.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorScheme.surface,
            contentColor = colorScheme.onSurface
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(84.dp)
            ) {
                AsyncImage(
                    model = imageUrl,
                    modifier = Modifier.fillMaxSize(),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    placeholder = painterResource(R.drawable.bookcoverdefault),
                    error = painterResource(R.drawable.bookcoverdefault)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = title,
                    color = colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                )
                Text(author, color = Color.Gray)
                Text(dateRange, color = Color.Gray, fontSize = 13.sp)
                Text(
                    text = stringResource(R.string.txt_book_status_reading),
                    modifier = Modifier.fillMaxSize(),
                    color = colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Right,
                )
            }
        }
    }
}

@Composable
fun StatisticsSection(
    totalBooks: Int,
    finishedPercent: Float
) {
    Column {
        Text(
            stringResource(R.string.txt_card_statics),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(modifier = Modifier.weight(1f)) {
                StatCard(
                    value = "$totalBooks",
                    label = if (totalBooks == 1) stringResource(R.string.txt_book) else stringResource(
                        R.string.txt_books
                    )
                )
            }
            Box(modifier = Modifier.weight(1f)) {
                StatCard(
                    value = "$finishedPercent%",
                    label = stringResource(R.string.txt_booksFinished)
                )
            }
        }
    }
}


@Composable
private fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorScreen(error: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Error al cargar los libros",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = error,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}
@Composable
fun StatCard(value: String, label: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorScheme.surface,
            contentColor = colorScheme.onSurface
        )
    ) {
        Column(
            modifier = Modifier.padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                value, fontWeight = FontWeight.Bold, fontSize = 20.sp,
                modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center
            )
            Text(
                label,
                color = Color.Gray,
                modifier = Modifier.fillMaxSize(),
                textAlign = TextAlign.Center
            )
        }
    }

}
