package com.juagosin.estudio.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.juagosin.estudio.R


enum class BookStatus(
    val code: Int,
    val descriptionRes: Int,
    val backgroundColor: Color,
    val icon: ImageVector
) {
    PENDING(code = 1, descriptionRes = R.string.txt_book_status_following, backgroundColor = Color.Red.copy(0.1f), icon = Icons.Default.Notifications),
    READ(code = 2, descriptionRes = R.string.txt_book_status_finished, backgroundColor = Color.Green.copy(0.1f), icon = Icons.Default.Bookmark),
    READING(code = 3, descriptionRes = R.string.txt_book_status_reading, backgroundColor = Color.Yellow.copy(0.1f), icon = Icons.Default.Book);
}
fun getBookStatusByCode(code: Int): BookStatus? {
    return BookStatus.entries.find { it.code == code }
}