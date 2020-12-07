package com.artemissoftware.movievault.repositories

import com.artemissoftware.movievault.api.MovieApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(private val movieApi: MovieApi){
}