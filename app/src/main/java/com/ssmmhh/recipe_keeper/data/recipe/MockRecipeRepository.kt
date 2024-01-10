package com.ssmmhh.recipe_keeper.data.recipe

import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

class MockRecipeRepository : RecipeRepository {

    private val fakeRecipes = buildList<Recipe> {
        add(
            Recipe(
                title = "Potato salad",
                ingredients = listOf("orange", "salt", "potatoes", "tomatoes"),
                instructions = listOf("boil the potatoes")
            )
        )
        add(
            Recipe(
                title = "aenean",
                ingredients = listOf("lemon", "tomatoes", "salt", "potatoes"),
                instructions = listOf("wash the dishes","boil the potatoes")
            )
        )
        add(
            Recipe(
                title = "aenean",
                ingredients = listOf("bread", "tomatoes", "salt", "potatoes"),
                instructions = listOf("Put gas on medium level","boil the potatoes")
            )
        )
    }

    override suspend fun getRecipes(): List<Recipe> {
        delay(500.milliseconds)
        return fakeRecipes
    }

}