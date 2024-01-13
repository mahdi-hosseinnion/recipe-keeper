package com.ssmmhh.recipe_keeper.ui.home

import com.ssmmhh.recipe_keeper.data.recipe.Recipe
import com.ssmmhh.recipe_keeper.ui.common.LoadableData

data class HomeScreenUiState(
    val recipes: LoadableData<List<Recipe>>
) {
    companion object {
        val initialValue: HomeScreenUiState =
            HomeScreenUiState(
                LoadableData.Loading
            )
    }
}
