package com.juagosin.readingAPP.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("numFound")
    val numFound: Int,
    @SerializedName("docs")
    val docs: List<BookDto>
)

data class BookDto(
    @SerializedName("title")
    val title: String,
    @SerializedName("author_name")
    val authorName: List<String>? = null,
    @SerializedName("cover_i")
    val coverId: Int? = null
)