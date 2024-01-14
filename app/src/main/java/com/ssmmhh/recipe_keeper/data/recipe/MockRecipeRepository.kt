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
                    image = Image("https://cdn.loveandlemons.com/wp-content/uploads/2023/05/potato-salad-recipe.jpg"),
                    ingredients = listOf("orange", "salt", "potatoes", "tomatoes"),
                    instructions = listOf("boil the potatoes")
                )
            )
            add(
                Recipe(
                    title = "Ghorme sabzi",
                    image = Image("https://cdn.yjc.ir/files/fa/news/1396/7/5/6824706_316.jpg"),
                    ingredients = listOf("rice", "lemon", "tomatoes", "salt", "potatoes"),
                    instructions = listOf("wash the dishes", "boil the potatoes")
                )
            )
            add(
                Recipe(
                    title = "Adasi",
                    image = Image("https://img-global.cpcdn.com/recipes/277642ba883dfd2c/680x482cq70/%D8%B9%D8%AF%D8%B3%DB%8C-%D8%BA%D8%B0%D8%A7%DB%8C-%D9%85%D9%82%D9%88%DB%8C-%D8%AF%D8%B3%D8%AA%D9%88%D8%B1-%D8%A7%D8%B5%D9%84%DB%8C-%D8%B9%DA%A9%D8%B3.jpg"),
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