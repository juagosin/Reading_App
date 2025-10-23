package com.juagosin.readingAPP.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.juagosin.readingAPP.R
import com.juagosin.readingAPP.data.local.entity.Book
import com.juagosin.readingAPP.domain.model.BookStatus
import com.juagosin.readingAPP.domain.model.getBookStatusByCode
import kotlin.collections.forEach


@Composable
fun MyBooksSection(books: List<Book>, onItemClick: (bookId: Int) -> Unit) {
    Column {
        Text(stringResource(R.string.screen_title_list), fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        books.forEach { book ->
            BookListItem(book, onItemClick)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
@Composable
fun BookListItem(book: Book, onItemClick: (Int) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(top = 2.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorScheme.surface,
            contentColor = colorScheme.onSurface
        ),
        onClick = {
            onItemClick(book.id!!)
        }
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = book.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .width(80.dp)
                    .height(100.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.bookcoverdefault),
                error = painterResource(R.drawable.bookcoverdefault)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(book.title, fontWeight = FontWeight.Bold)
                Text(book.author, color = Color.Gray)

                Text(
                    text = stringResource(getBookStatusByCode(book.status)!!.descriptionRes),
                    fontWeight = FontWeight.Medium,
                    color =
                        when(getBookStatusByCode(book.status)!!) {
                            BookStatus.READING -> colorScheme.primary
                            BookStatus.PENDING -> colorScheme.error
                            BookStatus.READ -> colorScheme.secondary
                        }
                )

            }
        }
    }
}