package com.dylan.recipemeal.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dylan.recipemeal.data.model.MealEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM meal")
    fun getFavoriteMeals(): Flow<List<MealEntity>>

    @Query("SELECT EXISTS(SELECT * FROM meal WHERE idMeal = :idMeal)")
    fun isFavorite(idMeal: String): Flow<Boolean>

    @Query("DELETE FROM meal WHERE idMeal = :idMeal")
    suspend fun delete(idMeal: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(meal: MealEntity)
}