package com.artemissoftware.movievault.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.artemissoftware.movievault.api.MovieApi
import com.artemissoftware.movievault.ui.MoviePagingSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(private val movieApi: MovieApi){

    fun getNowPlayingMovies() =
        Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {MoviePagingSource(movieApi, null)}
        ).liveData

    fun getSearchMovies(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {MoviePagingSource(movieApi, query)}
        ).liveData
}