package com.juagosin.readingAPP.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.juagosin.readingAPP.data.local.entity.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Query("SELECT * FROM book ORDER BY dateAd DESC")
    fun getBooksOrderByDateAd(): Flow<List<BookEntity>>

    @Query("SELECT * FROM book WHERE status = 1 ORDER BY dateAd")
    fun getFollowBooksOrderByDateAs(): Flow<List<BookEntity>>

    @Query("SELECT * FROM book WHERE id = :id")
    suspend fun getBookById(id: Int): BookEntity?

    @Query("SELECT * FROM book where status = 3 ORDER BY dateAd DESC LIMIT 1")
    suspend fun getBookReading():BookEntity?

    @Query("SELECT * FROM book where status = 2 ORDER by dateEnd DESC LIMIT 1")
    suspend fun getLastBookRead():BookEntity?

    @Query("DELETE FROM book WHERE id = :id")
    suspend fun deleteBook(id: Int?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: BookEntity)

    @Query("SELECT ROUND(100.0 * SUM(CASE WHEN status = 2 THEN 1 ELSE 0 END) / COUNT(*), 2) FROM book")
    suspend fun getPercentFinished():Float




}