package com.artemissoftware.movievault.repositories

import com.artemissoftware.movievault.db.dao.FavoriteDao
import com.artemissoftware.movievault.db.entities.Favorite
import javax.inject.Inject

class FavoriteRepository @Inject constructor(private val favoriteDao: FavoriteDao){

    suspend fun addToFavorite(favorite: Favorite) = favoriteDao.addToFavorite(favorite)

    fun getFavoriteMovies() = favoriteDao.getFavoriteMovies()

    suspend fun checkMovie(id: String) = favoriteDao.checkMovie(id)

    suspend fun remove(id: String) {
        favoriteDao.removeFromFavorite(id)
    }
}