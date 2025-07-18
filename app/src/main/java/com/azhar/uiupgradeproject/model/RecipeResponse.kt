package com.azhar.uiupgradeproject.data.model // PASTIKAN PACKAGE INI SESUAI

data class RecipeResponse(
    val recipes: List<Recipe>, // Akan merujuk ke kelas Recipe
    val total: Int,
    val skip: Int,
    val limit: Int
)