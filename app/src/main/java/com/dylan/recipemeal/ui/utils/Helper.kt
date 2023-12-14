package com.dylan.recipemeal.ui.utils

import com.dylan.recipemeal.data.model.MealsItem

fun MealsItem.extractIngredients(): List<String> {
    val ingredients = mutableListOf<String>()
    if (strIngredient1 != null) ingredients.add("1. $strMeasure1 $strIngredient1")
    if (strIngredient2 != null) ingredients.add("2. $strMeasure2 $strIngredient2")
    if (strIngredient3 != null) ingredients.add("3. $strMeasure3 $strIngredient3")
    if (strIngredient4 != null) ingredients.add("4. $strMeasure4 $strIngredient4")
    if (strIngredient5 != null) ingredients.add("5. $strMeasure5 $strIngredient5")
    if (strIngredient6 != null) ingredients.add("6. $strMeasure6 $strIngredient6")
    if (strIngredient7 != null) ingredients.add("7. $strMeasure7 $strIngredient7")
    if (strIngredient8 != null) ingredients.add("8. $strMeasure8 $strIngredient8")
    if (strIngredient9 != null) ingredients.add("9. $strMeasure9 $strIngredient9")
    if (strIngredient10 != null) ingredients.add("10. $strMeasure10 $strIngredient10")
    if (strIngredient11 != null) ingredients.add("11. $strMeasure11 $strIngredient11")
    if (strIngredient12 != null) ingredients.add("12. $strMeasure12 $strIngredient12")
    if (strIngredient13 != null) ingredients.add("13. $strMeasure13 $strIngredient13")
    if (strIngredient14 != null) ingredients.add("14. $strMeasure14 $strIngredient14")
    if (strIngredient15 != null) ingredients.add("15. $strMeasure15 $strIngredient15")
    return ingredients
}