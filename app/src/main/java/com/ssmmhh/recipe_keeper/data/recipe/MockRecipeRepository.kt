package com.ssmmhh.recipe_keeper.data.recipe

import com.ssmmhh.recipe_keeper.utils.ioThread
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

class MockRecipeRepository : RecipeRepository {
    companion object {
        val fakeRecipes = buildList<Recipe> {
            add(
                Recipe(
                    title = "Potato salad",
                    ingredients = listOf("orange", "salt", "potatoes", "tomatoes"),
                    instructions = listOf("boil the potatoes")
                )
            )
            add(
                Recipe(
                    title = "Ghorme sabzi",
                    ingredients = listOf("rice", "lemon", "tomatoes", "salt", "potatoes"),
                    instructions = listOf("wash the dishes", "boil the potatoes")
                )
            )
            add(
                Recipe(
                    title = "Adasi",
                    ingredients = listOf("Lentil", "bread", "tomatoes", "salt", "potatoes"),
                    instructions = listOf("Put gas on medium level", "boil the potatoes")
                )
            )
        }
    }

    override suspend fun getRecipes(): List<Recipe> = ioThread {
        delay(500.milliseconds)
        return@ioThread fakeRecipes
    }

}