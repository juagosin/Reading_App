package com.juagosin.estudio.domain.use_case

import com.juagosin.estudio.data.local.entity.Book
import com.juagosin.estudio.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow

class GetBooksFollowingUseCase(
    private val repository: BookRepository)
{
    operator fun invoke(): Flow<List<Book>> {
        return repository.getFollowBooksOrderByDateAs()
    }

}