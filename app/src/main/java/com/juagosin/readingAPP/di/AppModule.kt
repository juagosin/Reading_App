package com.juagosin.readingAPP.di


import android.app.Application
import androidx.room.Room
import com.juagosin.readingAPP.data.local.BookDatabase
import com.juagosin.readingAPP.data.remote.OpenLibraryApi
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
import com.juagosin.readingAPP.domain.use_case.SearchBooksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
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
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://openlibrary.org/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    @Singleton
    fun provideOpenLibraryApi(retrofit: Retrofit): OpenLibraryApi {
        return retrofit.create(OpenLibraryApi::class.java)
    }
    @Provides
    @Singleton
    fun provideBookRepository(db: BookDatabase,
                              api: OpenLibraryApi
    ): BookRepository {
        return BookRepositoryImpl(db.bookDao, api)
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
            getPercentFinished = GetPercentBooksFinished(repository),
            searchBooksUseCase = SearchBooksUseCase(repository)
        )

    }

}