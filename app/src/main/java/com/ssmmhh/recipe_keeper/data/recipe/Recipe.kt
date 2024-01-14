package com.ssmmhh.recipe_keeper.data.recipe

import java.util.UUID

@JvmInline
value class Image(val url: String)

data class Recipe(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val image: Image,
    val cookingTimeInMin: Int,
    val ingredients: List<String>,
    val instructions: List<String>,
)

