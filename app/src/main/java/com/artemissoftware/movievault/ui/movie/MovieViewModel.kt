package com.artemissoftware.movievault.ui.movie

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.artemissoftware.movievault.repositories.MovieRepository

class MovieViewModel @ViewModelInject constructor(private val repository: MovieRepository, @Assisted state: SavedStateHandle) : ViewModel(){

    companion object{
        private const val CURRENT_QUERY = "current_query"
        private const val EMPTY_QUERY = ""
    }

    private val currentQuery: MutableLiveData<String> = state.getLiveData(CURRENT_QUERY, EMPTY_QUERY)


    val movies = currentQuery.switchMap { query ->
        if (!query.isEmpty()){
            repository.getSearchMovies(query)
        }
        else{
            repository.getNowPlayingMovies().cachedIn(viewModelScope)
        }
    }

    fun searchMovies(query: String){
        currentQuery.value = query
    }

}