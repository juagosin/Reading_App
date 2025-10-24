package com.juagosin.readingAPP.data.local.mapper

import com.juagosin.readingAPP.data.local.entity.BookEntity
import com.juagosin.readingAPP.domain.model.Book
import com.juagosin.readingAPP.domain.model.BookStatus
import kotlin.collections.map

fun BookEntity.toDomain(): Book {
    return Book(
        id = id,
        title = title,
        author = author,
        imageUrl = imageUrl,
        description = description,
        dateAdded = dateAd,
        dateStarted = dateStart,
        dateFinished = dateEnd,
        status = BookStatus.fromInt(status)
    )
}

fun Book.toEntity(): BookEntity {
    return BookEntity(
        id = id,
        title = title,
        author = author,
        imageUrl = imageUrl,
        description = description,
        dateAd = dateAdded,
        dateStart = dateStarted,
        dateEnd = dateFinished,
        status = status.code
    )
}

fun List<BookEntity>.toDomain(): List<Book> = map { it.toDomain() }