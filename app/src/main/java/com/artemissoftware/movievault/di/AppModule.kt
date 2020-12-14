package com.artemissoftware.movievault.di

import android.content.Context
import androidx.room.Room
import com.artemissoftware.movievault.api.MovieApi
import com.artemissoftware.movievault.db.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(MovieApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit): MovieApi =
        retrofit.create(MovieApi::class.java)


    @Singleton
    @Provides
    fun provideMovieDatabase(@ApplicationContext app: Context) = Room.databaseBuilder(app, MovieDatabase::class.java, "movie_db").build()

    @Singleton
    @Provides
    fun provideFavoriteDao(db: MovieDatabase) = db.getFavoriteDao()

}