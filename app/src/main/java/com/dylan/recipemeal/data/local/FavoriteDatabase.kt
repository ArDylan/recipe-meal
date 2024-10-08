package com.dylan.recipemeal.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dylan.recipemeal.data.model.MealEntity

@Database(entities = [MealEntity::class], version = 1, exportSchema = false)
abstract class FavoriteDatabase: RoomDatabase(){
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var instance: FavoriteDatabase? = null
        fun getInstance(context: Context): FavoriteDatabase =
            instance ?: synchronized(this){
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteDatabase::class.java, "Favorite.db"
                ).build().also { instance = it }
            }
    }
}