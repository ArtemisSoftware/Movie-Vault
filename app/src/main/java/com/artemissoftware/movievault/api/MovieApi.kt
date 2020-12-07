package com.artemissoftware.movievault.api

import com.artemissoftware.movievault.BuildConfig
import com.artemissoftware.movievault.data.response.MovieResponse
import retrofit2.http.GET

interface MovieApi {


    companion object{
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val API_KEY = BuildConfig.MOVIEDB_API_KEY
    }

    @GET("movie/now_playing?api_key=$API_KEY")
    suspend fun getNowPlayingMovies(): MovieResponse

}