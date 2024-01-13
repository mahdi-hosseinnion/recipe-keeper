package com.ssmmhh.recipe_keeper.ui.home

import android.widget.Space
import androidx.compose.foundation.Indication
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ssmmhh.recipe_keeper.data.recipe.MockRecipeRepository
import com.ssmmhh.recipe_keeper.data.recipe.Recipe
import com.ssmmhh.recipe_keeper.ui.common.LoadableData
import com.ssmmhh.recipe_keeper.ui.theme.RecipeKeeperTheme

@Composable
fun HomeScreen() {
    val viewModel = viewModel<HomeScreenViewModel>()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(uiState.value)
}

@Composable
fun HomeScreen(uiState: HomeScreenUiState) {
    RecipesList(uiState.recipes)
}

@Composable
fun RecipesList(loadableData: LoadableData<List<Recipe>>) {
    when (loadableData) {
        is LoadableData.Error -> Text("Error with message ${loadableData.errorMessage}")
        LoadableData.Loading -> CircularProgressIndicator()
        is LoadableData.Success -> {
            LazyColumn() {
                val recipes = loadableData.data
                items(recipes, { it.id }) {
                    RecipeItem(recipe = it)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun RecipeItem(recipe: Recipe) {
    Card {
        Column(Modifier.padding(4.dp)) {
            Text(text = recipe.title, style = MaterialTheme.typography.titleMedium)
            Text(
                text = recipe.ingredients.joinToString { "$it" },
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun MainScreenPreviewLoading() {
    RecipeKeeperTheme {
        HomeScreen(
            uiState = HomeScreenUiState(
                recipes = LoadableData.Loading
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreviewLoaded() {
    RecipeKeeperTheme {
        HomeScreen(
            uiState = HomeScreenUiState(
                recipes = LoadableData.Success(MockRecipeRepository.fakeRecipes)
            )
        )
    }
}