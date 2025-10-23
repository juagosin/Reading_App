package com.juagosin.readingAPP.domain.use_case

import com.juagosin.readingAPP.domain.repository.BookRepository

class GetPercentBooksFinished(private val repository: BookRepository) {
    suspend operator fun invoke():Float{
        return repository.getPercentFinished()

    }
}