package com.juagosin.readingAPP.presentation.screens.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.juagosin.readingAPP.R
import com.juagosin.readingAPP.domain.model.BookStatus
import com.juagosin.readingAPP.presentation.common.BookListItem

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    viewModel: ListViewModel = hiltViewModel(),
    onItemClick: (bookId: Int) -> Unit
) {
    val state = viewModel.state.value
    val options = BookStatus.entries
    val scope = rememberCoroutineScope()



    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp )
    ) {

        if (state.books.isEmpty()) {
            item {
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(vertical = 4.dp),
                    shape = RoundedCornerShape(20.dp),
                    onClick = {
                    }

                ) {
                    Box(
                        modifier = Modifier
                            .background(Color.White)

                            .fillMaxSize()

                    ) {
                        Image(
                            Icons.Default.Notifications,
                            contentDescription = null,
                            contentScale = ContentScale.FillHeight,

                            modifier = Modifier
                                .fillMaxSize()
                                .alpha(0.05f),
                            alignment = Alignment.CenterEnd,
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(20.dp),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = stringResource(R.string.txt_card_listempty),
                                color = Color.Black.copy(0.7f),
                                fontSize = 15.sp
                            )


                        }
                    }
                }
            }
        }

        items(state.books) { book ->
            BookListItem(book,onItemClick)
            Spacer(modifier = Modifier.height(16.dp))
        }

    }
}

