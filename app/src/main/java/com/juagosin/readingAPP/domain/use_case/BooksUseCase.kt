package com.juagosin.readingAPP.domain.use_case

data class BooksUseCase(
    val getBooksUseCase: GetBooksUseCase,
    val getBookReadingUseCase: GetBookReadingUseCase,
    val getBooksFollowingUseCase: GetBooksFollowingUseCase,
    val deleteBookUseCase: DeleteBookUseCase,
    val addBookUseCase: AddBookUseCase,
    val getBookUseCase: GetBookUseCase,
    val getLastBookReadUseCase: GetLastBookReadUseCase,
    val getPercentFinished: GetPercentBooksFinished,
    val searchBooksUseCase: SearchBooksUseCase
)
