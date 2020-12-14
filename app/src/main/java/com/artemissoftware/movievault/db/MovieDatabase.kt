package com.artemissoftware.movievault.db


import androidx.room.Database
import androidx.room.RoomDatabase
import com.artemissoftware.movievault.db.dao.FavoriteDao
import com.artemissoftware.movievault.db.entities.Favorite

@Database(
    entities = [Favorite::class],
    version = 1
)
abstract class MovieDatabase : RoomDatabase(){
    abstract fun getFavoriteDao(): FavoriteDao
}