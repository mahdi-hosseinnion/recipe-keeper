package com.ssmmhh.recipe_keeper.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssmmhh.recipe_keeper.data.recipe.MockRecipeRepository
import com.ssmmhh.recipe_keeper.ui.common.LoadableData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class HomeScreenViewModel : ViewModel() {

    private val recipeRepository = MockRecipeRepository()

    private val _uiState = MutableStateFlow<HomeScreenUiState>(HomeScreenUiState.initialValue)
    val uiState: StateFlow<HomeScreenUiState> =
        _uiState.stateIn(
            viewModelScope,
            WhileSubscribed(5.seconds.inWholeMilliseconds),
            HomeScreenUiState.initialValue
        )

    init {
        fetchRecipes()
    }

    private fun fetchRecipes() {
        viewModelScope.launch {
            _uiState.update { it.copy(recipes = LoadableData.Loading) }
            val recipes = recipeRepository.getRecipes()
            _uiState.update { it.copy(recipes = LoadableData.Success(recipes)) }
        }
    }
}

