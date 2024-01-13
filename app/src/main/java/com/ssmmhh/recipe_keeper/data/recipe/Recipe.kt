package com.ssmmhh.recipe_keeper.data.recipe

import java.util.UUID

data class Recipe(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val ingredients: List<String>,
    val instructions: List<String>
)
