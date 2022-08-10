package com.pou.udfsample.domain

import com.squareup.moshi.Json


/**
{
"genus": "Prunus",
"name": "Apricot",
"id": 35,
"family": "Rosaceae",
"order": "Rosales",
"nutritions": {
"carbohydrates": 3.9,
"protein": 0.5,
"fat": 0.1,
"calories": 15,
"sugar": 3.2
}
}

 * */



data class FruitModel(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "genus") val genus: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "family") val family: String,
    @field:Json(name = "order") val order: String,
    @field:Json(name = "nutritions") val nutritions: NutritionModel
)

data class NutritionModel(
    @field:Json(name = "carbohydrates") val carbohydrates: Float,
    @field:Json(name = "protein") val protein: Float,
    @field:Json(name = "fat") val fat: Float,
    @field:Json(name = "calories") val calories: Float,
    @field:Json(name = "sugar") val sugar: Float,
)