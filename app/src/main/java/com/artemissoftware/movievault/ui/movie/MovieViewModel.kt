package com.artemissoftware.movievault.ui.movie

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.artemissoftware.movievault.repositories.MovieRepository

class MovieViewModel @ViewModelInject constructor(private val repository: MovieRepository) : ViewModel(){

    val movies = repository.getNowPlayingMovies()

}