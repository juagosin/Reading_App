package com.juagosin.readingAPP.data.remote

import com.juagosin.readingAPP.data.remote.dto.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenLibraryApi {
    @GET("search.json")
    suspend fun searchBooks(
        @Query("title") title: String,
        @Query("limit") limit: Int = 15
    ): SearchResponse
}