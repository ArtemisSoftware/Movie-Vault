package com.artemissoftware.movievault.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.artemissoftware.movievault.db.entities.Favorite

@Dao
interface FavoriteDao {

    @Insert
    suspend fun addToFavorite(favorite: Favorite)

    @Query("SELECT * FROM favorite_movie")
    fun getFavoriteMovies(): LiveData<List<Favorite>>

    @Query("SELECT count(*) FROM favorite_movie WHERE favorite_movie.id_movie = :id")
    suspend fun checkMovie(id: String): Int

    @Query("DELETE FROM favorite_movie WHERE favorite_movie.id_movie = :id" )
    suspend fun removeFromFavorite(id: String) : Int
}