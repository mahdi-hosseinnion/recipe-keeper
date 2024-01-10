package com.ssmmhh.recipe_keeper.data.recipe

data class Recipe(
    val title: String,
    val ingredients: List<String>,
    val instructions: List<String>
)
