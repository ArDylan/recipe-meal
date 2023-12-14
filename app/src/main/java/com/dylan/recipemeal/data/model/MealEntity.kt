package com.dylan.recipemeal.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal")
class MealEntity(
    @field:ColumnInfo(name = "idMeal")
    @field:PrimaryKey
    val idMeal: String,

    @field:ColumnInfo(name = "strMeal")
    val strMeal: String,

    @field:ColumnInfo(name = "strMealThumb")
    val strMealThumb: String,

    @field:ColumnInfo(name = "strTags")
    val strTags: String?,

    @field:ColumnInfo(name = "strCategory")
    val strCategory: String,

    @field:ColumnInfo(name = "strYoutube")
    val strYoutube: String,
)