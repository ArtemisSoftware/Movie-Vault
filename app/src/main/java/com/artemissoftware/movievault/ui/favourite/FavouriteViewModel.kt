package com.artemissoftware.movievault.ui.favourite

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.artemissoftware.movievault.repositories.FavoriteRepository

class FavouriteViewModel @ViewModelInject constructor(private val repository: FavoriteRepository): ViewModel(){


    val movies = repository.getFavoriteMovies()
}