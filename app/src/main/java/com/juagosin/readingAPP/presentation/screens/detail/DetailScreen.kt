package com.juagosin.readingAPP.presentation.screens.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage
import com.juagosin.readingAPP.R
import com.juagosin.readingAPP.presentation.common.DateUtils.formatDate

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    bookId: Int,
    onDeleteBook: (() -> Unit)? = null
) {
    val state = viewModel.state
    LaunchedEffect(bookId) {
        viewModel.onEvent(DetailEvent.LoadBook(bookId))
    }

    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .weight(1.5f)
                    .padding(24.dp)

            ) {
                AsyncImage(
                    modifier = Modifier
                        .width(80.dp)
                        .height(100.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    model = state.book?.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    placeholder = painterResource(R.drawable.bookcoverdefault),
                    error = painterResource(R.drawable.bookcoverdefault)
                )
            }
            Column(
                modifier = Modifier
                    .weight(2.5f)
                    .padding(top = 24.dp)
            ) {
                Text(
                    "${state.book?.title}",
                    color = colorScheme.primary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    "${state.book?.author}",
                    color = colorScheme.secondary,
                    fontStyle = FontStyle.Italic,
                    fontSize = 16.sp,
                )

            }


        }
        if (state.book?.dateStarted != null) {
            Text(
                "Inicio de lectura: ${formatDate(state.book.dateStarted)} ",
                modifier = Modifier.padding(horizontal = 24.dp),
                color = colorScheme.primary,
                fontSize = 10.sp,
            )
        }
        if (state.book?.dateFinished != null) {
            Text(
                "Fin de lectura: ${formatDate(state.book.dateFinished)} ",
                modifier = Modifier.padding(horizontal = 24.dp),
                color = colorScheme.primary,
                fontWeight = FontWeight.Medium,
                fontSize = 10.sp,
            )
        }
        Text(
            "${state.book?.description}",
            modifier = Modifier.padding(horizontal = 24.dp),
            color = colorScheme.tertiary,
            fontSize = 14.sp,
        )
    }


}