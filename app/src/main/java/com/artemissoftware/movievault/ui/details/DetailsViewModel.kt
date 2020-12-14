package com.artemissoftware.movievault.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemissoftware.movievault.data.Movie
import com.artemissoftware.movievault.db.entities.Favorite
import com.artemissoftware.movievault.repositories.FavoriteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class DetailsViewModel @ViewModelInject constructor(private val repository : FavoriteRepository) : ViewModel(){


    private val detailsEventChannel = Channel<DetailsEvent>()
    val detailsEvent = detailsEventChannel.receiveAsFlow()


    fun addToFavorite(movie: Movie){
        CoroutineScope(Dispatchers.IO).launch {
            repository.addToFavorite(
                Favorite(
                    movie.id,
                    movie.original_title,
                    movie.overview,
                    movie.poster_path
                )
            )
        }
    }

    //option 1
    suspend fun checkMovie_(id: String) = repository.checkMovie(id)

    //option 2
    fun checkMovie(id: String) = viewModelScope.launch {
        val result = repository.checkMovie(id)
        detailsEventChannel.send(DetailsEvent.ToggleFavorite(result))
    }


    fun removeFromFavorite(id: String){
        CoroutineScope(Dispatchers.IO).launch {
            repository.remove(id)
        }
    }


    sealed class DetailsEvent {
        data class ToggleFavorite(val result: Int) : DetailsEvent()
    }


}