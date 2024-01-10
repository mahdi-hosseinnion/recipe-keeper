package com.ssmmhh.recipe_keeper.data.recipe

interface RecipeRepository {

    suspend fun getRecipes(): List<Recipe>

}