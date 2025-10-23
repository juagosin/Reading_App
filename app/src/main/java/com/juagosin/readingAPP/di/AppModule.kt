package com.juagosin.readingAPP.di


import android.app.Application
import androidx.room.Room
import com.juagosin.readingAPP.data.local.BookDatabase
import com.juagosin.readingAPP.data.repository.BookRepositoryImpl
import com.juagosin.readingAPP.domain.repository.BookRepository
import com.juagosin.readingAPP.domain.use_case.AddBookUseCase
import com.juagosin.readingAPP.domain.use_case.BooksUseCase
import com.juagosin.readingAPP.domain.use_case.DeleteBookUseCase
import com.juagosin.readingAPP.domain.use_case.GetBookReadingUseCase
import com.juagosin.readingAPP.domain.use_case.GetBookUseCase
import com.juagosin.readingAPP.domain.use_case.GetBooksFollowingUseCase
import com.juagosin.readingAPP.domain.use_case.GetBooksUseCase
import com.juagosin.readingAPP.domain.use_case.GetLastBookReadUseCase
import com.juagosin.readingAPP.domain.use_case.GetPercentBooksFinished
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBookDatabase(app: Application): BookDatabase {
        return Room.databaseBuilder(
            app,
            BookDatabase::class.java,
            "book_db"
        )
            .fallbackToDestructiveMigration()
            .build()

    }

    @Provides
    @Singleton
    fun provideBookRepository(db: BookDatabase): BookRepository {
        return BookRepositoryImpl(db.bookDao)
    }

    @Provides
    @Singleton
    fun provideBookUseCases(repository: BookRepository): BooksUseCase {
        return BooksUseCase(
            getBooksUseCase = GetBooksUseCase(repository),
            getBooksFollowingUseCase = GetBooksFollowingUseCase(repository),
            deleteBookUseCase = DeleteBookUseCase(repository),
            addBookUseCase = AddBookUseCase(repository),
            getBookUseCase = GetBookUseCase(repository),
            getBookReadingUseCase = GetBookReadingUseCase(repository),
            getLastBookReadUseCase = GetLastBookReadUseCase(repository),
            getPercentFinished = GetPercentBooksFinished(repository)
        )

    }

}