package com.dylan.recipemeal.data.model

data class MealsResponse(
	val meals: List<MealsItem>? = null
)

data class MealsItem(
	val strCategory: String,
	val strMealThumb: String,
	val strYoutube: String,
	val strMeal: String,
	val strTags: String,
	val idMeal: String,
	val strInstructions: String,
	val strCreativeCommonsConfirmed: Any? = null,
	val strArea: String? = null,
	val strImageSource: Any? = null,
	val strIngredient1: String? = null,
	val strIngredient2: String? = null,
	val strIngredient3: String? = null,
	val strIngredient5: String? = null,
	val strIngredient4: String? = null,
	val strIngredient6: String? = null,
	val strIngredient7: String? = null,
	val strIngredient8: String? = null,
	val strIngredient9: String? = null,
	val strIngredient10: String? = null,
	val strIngredient12: String? = null,
	val strIngredient11: String? = null,
	val strIngredient13: String? = null,
	val strIngredient14: String? = null,
	val strIngredient15: String? = null,
	val strIngredient16: String? = null,
	val strIngredient17: String? = null,
	val strIngredient18: String? = null,
	val strIngredient19: String? = null,
	val strIngredient20: String? = null,
	val dateModified: Any? = null,
	val strDrinkAlternate: Any? = null,
	val strSource: String? = null,
	val strMeasure1: String? = null,
	val strMeasure2: String? = null,
	val strMeasure3: String? = null,
	val strMeasure4: String? = null,
	val strMeasure5: String? = null,
	val strMeasure6: String? = null,
	val strMeasure7: String? = null,
	val strMeasure8: String? = null,
	val strMeasure9: String? = null,
	val strMeasure10: String? = null,
	val strMeasure11: String? = null,
	val strMeasure12: String? = null,
	val strMeasure13: String? = null,
	val strMeasure14: String? = null,
	val strMeasure15: String? = null,
	val strMeasure16: String? = null,
	val strMeasure17: String? = null,
	val strMeasure18: String? = null,
	val strMeasure19: String? = null,
	val strMeasure20: String? = null,

) {
	fun toMealEntity(): MealEntity{
		return MealEntity(
			idMeal = this.idMeal,
			strMeal = this.strMeal,
			strMealThumb = this.strMealThumb,
			strCategory = this.strCategory,
			strTags = this.strTags,
			strYoutube = this.strYoutube
		)
	}
}

